package Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProgramProperties {
	
	public static String getProgramVersion (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("program_version");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	/*public static String getCoreDataSet_version (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("CoreDataSet_version");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}*/
	
	/*public static String getDatabaseOrig (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("database_orig");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}*/
	
/*	public static String getDatabaseDest (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("database_dest");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}*/
	
	public static String getHostDb (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("host_db");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getHostDb2 (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("host_db2");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getUserDb (String path) {
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
	
	public static String getPassDb (String path) {
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
	
	public static String getDbScript (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("db_script");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getTruncateFile (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("truncate_file");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getCreateFile (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("create_file");
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
			String res = prop.getProperty("log_file");
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
	
	public static String getHostTransl (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("host_transl");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getUserTransl (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("user_transl");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getPassTransl (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("pass_transl");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getCertificate (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("certificate");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getPassCertificate (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("pass_certificate");
			//System.out.println(res);
			return res;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getTempFile (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("temp_file");
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
