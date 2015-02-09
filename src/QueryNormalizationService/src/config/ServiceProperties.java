package config;

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
	
	public static String getTemplateDir (String path) {
		try {
			Properties prop = new Properties();
		    String fileName = path;
		    InputStream is = new FileInputStream(fileName);
	    
			prop.load(is);
			String res = prop.getProperty("template_dir");
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
