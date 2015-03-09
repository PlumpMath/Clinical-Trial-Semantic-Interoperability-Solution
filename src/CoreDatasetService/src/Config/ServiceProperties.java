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

	public static String getOWL_version (String path) {
		try {
			Properties prop = new Properties();
			String fileName = path;
			InputStream is = new FileInputStream(fileName);

			prop.load(is);
			String res = prop.getProperty("OWL_version");
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


	public static String getTermBinding_version (String path) {
		try {
			Properties prop = new Properties();
			String fileName = path;
			InputStream is = new FileInputStream(fileName);

			prop.load(is);
			String res = prop.getProperty("TermBinding_version");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static String getNormalForm_version (String path) {
		try {
			Properties prop = new Properties();
			String fileName = path;
			InputStream is = new FileInputStream(fileName);

			prop.load(is);
			String res = prop.getProperty("NormalForm_version");
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

	public static String getOWL_file (String path) {
		try {
			Properties prop = new Properties();
			String fileName = path;
			InputStream is = new FileInputStream(fileName);

			prop.load(is);
			String res = prop.getProperty("owl_file");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
