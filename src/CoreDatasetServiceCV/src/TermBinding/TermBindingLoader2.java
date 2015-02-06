package TermBinding;

/*import org.openrdf.repository.Repository;
import org.openrdf.repository.http.HTTPRepository;*/

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
//import java.io.FileWriter;

import java.sql.*;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;

import CoreDataset.CoreDatasetService;
import SesameServer.Sesame_Server;


public class TermBindingLoader2 {

	static int depth=1;
	static int lineTermbinding=0;
	static int lineOffspring=0;
	static int conflicts=0;
	static int checks = 0;
	static boolean first = true;
	static boolean first2 = true;
	static String ancestry = new String();

	static BufferedWriter bufferedWriter;
	static BufferedWriter bufferedWriter2;

	static BufferedWriter bufferedWriterAutocomplete;
	static BufferedWriter bufferedWriterSynonyms;


	static class MapValue {
		ArrayList<Classification> clasificaciones =  new ArrayList<Classification>();
		Boolean checked;  
		String label = new String();
		String text = new String();

		public MapValue(String t, String a, Boolean c, String label, String text){
			clasificaciones.add(new Classification(t.replaceAll("\\r|\\n", ""),a.replaceAll("\\r|\\n", "")));
			checked = c;
			this.label = label;
			this.text = text;
		}

	}

	static class Classification{
		String table = new String();
		String atribute = new String();		
		public Classification(String t, String a){
			table = t;
			atribute = a;
		}
	}

	static Map<Long, MapValue> mp = new HashMap<Long, MapValue>();
	static Map<String, String> terminfo = new HashMap<String, String>();


	private static void recOffspring (String valueP, String table, String attribute, String label,  Connection conexion, String valueS)
	{

		try {


			/////////////////////////////////////////////////////////////////////////////////

			if (mp.get(Long.parseLong(valueS))==null)
				mp.put(Long.parseLong(valueS),new MapValue(table,attribute,true, label, getText(valueS,label)));
			else if (mp.get(Long.parseLong(valueS)).checked==true)
			{

				checks++;

				String row="\n"+valueP+"\t"+valueS+"\t"+label;
				if (first2==true)
				{
					bufferedWriter2.write(row.replaceAll("\\r|\\n", ""));first2 = false;
				}
				else
					bufferedWriter2.write(row);

				lineOffspring++;
			
				if(!contiene(mp.get(Long.parseLong(valueS)).clasificaciones,table.replaceAll("\\r|\\n", ""),attribute.replaceAll("\\r|\\n", "")))
				{
					conflicts++;
					mp.get(Long.parseLong(valueS)).clasificaciones.add(new Classification(table.replaceAll("\\r|\\n", ""),attribute.replaceAll("\\r|\\n", "")));
				}
				else
					return; 
			}
			else
				System.out.print("");
			/////////////////////////////////////////////////////////////////////////////////


			bufferedWriter.write("\n" + table+"\t"+ attribute+"\t"+valueS+"\t"+label.replaceAll("\\r|\\n", "")+"\t"+Integer.toString(depth)+"\t"+ancestry);		
			lineTermbinding++;

			bufferedWriterAutocomplete.write("\n" +valueS+"\t"+ label.replaceAll("\\r|\\n", "") +"\t"+mp.get(Long.parseLong(valueS)).text +"\t"+ancestry+"\t"+terminfo.get(ancestry)+"\t"+getRoot(table, attribute)+"\t"+"SNOMED CT");	

			String row="\n"+valueP+"\t"+valueS+"\t"+label.replaceAll("\\r|\\n", "");
			if (first2==true)
			{
				bufferedWriter2.write(row.replaceAll("\\r|\\n", ""));first2 = false;
			}
			else
				bufferedWriter2.write(row);

			lineOffspring++;


			String queryString = buildQuery(valueS);    	

			ArrayList<String> queryResp = query(queryString);

			for (String string : queryResp) 
			{   
				depth++;
				recOffspring(valueS,table,attribute,string.substring(string.indexOf("x=\"")+3, string.indexOf("\"@en")),conexion,string.substring(string.indexOf("SCT_")+4, string.indexOf(";")));
				depth--;	    			

			}

		}
		catch (Exception e) {
			System.out.println(valueS+" "+label);
			e.printStackTrace();
		}	
	}

