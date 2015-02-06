package Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServiceProperties {
	
	public static String getServiceVersion (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("service_version");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getSNOMED_version (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("SNOMED_version");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getETL_version (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("ETL_version");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getFiles_Dir (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("files_dir");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getServer (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("server");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getDatabaseName (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("database");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getUserDB (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("user_db");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getPassDB (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("pass_db");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getLogFile (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("logfile");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getSolrHome (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("solrHome");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getGenericOverride (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("generic_override");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	

	
	public static String getOverrideData (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("override_data");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getOverrideETL (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("override_etl");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static String getGenericETL (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("generic_etl");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getETLData (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("etl_data");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getETLfile (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("etl_file");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getSQLScript (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("sql_script");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getDumpScript (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("dump_script");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getUri (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("uri");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getLOGmessage (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("LOG");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getConfigNormalization (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("config_normalization");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getLogConfig (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("log4j_config");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
