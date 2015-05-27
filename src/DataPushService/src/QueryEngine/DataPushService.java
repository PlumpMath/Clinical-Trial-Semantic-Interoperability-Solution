package QueryEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import sun.util.calendar.Era;

import java.util.HashSet;
import java.util.Set;
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
		
		//Change files permissions
		Set<PosixFilePermission> perms = new HashSet<PosixFilePermission>();
        //add owners permission
        perms.add(PosixFilePermission.OWNER_READ);
        perms.add(PosixFilePermission.OWNER_WRITE);
        perms.add(PosixFilePermission.OWNER_EXECUTE);
        //add group permissions
        perms.add(PosixFilePermission.GROUP_READ);
        perms.add(PosixFilePermission.GROUP_WRITE);
        perms.add(PosixFilePermission.GROUP_EXECUTE);
        //add others permissions
        perms.add(PosixFilePermission.OTHERS_READ);
        perms.add(PosixFilePermission.OTHERS_WRITE);
        perms.add(PosixFilePermission.OTHERS_EXECUTE);
         
        try {
        	File dir = new File(serverDirectory + "files");
        	String[] files = dir.list();
        	for (int i = 0; i < files.length; i++) {
        		Files.setPosixFilePermissions(Paths.get(serverDirectory + "files" + File.separator + files[i]), perms);
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        System.out.println("["+LOG+"]-[LOG].- Deployed");

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


	public static void rollback(String dateString) {
		
		log.info("[ROLLBACK] - Given date: " + dateString);
		
		Timestamp date = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date parsedDate = dateFormat.parse(dateString);
			date = new java.sql.Timestamp(parsedDate.getTime());
		} catch (ParseException e1) {
			log.error(e1.getMessage());
			e1.printStackTrace();
		}

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName(host_db);
		dataSource.setDatabaseName(database);
		dataSource.setUser(user);
		dataSource.setPassword(pass);	

		String normalized = "normalized_" + database;

		MysqlDataSource dataSource2 = new MysqlDataSource();
		dataSource2.setServerName(host_db);
		dataSource2.setDatabaseName(normalized);
		dataSource2.setUser(user);
		dataSource2.setPassword(pass);

		try {
			Connection con = dataSource.getConnection();
			Connection conNorm = dataSource2.getConnection();
			//Select messages modified after the given date 
			String query = "SELECT id FROM " + database + " .act WHERE modificationTime != '0000-00-00 00:00:00' AND modificationTime IS NOT NULL AND modificationTime > '"+ date +"'";
			ResultSet rs = con.prepareStatement(query).executeQuery();
			while (rs.next()) {
				String idMsg = rs.getString(1);
				//Delete message with modification time previous to the given date on both database and normalized database
				query = "DELETE FROM act WHERE id = '" + idMsg + "'";
				con.prepareStatement(query).executeUpdate();
				conNorm.prepareStatement(query).executeUpdate();
				//Obtain patient id related to the message  
				query = "SELECT entityId FROM participation WHERE actId = '" + idMsg + "'";
				ResultSet rs3 = con.prepareStatement(query).executeQuery();
				rs3.next();
				String patientId = rs3.getString(1);
				rs3.close();
				//Delete participation between the patient and the message on both database and normalized database
				query = "DELETE FROM participation WHERE actId = '" + idMsg + "'";
				con.prepareStatement(query).executeUpdate();
				conNorm.prepareStatement(query).executeUpdate();
				//Select acts related included on message
				query = "SELECT idA FROM actrelationship WHERE idB = '" + idMsg + "'";
				ResultSet rs2 = con.prepareStatement(query).executeQuery();
				//Delete information related to the previous acts on both database and normalized database
				while (rs2.next()) {
					String id = rs2.getString(1);
					query = "DELETE FROM act WHERE id = '" + id + "'";
					con.prepareStatement(query).executeUpdate();
					conNorm.prepareStatement(query).executeUpdate();
					query = "DELETE FROM actobservationvalues WHERE id = '" + id + "'";
					con.prepareStatement(query).executeUpdate();
					conNorm.prepareStatement(query).executeUpdate();
					query = "DELETE FROM actrelationship WHERE idA = '" + id + "'";
					con.prepareStatement(query).executeUpdate();
					conNorm.prepareStatement(query).executeUpdate();
					query = "DELETE FROM acttargetsitecode WHERE id = '" + id + "'";
					con.prepareStatement(query).executeUpdate();
					conNorm.prepareStatement(query).executeUpdate();
					query = "DELETE FROM actobservationinterpretationcode WHERE id = '" + id + "'";
					con.prepareStatement(query).executeUpdate();
					conNorm.prepareStatement(query).executeUpdate();
					query = "DELETE FROM actmethodcode WHERE id = '" + id + "'";
					con.prepareStatement(query).executeUpdate();
					conNorm.prepareStatement(query).executeUpdate();
					query = "DELETE FROM observation WHERE id = '" + id + "'";
					con.prepareStatement(query).executeUpdate();
					conNorm.prepareStatement(query).executeUpdate();
					query = "DELETE FROM procedures WHERE id = '" + id + "'";
					con.prepareStatement(query).executeUpdate();
					conNorm.prepareStatement(query).executeUpdate();
					query = "DELETE FROM substanceadministration WHERE id = '" + id + "'";
					con.prepareStatement(query).executeUpdate();
					conNorm.prepareStatement(query).executeUpdate();
					query = "DELETE FROM participation WHERE actId = '" + id + "'";
					con.prepareStatement(query).executeUpdate();
					conNorm.prepareStatement(query).executeUpdate();
				}

				//Select messages from deleted database in which modificationTime is later than given date to be restored
				query = "SELECT modificationTime FROM " + database + "_deleted.act WHERE id = '" + idMsg + "' AND modificationTime != '0000-00-00 00:00:00' AND modificationTime IS NOT NULL AND modificationTime <=  '" + date + "' ORDER BY modificationTime DESC";	
				ResultSet rs4 = con.prepareStatement(query).executeQuery();

				//Restore the information related to previous acts on both database and normalized database 
				//Delete the information restored 
				if (rs4.next()) {
					String modif = rs4.getString(1);

					query = "INSERT INTO act SELECT * FROM " + database + "_deleted.act WHERE act.id = '" + idMsg + "' and act.modificationTime = '" + modif + "'";
					con.prepareStatement(query).executeUpdate();
					query = "INSERT INTO act SELECT * FROM " + normalized + "_deleted.act WHERE act.id = '" + idMsg + "' and act.modificationTime = '" + modif + "'";
					conNorm.prepareStatement(query).executeUpdate();

					query = "DELETE FROM " + database + "_deleted.act WHERE id = '" + idMsg + "' and act.modificationTime = '" + modif + "'";
					con.prepareStatement(query).executeUpdate();
					query = "DELETE FROM " + normalized + "_deleted.act WHERE id = '" + idMsg + "' and act.modificationTime = '" + modif + "'";
					conNorm.prepareStatement(query).executeUpdate();

					query = "INSERT INTO participation (entityId, roleId, typeCode, actId) VALUES ('" + patientId + "', '" + patientId + "', 'PART', '" + idMsg + "')";				
					con.prepareStatement(query).executeUpdate();			
					conNorm.prepareStatement(query).executeUpdate();

					rs2.close();
					query = "SELECT idA FROM " + database + "_deleted.actrelationship WHERE idB = '" + idMsg + "' and modificationTime = '" + modif + "'";
					rs2 = con.prepareStatement(query).executeQuery();
					while (rs2.next()) {
						String id = rs2.getString(1);
						//Insert deleted acts, and deleted them from deleted database
						query = "INSERT INTO act SELECT * FROM " + database + "_deleted.act WHERE act.id = '" + id + "'";
						con.prepareStatement(query).executeUpdate();
						query = "INSERT INTO act SELECT * FROM " + normalized + "_deleted.act WHERE act.id = '" + id + "'";
						conNorm.prepareStatement(query).executeUpdate();
						query = "DELETE FROM " + database + "_deleted.act WHERE id = '" + id + "'";
						con.prepareStatement(query).executeUpdate();
						query = "DELETE FROM " + normalized + "_deleted.act WHERE id = '" + id + "'";
						conNorm.prepareStatement(query).executeUpdate();

						query = "INSERT INTO actobservationvalues SELECT * FROM " + database + "_deleted.actobservationvalues WHERE actobservationvalues.id = '" + id + "'";
						con.prepareStatement(query).executeUpdate();
						query = "INSERT INTO actobservationvalues SELECT * FROM " + normalized + "_deleted.actobservationvalues WHERE actobservationvalues.id = '" + id + "'";
						conNorm.prepareStatement(query).executeUpdate();
						query = "DELETE FROM " + database + "_deleted.actobservationvalues WHERE id = '" + id + "'";
						con.prepareStatement(query).executeUpdate();
						query = "DELETE FROM " + normalized + "_deleted.actobservationvalues WHERE id = '" + id + "'";
						conNorm.prepareStatement(query).executeUpdate();

						query = "INSERT INTO actrelationship (idA, idB, typeCode) SELECT actrelationship.idA, actrelationship.idB, actrelationship.typeCode FROM " + database + "_deleted.actrelationship WHERE actrelationship.idA = '" + id + "'";
						con.prepareStatement(query).executeUpdate();
						query = "INSERT INTO actrelationship (idA, idB, typeCode) SELECT actrelationship.idA, actrelationship.idB, actrelationship.typeCode FROM " + normalized + "_deleted.actrelationship WHERE actrelationship.idA = '" + id + "'";
						conNorm.prepareStatement(query).executeUpdate();
						query = "DELETE FROM " + database + "_deleted.actrelationship WHERE idA = '" + id + "'";
						con.prepareStatement(query).executeUpdate();
						query = "DELETE FROM " + normalized + "_deleted.actrelationship WHERE idA = '" + id + "'";
						conNorm.prepareStatement(query).executeUpdate();

						query = "INSERT INTO acttargetsitecode SELECT * FROM " + database + "_deleted.acttargetsitecode WHERE acttargetsitecode.id = '" + id + "'";
						con.prepareStatement(query).executeUpdate();
						query = "INSERT INTO acttargetsitecode SELECT * FROM " + normalized + "_deleted.acttargetsitecode WHERE acttargetsitecode.id = '" + id + "'";
						conNorm.prepareStatement(query).executeUpdate();
						query = "DELETE FROM " + database + "_deleted.acttargetsitecode WHERE id = '" + id + "'";
						con.prepareStatement(query).executeUpdate();
						query = "DELETE FROM " + normalized + "_deleted.acttargetsitecode WHERE id = '" + id + "'";
						conNorm.prepareStatement(query).executeUpdate();

						query = "INSERT INTO actobservationinterpretationcode SELECT * FROM " + database + "_deleted.actobservationinterpretationcode WHERE actobservationinterpretationcode.id = '" + id + "'";
						con.prepareStatement(query).executeUpdate();
						query = "INSERT INTO actobservationinterpretationcode SELECT * FROM " + normalized + "_deleted.actobservationinterpretationcode WHERE actobservationinterpretationcode.id = '" + id + "'";
						conNorm.prepareStatement(query).executeUpdate();
						query = "DELETE FROM " + database + "_deleted.actobservationinterpretationcode WHERE id = '" + id + "'";
						con.prepareStatement(query).executeUpdate();
						query = "DELETE FROM " + normalized + "_deleted.actobservationinterpretationcode WHERE id = '" + id + "'";
						conNorm.prepareStatement(query).executeUpdate();

						query = "INSERT INTO actmethodcode SELECT * FROM " + database + "_deleted.actmethodcode WHERE actmethodcode.id = '" + id + "'";
						con.prepareStatement(query).executeUpdate();
						query = "INSERT INTO actmethodcode SELECT * FROM " + normalized + "_deleted.actmethodcode WHERE actmethodcode.id = '" + id + "'";
						conNorm.prepareStatement(query).executeUpdate();
						query = "DELETE FROM " + database + "_deleted.actmethodcode WHERE id = '" + id + "'";
						con.prepareStatement(query).executeUpdate();
						query = "DELETE FROM " + normalized + "_deleted.actmethodcode WHERE id = '" + id + "'";
						conNorm.prepareStatement(query).executeUpdate();

						query = "INSERT INTO observation SELECT * FROM " + database + "_deleted.observation WHERE observation.id = '" + id + "'";
						con.prepareStatement(query).executeUpdate();
						query = "INSERT INTO observation SELECT * FROM " + normalized + "_deleted.observation WHERE observation.id = '" + id + "'";
						conNorm.prepareStatement(query).executeUpdate();
						query = "DELETE FROM " + database + "_deleted.observation WHERE id = '" + id + "'";
						con.prepareStatement(query).executeUpdate();
						query = "DELETE FROM " + normalized + "_deleted.observation WHERE id = '" + id + "'";
						conNorm.prepareStatement(query).executeUpdate();

						query = "INSERT INTO procedures SELECT * FROM " + database + "_deleted.procedures WHERE procedures.id = '" + id + "'";
						con.prepareStatement(query).executeUpdate();
						query = "INSERT INTO procedures SELECT * FROM " + normalized + "_deleted.procedures WHERE procedures.id = '" + id + "'";
						conNorm.prepareStatement(query).executeUpdate();
						query = "DELETE FROM " + database + "_deleted.procedures WHERE id = '" + id + "'";
						con.prepareStatement(query).executeUpdate();
						query = "DELETE FROM " + normalized + "_deleted.procedures WHERE id = '" + id + "'";
						conNorm.prepareStatement(query).executeUpdate();

						query = "INSERT INTO substanceadministration SELECT * FROM " + database + "_deleted.substanceadministration WHERE substanceadministration.id = '" + id + "'";
						con.prepareStatement(query).executeUpdate();
						query = "INSERT INTO substanceadministration SELECT * FROM " + normalized + "_deleted.substanceadministration WHERE substanceadministration.id = '" + id + "'";
						conNorm.prepareStatement(query).executeUpdate();
						query = "DELETE FROM " + database + "_deleted.substanceadministration WHERE id = '" + id + "'";
						con.prepareStatement(query).executeUpdate();
						query = "DELETE FROM " + normalized + "_deleted.substanceadministration WHERE id = '" + id + "'";
						conNorm.prepareStatement(query).executeUpdate();

						query = "INSERT INTO actprocedureapproachsitecode SELECT * FROM " + database + "_deleted.actprocedureapproachsitecode WHERE actprocedureapproachsitecode.id = '" + id + "'";
						con.prepareStatement(query).executeUpdate();
						query = "INSERT INTO actprocedureapproachsitecode SELECT * FROM " + normalized + "_deleted.actprocedureapproachsitecode WHERE actprocedureapproachsitecode.id = '" + id + "'";
						conNorm.prepareStatement(query).executeUpdate();
						query = "DELETE FROM " + database + "_deleted.actprocedureapproachsitecode WHERE id = '" + id + "'";
						con.prepareStatement(query).executeUpdate();	
						query = "DELETE FROM " + normalized + "_deleted.actprocedureapproachsitecode WHERE id = '" + id + "'";
						conNorm.prepareStatement(query).executeUpdate();

						query = "INSERT INTO exposure SELECT * FROM " + database + "_deleted.exposure WHERE exposure.id = '" + id + "'";
						con.prepareStatement(query).executeUpdate();
						query = "INSERT INTO exposure SELECT * FROM " + normalized + "_deleted.exposure WHERE exposure.id = '" + id + "'";
						conNorm.prepareStatement(query).executeUpdate();
						query = "DELETE FROM " + database + "_deleted.exposure WHERE id = '" + id + "'";
						con.prepareStatement(query).executeUpdate();
						query = "DELETE FROM " + normalized + "_deleted.exposure WHERE id = '" + id + "'";
						conNorm.prepareStatement(query).executeUpdate();

						query = "INSERT INTO participation (entityId, roleId, typeCode, actId) VALUES ('" + patientId + "', '" + patientId + "', 'PART', '" + id + "')";				
						con.prepareStatement(query).executeUpdate();			
						conNorm.prepareStatement(query).executeUpdate();

					}		
				}

				//Delete current patient whether has not acts related 
				query = "SELECT * FROM participation WHERE entityId = '" + patientId + "'";
				rs2 = con.prepareStatement(query).executeQuery();
				if (!rs2.next()) {
					query = "DELETE FROM entity WHERE id = '" + patientId + "'";
					con.prepareStatement(query).executeUpdate();
					conNorm.prepareStatement(query).executeUpdate();
					query = "DELETE FROM role WHERE entityId = '" + patientId + "'";
					con.prepareStatement(query).executeUpdate();
					conNorm.prepareStatement(query).executeUpdate();
					query = "DELETE FROM livingsubject WHERE id = '" + patientId + "'";
					con.prepareStatement(query).executeUpdate();
					conNorm.prepareStatement(query).executeUpdate();
					query = "DELETE FROM person WHERE id = '" + patientId + "'";
					con.prepareStatement(query).executeUpdate();
					conNorm.prepareStatement(query).executeUpdate();
				}
				rs2.close();
			}

			//Delete all messages from deleted databases in which modificationTime is later than the given date
			query = "SELECT id, modificationTime FROM " + database + "_deleted.act WHERE modificationTime != '0000-00-00 00:00:00' AND modificationTime IS NOT NULL AND modificationTime > '"+ date +"'";
			rs = con.prepareStatement(query).executeQuery();
			while (rs.next()) {
				String idMsg = rs.getString(1);
				String modif = rs.getString(2);
				//Delete message
				query = "DELETE FROM " + database + "_deleted.act WHERE id = '" + idMsg + "' and modificationTime = '" + modif + "'";
				con.prepareStatement(query).executeUpdate();
				query = "DELETE FROM " + normalized + "_deleted.act WHERE id = '" + idMsg + "' and modificationTime = '" + modif + "'";
				conNorm.prepareStatement(query).executeUpdate();
				//Selecting acts related to the message 
				query = "SELECT idA FROM " + database + "_deleted.actrelationship WHERE idB = '" + idMsg + "' and modificationTime = '" + modif + "'";
				ResultSet rs2 = con.prepareStatement(query).executeQuery();
				//Delete information related to previous acts
				while (rs2.next()) {
					String id = rs2.getString(1);
					query = "DELETE FROM " + database + "_deleted.act WHERE id = '" + id + "'";
					con.prepareStatement(query).executeUpdate();
					query = "DELETE FROM " + normalized + "_deleted.act WHERE id = '" + id + "'";
					conNorm.prepareStatement(query).executeUpdate();

					query = "DELETE FROM " + database + "_deleted.actobservationvalues WHERE id = '" + id + "'";
					con.prepareStatement(query).executeUpdate();
					query = "DELETE FROM " + normalized + "_deleted.actobservationvalues WHERE id = '" + id + "'";
					conNorm.prepareStatement(query).executeUpdate();

					query = "DELETE FROM " + database + "_deleted.actrelationship WHERE idA = '" + id + "'";
					con.prepareStatement(query).executeUpdate();
					query = "DELETE FROM " + normalized + "_deleted.actrelationship WHERE idA = '" + id + "'";	
					conNorm.prepareStatement(query).executeUpdate();					

					query = "DELETE FROM " + database + "_deleted.acttargetsitecode WHERE id = '" + id + "'";
					con.prepareStatement(query).executeUpdate();
					query = "DELETE FROM " + normalized + "_deleted.acttargetsitecode WHERE id = '" + id + "'";
					conNorm.prepareStatement(query).executeUpdate();

					query = "DELETE FROM " + database + "_deleted.actobservationinterpretationcode WHERE id = '" + id + "'";
					con.prepareStatement(query).executeUpdate();
					query = "DELETE FROM " + normalized + "_deleted.actobservationinterpretationcode WHERE id = '" + id + "'";
					conNorm.prepareStatement(query).executeUpdate();

					query = "DELETE FROM " + database + "_deleted.actmethodcode WHERE id = '" + id + "'";
					con.prepareStatement(query).executeUpdate();
					query = "DELETE FROM " + normalized + "_deleted.actmethodcode WHERE id = '" + id + "'";
					conNorm.prepareStatement(query).executeUpdate();

					query = "DELETE FROM " + database + "_deleted.observation WHERE id = '" + id + "'";
					con.prepareStatement(query).executeUpdate();
					query = "DELETE FROM " + normalized + "_deleted.observation WHERE id = '" + id + "'";
					conNorm.prepareStatement(query).executeUpdate();

					query = "DELETE FROM " + database + "_deleted.procedures WHERE id = '" + id + "'";
					con.prepareStatement(query).executeUpdate();
					query = "DELETE FROM " + normalized + "_deleted.procedures WHERE id = '" + id + "'";
					conNorm.prepareStatement(query).executeUpdate();

					query = "DELETE FROM " + database + "_deleted.substanceadministration WHERE id = '" + id + "'";
					con.prepareStatement(query).executeUpdate();
					query = "DELETE FROM " + normalized + "_deleted.substanceadministration WHERE id = '" + id + "'";
					conNorm.prepareStatement(query).executeUpdate();

					query = "DELETE FROM " + database + "_deleted.actprocedureapproachsitecode WHERE id = '" + id + "'";
					con.prepareStatement(query).executeUpdate();
					query = "DELETE FROM " + normalized + "_deleted.actprocedureapproachsitecode WHERE id = '" + id + "'";
					conNorm.prepareStatement(query).executeUpdate();

					query = "DELETE FROM " + database + "_deleted.exposure WHERE id = '" + id + "'";
					con.prepareStatement(query).executeUpdate();
					query = "DELETE FROM " + normalized + "_deleted.exposure WHERE id = '" + id + "'";
					conNorm.prepareStatement(query).executeUpdate();

				}	
				rs2.close();
			}
			rs.close();
			con.close();
			conNorm.close();
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}	

	}

}