	private static boolean contiene (ArrayList<Classification> clasificaciones, String tabla, String atributo)
	{
		boolean result = false;
		for (Classification classification : clasificaciones)
			result = result || (classification.table.equals(tabla)&&classification.atribute.equals(atributo));

		return result;
	}

	private static String clean(String s) {  
		s=s.replaceAll("[\\r\\n\\,\\\"\\'\\)\\(\\/\\  ]+", " ");
		return new LinkedHashSet<String>(Arrays.asList(s.split(" "))).toString().replaceAll("[\\[\\]]+", "").replaceAll("[\\,\\  ]+", " ");
	}

	private static String getRoot(String c, String a)
	{
		String result = new String();

		if (c.equals("Entity") && a.equals("code"))
			result = "Entity";
		else if(c.equals("Observation") && a.equals("code"))
			result="Observation";
		else if(c.equals("Procedure") && a.equals("code"))
			result="Procedure";
		else if(c.equals("SubstanceAdministration") && a.equals("code"))
			result="SBADM";
		else if(c.equals("Observation") && a.equals("methodCode"))
			result="Method";
		else if(c.equals("Procedure") && a.equals("methodCode"))
			result="Method";
		else if(c.equals("SubstanceAdministration") && a.equals("methodCode"))
			result="Method";
		else if(c.equals("Observation") && a.equals("targetSiteCode"))
			result="TargetSite";
		else if(c.equals("Procedure") && a.equals("targetSiteCode"))
			result="TargetSite";
		else if(c.equals("SubstanceAdministration") && a.equals("targetSiteCode"))
			result="TargetSite";
		else if(c.equals("Procedure") && a.equals("approachSiteCode"))
			result="ApproachSite";
		else
			result="Error";



		return result;
	}

	private static String buildQuery(String value)
	{

		String queryString=
				"PREFIX :<http://www.w3.org/2002/07/owl#>\n"
						+"PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>\n"
						+"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>\n"
						+"PREFIX owl:<http://www.w3.org/2002/07/owl#>\n"
						+"PREFIX www:<http://www.ihtsdo.org/>\n"
						+"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"

			      		  +"SELECT DISTINCT ?subClassOf ?x WHERE { \n"
			      		  +"?subClassOf rdfs:subClassOf www:SCT_"+value+";"
			      		  + "rdfs:label ?x .}";

		return queryString;

	}

	private static String buildQuerySynonyms(String value)
	{

		String queryString= "PREFIX prv:<http://purl.org/net/provenance/ns#>\n"
				+"PREFIX :<http://www.ihtsdo.org/>\n"
				+"PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>\n"
				+"PREFIX hl7:<http://test.org#>\n"
				+"PREFIX loinc:<http://www.loinc.org/>\n"
				+"PREFIX owl:<http://www.w3.org/2002/07/owl#>\n"
				+"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>\n"
				+"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+"PREFIX skos:<http://www.w3.org/2004/02/skos/core#>\n"
				+"PREFIX hgnc:<http://www.hgnc.org/>\n"

						+"SELECT ?z WHERE { \n"
						+"	 {:SCT_"+value+" skos:altLabel ?z.}}";

		return queryString;

	}

	private static String getText(String code, String label)
	{
		String text = new String();
		String queryString = buildQuerySynonyms(code);    	

		ArrayList<String> queryResp = query(queryString);
		try {
			bufferedWriterSynonyms.write(code+"\t"+label+"\n");

			for (String string : queryResp) {
				//("[\\r\\n\\,\\\"\\'\\)\\(\\/\\  ]+", " ")
				text = text + string.replaceAll("\\[z=\"", "").replaceAll("\"@en\\]", "") + " ";
				bufferedWriterSynonyms.write(code+"\t"+string.replaceAll("\\[z=\"", "").replaceAll("\"@en\\]", "")+"\n");
			}
			text=label +" "+ text;
			text = clean(text);

			return text;
		} catch (IOException e) {
			e.printStackTrace();
			return text;
		}

	}

