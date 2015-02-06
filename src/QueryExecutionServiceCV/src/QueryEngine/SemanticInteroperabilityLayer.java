package QueryEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import Config.ServiceProperties;

public class SemanticInteroperabilityLayer {

	public static String serverDirectory;
	private static String serverIndex = "WEB-INF";

	public static String configFile;

	static Util object_query;
	static String log4jConfig;
	static Logger log;
	static String database;
	static String user;
	static String pass;
	static String service_version;
	static String SNOMED_version;
	static String ETL_version;
	static String server;

	static {
		init_servers();
	}

	private static void init_servers () {
		File miDir = new File (".");
		
		//Get execution directory
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		int index = classLoader.getResource("").getFile().indexOf(serverIndex);
		serverDirectory = classLoader.getResource("").getFile().substring(0, index);
		
		try {
			configFile = serverDirectory + "config" + File.separator + "properties.config";

			service_version = ServiceProperties.getServiceVersion(configFile);
			SNOMED_version = ServiceProperties.getSNOMED_version(configFile);
			ETL_version = ServiceProperties.getETL_version(configFile);
			database = ServiceProperties.getDatabaseName(configFile);
			user = ServiceProperties.getUserDB(configFile);
			pass = ServiceProperties.getPassDB(configFile);
			server = ServiceProperties.getServer(configFile);

			//Load log4j config file
			log4jConfig = serverDirectory + ServiceProperties.getLogConfig(configFile);
			PropertyConfigurator.configure(log4jConfig);  
			//Log
			log = Logger.getLogger(SemanticInteroperabilityLayer.class); 
			object_query = new Util();
		} catch (Exception e) {
			e.printStackTrace();
		} 	
	}

	/**
	 * @see h
	 * @param query
	 * @return
	 */
	public String executeQuery (String query){

		long t0 = System.currentTimeMillis();
		log.info("[GIVEN QUERY]" + "\n---------------------------------------" + "\n" + query + "\n---------------------------------------");

		String res = "No Results";
		object_query = new Util();

		res = object_query.QueryResult(query);

		String logResult = "No Results";

		if (res.contains("<Row>") || res.contains("{"))
			logResult = "Works!";

		long t1 = System.currentTimeMillis();
		long time = t1-t0;

		log.info("[RESULT]" + "\n---------------------------------------" + "\nExecution time: " + time + " ms\n" + logResult + "\n---------------------------------------");

		return res;
	}

	public String getServiceVersion() {
		return service_version;
	}

}