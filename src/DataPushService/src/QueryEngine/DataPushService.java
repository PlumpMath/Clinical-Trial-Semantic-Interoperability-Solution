package QueryEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

//import sun.util.calendar.Era;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import normalization.CreateDataBase;
import normalization.Normalization;
import Config.ProgramProperties;
import Config.ServiceProperties;
import PentahoTransformation.Pentaho_Transformation;


public class DataPushService {

	public static String serverDirectory;
	private static String serverIndex = "WEB-INF";

	public static String configFile;
	static Util object_query;
	static String LOG;
	static Logger log;
	static String log4jConfig;	
	static String database;
	static String user;
	static String pass;
	static String service_version;
	static String SNOMED_version;
	static String ETL_version;

	//NORMALIZATION PIPELINE
	static String config_normalization;
	static String host_db;
	static String create_file;
	static String temp_file;
	static String db_script;
	static String host_db2;
	static String truncate_file;
	//Normalization pipeline errors
	static String host_transl;
	static String user_transl;
	static String pass_transl;

	static {
		init_servers();
	}

	private static void init_servers () {

		//Get execution directory
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		int index = classLoader.getResource("").getFile().indexOf(serverIndex);
		serverDirectory = classLoader.getResource("").getFile().substring(0, index);

		configFile = serverDirectory + "config" + File.separator + "properties.config";

		object_query = new Util();

		LOG = ServiceProperties.getLOGmessage(configFile);
		System.out.println("["+LOG+"]-[LOG].- Deploying...");

		//Get config
		service_version = ServiceProperties.getServiceVersion(configFile);
		SNOMED_version = ServiceProperties.getSNOMED_version(configFile);
		ETL_version = ServiceProperties.getETL_version(configFile);
		database = ServiceProperties.getDatabaseName(configFile);
		user = ServiceProperties.getUserDB(configFile);
		pass = ServiceProperties.getPassDB(configFile);

		//NORMALIZATION PIPELINE
		config_normalization = serverDirectory + ServiceProperties.getConfigNormalization(configFile);
		create_file = serverDirectory + ProgramProperties.getCreateFile(configFile);
		temp_file = serverDirectory + ProgramProperties.getTempFile(configFile);
		db_script = serverDirectory + ProgramProperties.getDbScript(configFile);
		host_db = ProgramProperties.getHostDb(configFile);
		host_db2 = ProgramProperties.getHostDb2(configFile);
		truncate_file = serverDirectory + ProgramProperties.getTruncateFile(configFile);
		//Normalization pipeline errors
		host_transl = ProgramProperties.getHostTransl(configFile);
		user_transl = ProgramProperties.getUserTransl(configFile);
		pass_transl = ProgramProperties.getPassTransl(configFile);

		//LOG
		log4jConfig = serverDirectory + ServiceProperties.getLogConfig(configFile);
		PropertyConfigurator.configure(log4jConfig);  
		log = Logger.getLogger(DataPushService.class); 
		
		//Files permissions 
		File f = new File(serverDirectory + ServiceProperties.getDumpScript(configFile));
		f.setExecutable(true, false);
		f = new File(temp_file);
		f.setExecutable(true, false);

	}


	public static void erase_Data () {
		// LOG
		log.info("[ERASE - DATA] - Database: " + database);

		Util object_query = new Util();
		object_query.truncateDB();
	}

	public String getServiceVersion() {
		return service_version;
	}