	private static ArrayList<String> query(String query){

		ArrayList<String> rQuery = new ArrayList<String>();

		try {

			TupleQuery tupleQuery = Sesame_Server.getCon().prepareTupleQuery(QueryLanguage.SPARQL, query);
			//TupleQuery tupleQuery = myRepository.getConnection().prepareTupleQuery(QueryLanguage.SPARQL, query);


			TupleQueryResult queryResult = tupleQuery.evaluate();

			while (queryResult.hasNext()) {
				BindingSet bindingSet = queryResult.next();
				rQuery.add(bindingSet.toString());
			}

			queryResult.close();		


		} catch (RepositoryException e) {
			e.printStackTrace();
		} catch (MalformedQueryException e) {
			e.printStackTrace();
		} catch (QueryEvaluationException e) {
			e.printStackTrace();
		}

		return rQuery;
	}


	public static void main(String[] args){

		try
		{
			CoreDatasetService st = new CoreDatasetService();
			
			long t0 =  System.currentTimeMillis();

			DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());

			Connection conexion = DriverManager.getConnection ("jdbc:mysql://127.0.0.1/termbindingdb","root", "1234");

			Statement sTerminfo = conexion.createStatement();	            
			ResultSet rTerminfo = sTerminfo.executeQuery ("select value,label from terminfo");  
			while (rTerminfo.next())
			{
				terminfo.put(rTerminfo.getString("value"), rTerminfo.getString("label"));
			}
			rTerminfo = sTerminfo.executeQuery ("select * from terminfo"); 

			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(".//files//termbinding"),"UTF8"));
			bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(".//files//offspring"),"UTF8"));
			bufferedWriterAutocomplete = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(".//files//autocomplete"),"UTF8"));
			bufferedWriterSynonyms = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(".//files//synonyms"),"UTF8"));
			
			while (rTerminfo.next())
			{
				depth=1;
				ancestry=rTerminfo.getString("value");

				String row = ("\n"+rTerminfo.getString("table")+"\t"
						+ rTerminfo.getString("attribute")+"\t"
						+rTerminfo.getString("value")+"\t"
						+rTerminfo.getString("label").replaceAll("\\r|\\n", "")+"\t"
						+Integer.toString(depth)+"\t"
						+ancestry);


				if (mp.get(Long.parseLong(rTerminfo.getString("value")))==null)	    				
					mp.put(Long.parseLong(rTerminfo.getString("value")),new MapValue(rTerminfo.getString("table"),rTerminfo.getString("attribute"),true,rTerminfo.getString("label"),getText(rTerminfo.getString("value"),rTerminfo.getString("label"))));   				
				else if (mp.get(Long.parseLong(rTerminfo.getString("value"))).checked==true)
				{
					checks++;

					
					if(!contiene(mp.get(Long.parseLong(rTerminfo.getString("value"))).clasificaciones,rTerminfo.getString("table").replaceAll("\\r|\\n", ""),rTerminfo.getString("attribute").replaceAll("\\r|\\n", "")))
					{
						System.out.println("->"+rTerminfo.getString("value")
								+"\t"+rTerminfo.getString("label").replaceAll("\\r|\\n", "")
								+"\t"+rTerminfo.getString("table")+" "+rTerminfo.getString("attribute")
								+"\t"+mp.get(Long.parseLong(rTerminfo.getString("value"))).clasificaciones.get(0).table+ " " + mp.get(Long.parseLong(rTerminfo.getString("value"))).clasificaciones.get(0).atribute);

						conflicts++;
						mp.get(Long.parseLong(rTerminfo.getString("value"))).clasificaciones.add(new Classification(rTerminfo.getString("table").replaceAll("\\r|\\n", ""), rTerminfo.getString("attribute").replaceAll("\\r|\\n", "")));
					}	    					
				}
				else		
					System.out.print("");

				if (first==true)
				{
					bufferedWriter.write(row.replaceAll("\\r|\\n", ""));first = false;
					bufferedWriterAutocomplete.write(rTerminfo.getString("value")+"\t"+ rTerminfo.getString("label").replaceAll("\\r|\\n", "") +"\t"+mp.get(Long.parseLong(rTerminfo.getString("value"))).text +"\t"+ancestry+"\t"+terminfo.get(ancestry)+"\t"+getRoot(rTerminfo.getString("table"), rTerminfo.getString("attribute"))+"\t"+"SNOMED CT");
				}
				else{
					bufferedWriter.write(row);
					bufferedWriterAutocomplete.write("\n" +rTerminfo.getString("value")+"\t"+ rTerminfo.getString("label").replaceAll("\\r|\\n", "") +"\t"+mp.get(Long.parseLong(rTerminfo.getString("value"))).text +"\t"+ancestry+"\t"+terminfo.get(ancestry)+"\t"+getRoot(rTerminfo.getString("table"), rTerminfo.getString("attribute"))+"\t"+"SNOMED CT");
				}

				lineTermbinding++;

				/////////////////////////////////////////////////////////////////////////////////

				String queryString = buildQuery(rTerminfo.getString("value"));    	

				ArrayList<String> queryResp = query(queryString);

				for (String string : queryResp) 
				{
					//System.out.println(string);     
					depth++;
					recOffspring(rTerminfo.getString("value"),rTerminfo.getString("table"),rTerminfo.getString("attribute"),string.substring(string.indexOf("x=\"")+3, string.indexOf("\"@en")),conexion,string.substring(string.indexOf("SCT_")+4, string.indexOf(";"))); 	    			
					depth--;	    			

				}         		

				/////////////////////////////////////////////////////////////////////////////////

			}         

			/////Genes of SNOMED CT
			// We expand all the concepts of 67271001	Gene (substance) from SNOMED CT
			bufferedWriterAutocomplete.write("\n" +"67271001"+"\t"+mp.get(Long.parseLong("67271001")).label+"\t"+mp.get(Long.parseLong("67271001")).text +"\t"+"67271001"+"\t"+mp.get(Long.parseLong("67271001")).label+"\t"+"Gene"+"\t"+"SNOMED CT");
			for (String string :  query(buildQuery("67271001"))) 
				bufferedWriterAutocomplete.write("\n" +string.substring(string.indexOf("SCT_")+4, string.indexOf(";"))+"\t"+string.substring(string.indexOf("x=\"")+3, string.indexOf("\"@en"))+"\t"+mp.get(Long.parseLong(string.substring(string.indexOf("SCT_")+4, string.indexOf(";")))).text +"\t"+"67271001"+"\t"+"Gene"+"\t"+"Gene"+"\t"+"SNOMED CT");		

			//LOINC concepts  SCT_000000051 
			for (String string :  query(buildQuery("000000051"))) 
				bufferedWriterAutocomplete.write("\n" +string.substring(string.indexOf("org/")+4, string.indexOf(";"))+"\t"+string.substring(string.indexOf("x=\"")+3, string.indexOf("\"@en"))+"\t"+string.substring(string.indexOf("x=\"")+3, string.indexOf("\"@en"))+"\t"+"LOINC"+"\t"+"LOINC"+"\t"+"Observation"+"\t"+"LOINC");		
			
			//HGNC concepts SCT_000000061 
			for (String string :  query(buildQuery("000000061"))) 
				bufferedWriterAutocomplete.write("\n" +string.substring(string.indexOf("org/")+4, string.indexOf(";"))+"\t"+string.substring(string.indexOf("x=\"")+3, string.indexOf("\"@en"))+"\t"+string.substring(string.indexOf("x=\"")+3, string.indexOf("\"@en"))+"\t"+"HGNC"+"\t"+"HGNC"+"\t"+"Gene"+"\t"+"HGNC");		


			bufferedWriter.close();
			bufferedWriter2.close();
			bufferedWriterAutocomplete.close();
			bufferedWriterSynonyms.close();

			conexion.close();

			System.out.println("Elementos insertados Termbinding:"+lineTermbinding);
			System.out.println("Elementos insertados Offspring:"+lineOffspring);
			System.out.println("Elementos checked:"+checks);
			System.out.println("Elementos conflictivos:"+conflicts);
			System.out.println("Tiempo empleado "+( System.currentTimeMillis()-t0)/1000+"s");	
			
		}       


		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}

