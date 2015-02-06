package QueryEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class TestDataPush {

	public static void main(String[] args) {
		
	/*	String name = "normalized_integrate_patient_recruitment_tmp_4f5b7c40f1bc4c4aa80b7324940295b0";
		if (name.length() > 64)
			name = name.substring(0, 64);
		System.out.println(name + " " + name.length());*/
	
		
		long time_start, time_end;
		time_start = System.currentTimeMillis();
		
		for (int j = 1; j <= 1; j++) {
			//File file = new File("C:\\Users\\gpadr\\Documents\\Integración Norm Pipeline - Data Push\\Mensajes\\TBP\\1-100\\patient"+j+".xml");
			//File file = new File("C:\\Users\\gpadr\\Documents\\Integración Norm Pipeline - Data Push\\Mensajes\\TBP\\patientPruebas.xml");
			File file = new File("C:\\Users\\gpadr\\Documents\\Community Version\\HL7_msg1.xml");

			FileReader fr;
			BufferedReader br;
			String msg = "";
			try {
				fr = new FileReader (file);
				br = new BufferedReader(fr);
				String linea = "";
				msg = "";
				int i =0;
				try {
					while((linea = br.readLine())!=null){
						if (i==0) {
							msg=linea;
						} else {
							msg=msg+" "+ linea;
						}
						i++;				
					}
					fr.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(DataPushService.storeData(msg, "", "0"));
		}
		time_end = System.currentTimeMillis();
		System.out.println("the task has taken "+ ( time_end - time_start ) +" milliseconds");
		
		//DataPushService.erase_Data();
	}

}