	// STORE WITH MIRTH
	public static String storeData (String xmlMessage, String messageId, String modeOffline) {
		if(modeOffline == null)  modeOffline = "0";

		String msg = xmlMessage;

		Date actual = new Date();
		System.out.println("\n"+actual + " - ["+LOG+"]-[LOG].- Storing data");
		// LOG
		log.info("[STORING DATA]");	

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName(host_db);
		dataSource.setUser(user);
		dataSource.setPassword(pass);
		dataSource.setAutoReconnect(true);
		Connection con = null;
		Statement st = null;
		try {
			con = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String tmpDB = database + "_tmp";
		if (!modeOffline.equals("0")) {
			//Truncate tmp DB
			try {
				File fichero = new File(truncate_file);
				if (!fichero.exists()) {
					System.out.println("FILE DOESN'T EXIST");
				}
				else {
					String query = null;
					st =  con.createStatement();
					st.executeUpdate("USE " + tmpDB);
					@SuppressWarnings("resource")
					BufferedReader bf = new BufferedReader(new FileReader(fichero));
					while ((query = bf.readLine()) != null) {
						st.executeUpdate(query);
					}
				}
			} catch (SQLException e1) {
				// LOG
				log.error("[TRUNCATE TMP DATABASE] - " + e1.getMessage());
				e1.printStackTrace();
			} catch (IOException e) {
				// LOG
				log.error("[TRUNCATE TMP DATABASE] - " + e.getMessage());
				e.printStackTrace();
			}

		}
		else {
			UUID tmpId = UUID.randomUUID();  
			tmpDB += "_" + tmpId;
			tmpDB = tmpDB.replace("-", "");
			if (tmpDB.length() > 64)
				tmpDB = tmpDB.substring(0,64);
			CreateDataBase cdb = new CreateDataBase();
			cdb.create(con, tmpDB, create_file, db_script, user, pass, host_db2, log, temp_file);
		}


		//Add filename
		int pos = msg.indexOf("</ClinicalDocument>");

		msg = msg.substring(0, pos)+"\t<database name=\""+database+"\" tmp_name=\""+ tmpDB +"\" user=\""+user+"\" password=\""+pass+"\"/>\n"+msg.substring(pos, msg.length());
		msg = msg.replace("xsi:type", "xsi_type");
		long t0 = System.currentTimeMillis();
		String res = Pentaho_Transformation.processETL(msg);


		//Normalization process
		actual = new Date();
		System.out.println("\n"+actual + " - ["+LOG+"]-[LOG].- Normalizing data");
		// LOG
		log.info("[NORMALIZE]");

		Normalization normalization = new Normalization(config_normalization);	
		normalization.Normalize(tmpDB,0);

		PropertyConfigurator.configure(log4jConfig);  
		log = Logger.getLogger(DataPushService.class); 

		//Delete database and return Normalization pipeline errors
		if (modeOffline.equals("0")) {
			try {
				st = con.createStatement();
				st.executeUpdate("DROP DATABASE " + tmpDB);
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
			Connection conErrors = null;
			try {
				MysqlDataSource dataSourceErrors = new MysqlDataSource();
				dataSourceErrors.setServerName(host_transl);
				dataSourceErrors.setUser(user_transl);
				dataSourceErrors.setPassword(pass_transl);
				dataSourceErrors.setAutoReconnect(true);

				conErrors = dataSourceErrors.getConnection();
				st = conErrors.createStatement();		
				ResultSet rs = st.executeQuery("SELECT * FROM `normalization_errors`.`errors`");
				while (rs.next()) {
					if (rs.getInt("translationError") == 0) 
						res = "[NormalizationPipeline]-[WARNING].- The act " + rs.getString("id") + " with code " + rs.getString("code") + " from message " + rs.getString("idMsg") + " do not belong to Core Data set" + "\n" + res;
					else
						res = "[NormalizationPipeline]-[WARNING].- The act " + rs.getString("id") + " with code " + rs.getString("code") + " from message " + rs.getString("idMsg") + " can not be translated" + "\n" + res;	
				}
				conErrors.close();
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}

		long t1 = System.currentTimeMillis();
		long time= t1-t0;
		// LOG
		log.info("[RESULT] - \n" +
				"msg: " + xmlMessage +"\n" +
				"res: " + res +"\n" +
				"Execution time: " + time +"ms" + "\n---------------------------------------------------------------------");
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return res;
	}

}
