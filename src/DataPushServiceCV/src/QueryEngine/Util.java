package QueryEngine;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import Config.ServiceProperties;


public class Util {
	
	public void truncateDB() {
		String dump = DataPushService.serverDirectory + ServiceProperties.getDumpScript(DataPushService.configFile);
		restaure_dump(dump);
	}
		
	public void restaure_dump (String path){
        try {
            String comando;
            Process proceso = Runtime.getRuntime().exec(path);
            BufferedReader lector = new BufferedReader (new InputStreamReader (proceso.getInputStream()));
            while ((comando = lector.readLine()) != null) {
                System.out.println(comando);
            }
                lector.close();
            }catch (Exception err) {
                err.printStackTrace();
            }
    }
	
}
