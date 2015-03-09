package TermBinding;

/*import org.openrdf.repository.Repository;
import org.openrdf.repository.http.HTTPRepository;*/

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
//import java.io.FileWriter;

import java.sql.*;
import java.util.ArrayList;

import java.util.Map;
import java.util.HashMap;

import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryException;

import CoreDataset.CoreDatasetService;
import SesameServer.Sesame_Server;


public class TermBindingLoader {

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

	//static Repository myRepository;

	static class MapValue {
		String table;
		String attribute;
		Boolean checked;    

		public MapValue(String t, String a, Boolean c){
			table = t;
			attribute = a;
			checked = c;
		}

	}

	static Map<Long, MapValue> mp = new HashMap<Long, MapValue>();	




	private static void recOffspring (String valueP, String table, String attribute, String label,  Connection conexion, String valueS)
	{

		try {


			/////////////////////////////////////////////////////////////////////////////////

			if (mp.get(Long.parseLong(valueS))==null)
				mp.put(Long.parseLong(valueS),new MapValue(table,attribute,true));
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

				if (!(mp.get(Long.parseLong(valueS)).table.replaceAll("\\r|\\n", "").equals(table.replaceAll("\\r|\\n", "")))
						||!(mp.get(Long.parseLong(valueS)).attribute.replaceAll("\\r|\\n", "").equals(attribute.replaceAll("\\r|\\n", ""))))
				{
					System.out.println(valueS
							+"\t"+label.replaceAll("\\r|\\n", "")
							+"\t"+table+" "+attribute
							+"\t"+mp.get(Long.parseLong(valueS)).table+ " " + mp.get(Long.parseLong(valueS)).attribute);
					conflicts++;
				}

				else 
					return; 
			}

			else
				System.out.print("");
			/////////////////////////////////////////////////////////////////////////////////


			bufferedWriter.write("\n" + table+"\t"+ attribute+"\t"+valueS+"\t"+label.replaceAll("\\r|\\n", "")+"\t"+Integer.toString(depth)+"\t"+ancestry);		
			lineTermbinding++;

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

			//String sesameServer = "http://jacob.dia.fi.upm.es:8080/openrdf-sesame/repositories/LAST";
			//myRepository = new HTTPRepository(sesameServer);
			//myRepository.initialize();

			long t0 =  System.currentTimeMillis();

			DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());

			Connection conexion = DriverManager.getConnection ("jdbc:mysql://127.0.0.1/termbindingdb","root", "1234");

			Statement sTerminfo = conexion.createStatement();	            
			ResultSet rTerminfo = sTerminfo.executeQuery ("select * from terminfo");  

			bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(".//files//termbinding"),"UTF8"));
			bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(".//files//offspring"),"UTF8"));

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

				if (first==true)
				{
					bufferedWriter.write(row.replaceAll("\\r|\\n", ""));first = false;
				}
				else
					bufferedWriter.write(row);

				lineTermbinding++;

				/////////////////////////////////////////////////////////////////////////////////

				if (mp.get(Long.parseLong(rTerminfo.getString("value")))==null)	    				
					mp.put(Long.parseLong(rTerminfo.getString("value")),new MapValue(rTerminfo.getString("table"),rTerminfo.getString("attribute"),true));   				
				else if (mp.get(Long.parseLong(rTerminfo.getString("value"))).checked==true)
				{
					checks++;

					if (!(mp.get(Long.parseLong(rTerminfo.getString("value"))).table.replaceAll("\\r|\\n", "").equals(rTerminfo.getString("table").replaceAll("\\r|\\n", "")))
							||!(mp.get(Long.parseLong(rTerminfo.getString("value"))).attribute.replaceAll("\\r|\\n", "").equals(rTerminfo.getString("attribute").replaceAll("\\r|\\n", ""))))
					{
						System.out.println("->"+rTerminfo.getString("value")
								+"\t"+rTerminfo.getString("label").replaceAll("\\r|\\n", "")
								+"\t"+rTerminfo.getString("table")+" "+rTerminfo.getString("attribute")
								+"\t"+mp.get(Long.parseLong(rTerminfo.getString("value"))).table+ " " + mp.get(Long.parseLong(rTerminfo.getString("value"))).attribute);

						conflicts++;
					}	    					
				}
				else		
					System.out.print("");
				/////////////////////////////////////////////////////////////////////////////////

				String queryString = buildQuery(rTerminfo.getString("value"));    	

				ArrayList<String> queryResp = query(queryString);

				for (String string : queryResp) 
				{ 
					depth++;
					recOffspring(rTerminfo.getString("value"),rTerminfo.getString("table"),rTerminfo.getString("attribute"),string.substring(string.indexOf("x=\"")+3, string.indexOf("\"@en")),conexion,string.substring(string.indexOf("SCT_")+4, string.indexOf(";"))); 	    			
					depth--;	    			

				}         		

				/////////////////////////////////////////////////////////////////////////////////

			}         

			bufferedWriter.close();
			bufferedWriter2.close();	

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

