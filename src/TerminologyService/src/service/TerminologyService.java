package service;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import util.Vocabulary;
import Config.ServiceProperties;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class TerminologyService {

	public static String serverDirectory;
	private static String serverIndex = "WEB-INF";

	public static String configFile;

	static String LOG;
	static Logger log;
	static String log4jConfig;

	static String server;
	static String database; 
	static String user;
	static String pass;
	static Connection con;

	static String service_version;

	static {
		init_service();
	}



	private static void init_service () {

		//Get execution directory
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		int index = classLoader.getResource("").getFile().indexOf(serverIndex);
		serverDirectory = classLoader.getResource("").getFile().substring(0, index);

		configFile = serverDirectory + "config" + File.separator + "properties.config";

		//LOG
		LOG = ServiceProperties.getLOGmessage(configFile);
		System.out.println("["+LOG+"] - Deploying...");

		//LOG
		log4jConfig = serverDirectory + ServiceProperties.getLogConfig(configFile);
		PropertyConfigurator.configure(log4jConfig);  
		log = Logger.getLogger(TerminologyService.class); 

		service_version = ServiceProperties.getServiceVersion(configFile);
		server = ServiceProperties.getHostDb(configFile);
		user = ServiceProperties.getUserDb(configFile);
		pass = ServiceProperties.getPassDb(configFile);	
		database = "metathesaurus";

		System.out.println("["+LOG+"] - Deployed \n");

	}

	public static List<Vocabulary> getVocabulariesId () {

		try {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setServerName(server);
			dataSource.setDatabaseName(database);
			dataSource.setUser(user);
			dataSource.setPassword(pass);
			con = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}

		List<Vocabulary> vocabularies = new ArrayList<Vocabulary>();

		try {
			String query = "SELECT id, voc FROM hl7vocid WHERE id IS NOT NULL";
			ResultSet rs = con.prepareStatement(query).executeQuery();
			log.info("------------------------------------------------");
			log.info("Get Vocabularies");
			while (rs.next()) {
				Vocabulary vocabulary = new Vocabulary();
				vocabulary.setHl7Id(rs.getString(1));
				vocabulary.setVoc(rs.getString(2));
				vocabularies.add(vocabulary);
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} 
		return vocabularies;
	}

	/**
	 * Returns a code re..
	 *
	 * @param  codeOrig  ...
	 * @param  vocOrig ...
	 * @param  vocDest ...
	 * @return ...
	 */
	public static String translate(String codeOrig, String vocOrig, String vocDest) {

		try {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setServerName(server);
			dataSource.setDatabaseName(database);
			dataSource.setUser(user);
			dataSource.setPassword(pass);
			con = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}

		log.info("------------------------------------------------");

		String cui = "";
		String codeDest = "notTranslatable";

		try {
			log.info("Translate concept: " + codeOrig);
			//Fistly, check in own translation table
			String query = "SELECT code FROM translation WHERE codeOrig = '" + codeOrig + "' and codeOrigVocId = '" + vocOrig + "' and codeVocId = '" + vocDest + "'";
			ResultSet rs = con.prepareStatement(query).executeQuery();
			if (rs.next()) {
				codeDest = rs.getString(1);
				log.info("Translation found in own translation table");
			} else {
				//Query hl7vocid database to obtain the vocabularies name in metathesaurus format. Can be more than one name (splitted by ';')
				query = "SELECT voc FROM hl7vocid WHERE id = '" + vocOrig + "'";
				rs = con.prepareStatement(query).executeQuery();
				String[] vocO;
				if (rs.next()) {
					String voc = rs.getString(1);
					vocO = voc.split(";");
					for (int i = 0; i < vocO.length; i++) {
						log.info("Vocabulary Orig " + i + ": " + vocO[i]);
					}
				} else {
					log.error("The vocabulary orig is not permitted");
					return codeDest;
				}
				query = "SELECT voc FROM hl7vocid WHERE id = '" + vocDest + "'";
				rs = con.prepareStatement(query).executeQuery();
				String[] vocD;
				if (rs.next()) {
					String voc = rs.getString(1);
					vocD = voc.split(";");
					for (int i = 0; i < vocD.length; i++) {
						log.info("Vocabulary Dest " + i + ": " + vocD[i]);
					}
				} else {
					log.error("The vocabulary dest is not permitted");
					return codeDest;
				}

				//Query metathesaurus database to obtain the code orig cui
				int i = 0;
				boolean exit = false;
				while (i < vocO.length && !exit) {
					query = "SELECT CUI FROM mrconso WHERE CODE = '" + codeOrig + "' AND SAB = '" + vocO[i] + "'";
					rs = con.prepareStatement(query).executeQuery();
					if (rs.next()) {
						cui = rs.getString(1);
						log.info("CUI: " + cui);
						exit = true;
					}
					i++;
				}

				//Query metathesaurus database to obain the code orig represented in the vocabulary dest
				i = 0;
				exit = false;
				while (i < vocD.length && !exit) {
					query = "SELECT CODE FROM mrconso WHERE CUI = '" + cui + "' AND SAB = '" + vocD[i] + "' AND TS = 'P'";
					rs = con.prepareStatement(query).executeQuery();
					if (rs.next()) {
						codeDest = rs.getString(1);
						exit = true;
					}
					i++;
				}
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		log.info("Result: " + codeDest);
		return codeDest;

	}

	/*	public static void main (String[] args) {
		/*String codeOrig = "432102000"; //"432102000"; 254837009
		String vocOrig = "2.16.840.1.113883.6.96";
		String vocDest = "2.16.840.1.113883.3.26.1.1"; 
		System.out.println(translate(codeOrig, vocOrig, vocDest));*/
	//System.out.println(translate("C9135", "2.16.840.1.113883.3.26.1.1", "2.16.840.1.113883.6.96"));
	//System.out.println(translate("C48718", "2.16.840.1.113883.3.26.1.1", "2.16.840.1.113883.6.96"));
	/*	List<Vocabulary> vocabularies = new ArrayList<Vocabulary>();
		vocabularies = getVocabulariesId();
		for (Vocabulary vocabulary : vocabularies) {
			System.out.println(vocabulary.getHl7Id() + " " + vocabulary.getVoc());
		}

	}*/

}
