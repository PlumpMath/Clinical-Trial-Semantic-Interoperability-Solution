package SesameServer;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.memory.MemoryStore;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;

import Config.ServiceProperties;
import CoreDataset.CoreDatasetService;


public class Sesame_Server {



	private static Sesame_Server instance = null;

	static String database;
	static String user;
	static String pass;
	static String server;

	private static MemoryStore memStore;
	private static Repository myRepository;
	private static String SNOMED_OWL = CoreDatasetService.tomcatDirectory + ServiceProperties.getOWL_file(CoreDatasetService.configFile);
	private static RepositoryConnection con;
	private static int total_queries;

	/**
	 * Singleton
	 * @return
	 * @throws IOException
	 * @throws QueryEvaluationException
	 * @throws MalformedQueryException
	 * @throws RDFParseException
	 * @throws RepositoryException
	 */
	public static Sesame_Server getInstance(){
		if (instance == null){
			instance = new Sesame_Server();
		}

		return instance;
	}

	/**
	 * Singleton
	 * @return
	 * @throws IOException
	 * @throws QueryEvaluationException
	 * @throws MalformedQueryException
	 * @throws RDFParseException
	 * @throws RepositoryException
	 */
	public static Sesame_Server setInstance() {
		instance = null;
		instance = new Sesame_Server();

		return instance;
	}

	/**
	 * Private constructor
	 * @throws IOException
	 * @throws QueryEvaluationException
	 * @throws MalformedQueryException
	 * @throws RDFParseException
	 * @throws RepositoryException
	 */
	private Sesame_Server() {

		// Create a SailRepository
		try {

			database = ServiceProperties.getDatabaseName(CoreDatasetService.configFile);
			user = ServiceProperties.getUserDB(CoreDatasetService.configFile);
			pass = ServiceProperties.getPassDB(CoreDatasetService.configFile);
			server = ServiceProperties.getServer(CoreDatasetService.configFile);
			long t0 =  System.currentTimeMillis();
			Date actual = new Date();
			System.out.println(actual + " - ["+ServiceProperties.getLOGmessage(CoreDatasetService.configFile)+"].Creating Sesame Repository");
			Sesame_Server.myRepository = new SailRepository(new MemoryStore());
			Sesame_Server.myRepository.initialize();
			Sesame_Server.total_queries=0;

			actual = new Date();
			System.out.println(actual + " - ["+ServiceProperties.getLOGmessage(CoreDatasetService.configFile)+"].Adding SNOMED to the Sesame Repository");
			Add_XML(SNOMED_OWL);
			actual = new Date();
			System.out.println(actual + " - ["+ServiceProperties.getLOGmessage(CoreDatasetService.configFile)+"].Initializating Sesame Time: " + ( System.currentTimeMillis()-t0));
		} catch (RepositoryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void Add_XML (String path) {
		//Add RDF to the repository
		try {
			File file = new File(path);
			String baseURI = "http://example.org/example/local";
			this.con = myRepository.getConnection();
			con.add(file, baseURI, RDFFormat.RDFXML);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RDFParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public static Repository getMyRepository() {
		return myRepository;
	}

	public static void setMyRepository(Repository myRepository) {
		Sesame_Server.myRepository = myRepository;
	}

	public static RepositoryConnection getCon() {
		return con;
	}

	public static void setCon(RepositoryConnection con) {
		Sesame_Server.con = con;
	}


	public ArrayList<String> Query_Concept (String term) {
		ArrayList<String> reasoned_concepts = new ArrayList<String>();
		String queryString = "prefix rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+ "prefix rdfs:<http://www.w3.org/2000/01/rdf-schema#>\n"
				+ "prefix owl:<http://www.w3.org/2002/07/owl#>\n"
				+ "prefix xsd:<http://www.w3.org/2001/XMLSchema#>\n"
				+ "PREFIX prv:<http://purl.org/net/provenance/ns#>\n"
				+ "PREFIX sct:<http://www.ihtsdo.org/>\n"
				+ "PREFIX hl7:<http://test.org#>\n"
				+ "SELECT DISTINCT ?subClassOf" + " WHERE { "
				+"?subClassOf rdfs:subClassOf sct:" + term + ".}";

		TupleQuery tupleQuery;
		try {
			tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

			total_queries=total_queries+1;
			TupleQueryResult result = tupleQuery.evaluate();
			String bindingSet_aux = new String();
			int j=0;
			while (result.hasNext()) {
				BindingSet bindingSet = result.next();
				bindingSet_aux = bindingSet.toString();
				bindingSet_aux = bindingSet_aux.substring(bindingSet_aux.indexOf("_")+1,bindingSet_aux.indexOf("]"));
				reasoned_concepts.add(bindingSet_aux);
				j=j+1;
			}
			result.close();

		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (QueryEvaluationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reasoned_concepts;
	}
	
	

	public ArrayList<String> ReasoningConcepts (String query, ArrayList<String> concept_list, Sesame_Server sesame) throws RepositoryException, MalformedQueryException, QueryEvaluationException {
		ArrayList<String> reasoned_concepts = new ArrayList<String>();
		boolean exist = true;
		int j=0;
		while (exist) {
			reasoned_concepts = sesame.Query_Concept("SCT_"+concept_list.get(j));
			for (int i=0; i<reasoned_concepts.size(); i++) {
				boolean repeated = false;
				for (int z=0; z<concept_list.size(); z++) {
					if (reasoned_concepts.get(i).equals(concept_list.get(z))) {
						repeated=true;
					}
				}
				if (!repeated) {
					concept_list.add(reasoned_concepts.get(i));
				} 
			}
			if (j<concept_list.size()-1) {
				j=j+1;
			} else {
				exist=false;
			}

		}

		int index = query.indexOf("isAnySubclassOf");
		String any_line = query.substring(index);
		int index2 = any_line.indexOf(")");
		String aux = query.substring(index+16,index+index2);
		String query1 = query.substring(0, index);
		String identify = query1.substring(query1.lastIndexOf("?"), query1.lastIndexOf("=")-1);
		String query2 = query.substring(index2+index+1);

		String query_reasoned = "";
		ArrayList<String> terms = concept_list;
		for (int i = 0; i<terms.size();i++) {
			if (i==0) {
				query_reasoned = query_reasoned + "\""+terms.get(i) + "\" || ";
			}
			if (i==terms.size()-1) {
				query_reasoned = query_reasoned + identify + " = \"" + terms.get(i)+"\"";
			} else {
				query_reasoned = query_reasoned + identify + " = \"" + terms.get(i)+"\" || ";
			}
		}

		String res_final = query1+query_reasoned+query2;

		return concept_list;
	}

	public ArrayList<String> recursiveQueryConcept(String concept, Sesame_Server sesame)
	{
		ArrayList<String> result = new ArrayList<String>();
		for (String string : sesame.Query_Concept("SCT_"+concept)) {
			result.add(string);
			result.addAll(recursiveQueryConcept(string,sesame));
		}
		return result;		
	}
	
	



	public ArrayList<String> expandedQuery(String concept, Sesame_Server sesame) throws RepositoryException, MalformedQueryException, QueryEvaluationException, ClassNotFoundException, SQLException, InterruptedException {

		String tabla="cache";

		database = ServiceProperties.getDatabaseName(CoreDatasetService.configFile);
		user = ServiceProperties.getUserDB(CoreDatasetService.configFile);
		pass = ServiceProperties.getPassDB(CoreDatasetService.configFile);
		server = ServiceProperties.getServer(CoreDatasetService.configFile);
		
		
		ArrayList<String> result = new ArrayList<String>();

		total_queries=0;

		// connection to the database
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion = DriverManager.getConnection("jdbc:mysql://"+server+"/"+database, user, pass);
		Statement st = conexion.createStatement();

		String reasoning_type="SubClassOf";


		// First we have to query if the concept is previously expanded
		ResultSet rs = st.executeQuery("SELECT * FROM "+tabla+" WHERE concept_id='"+concept+"' AND reasoning_type='"+reasoning_type+"'");				

		// if the concept is in the cache
		if (rs.next()) {
			rs.previous();
			while (rs.next()) {
				//METER RESULTADOS EN ARRAY
				result.add(rs.getObject("expanded_concepts").toString());
			}

		}
		else {
			// IF is not previously expanded
			// In result is stored the expanded concepts			
			result.addAll(recursiveQueryConcept(concept,sesame));

			//All duplicates are deleted
			HashSet<String> hs = new HashSet<String>();
			hs.addAll(result);
			result.clear();
			result.addAll(hs);

			//Then is inserted into the cache
			int contador=0;
			String insertCache = "INSERT INTO "+tabla+" VALUES ";
			for (int i=0; i<result.size(); i++) {
				if ((contador==200)||((i+1)==result.size())){
					insertCache += "('"+concept+"', '"+result.get(i)+"', 'SubClassOf')\n";
					st.executeUpdate(insertCache);
					contador=0;
					insertCache = "INSERT INTO "+tabla+" VALUES ";
				} else {
					insertCache += "('"+concept+"', '"+result.get(i)+"', 'SubClassOf'), \n";
					contador++;
				}
			}
		}


		st.close();
		conexion.close();



		Collections.sort(result);
		return result;
	}

	
	public ArrayList<String> getFatherGrandfathers (String concept, Sesame_Server sesame) throws RepositoryException, MalformedQueryException, QueryEvaluationException, ClassNotFoundException, SQLException, InterruptedException {
		ArrayList<String> result = new ArrayList<String>();

		ArrayList<String> sons = new ArrayList<String>();
		ArrayList<String> grandsons = new ArrayList<String>();

		if (!(concept.contains(":node"))) {
			sons = sesame.getParentsQuery("SCT_"+concept);
			for (String string : sons) {
					grandsons.addAll(sesame.getParentsQuery("SCT_"+string));
			}
		}

		result.addAll(sons);
		result.addAll(grandsons);

		//All duplicates are deleted
		HashSet<String> hs = new HashSet<String>();
		hs.addAll(result);
		result.clear();
		result.addAll(hs);
		Collections.sort(result);

		return result;
	}

	
	// GET PARENTS
	public ArrayList<String> getParentsSesame(String concept, Sesame_Server sesame) throws RepositoryException, MalformedQueryException, QueryEvaluationException, ClassNotFoundException, SQLException, InterruptedException {
		ArrayList<String> result = new ArrayList<String>();
		result.addAll(recursiveQueryConceptForParents(concept,sesame));
		//All duplicates are deleted
		HashSet<String> hs = new HashSet<String>();
		hs.addAll(result);
		result.clear();
		result.addAll(hs);
		Collections.sort(result);
		return result;
	}

	// GET PARENTS
	public ArrayList<String> recursiveQueryConceptForParents(String concept, Sesame_Server sesame) {
		ArrayList<String> result = new ArrayList<String>();
		if (!(concept.contains(":node"))) {
			for (String string : sesame.getParentsQuery("SCT_"+concept)) {
				result.add(string);
				result.addAll(recursiveQueryConceptForParents(string,sesame));
			}
		}
		return result;		
	}
	
	// GET PARENTS
	public ArrayList<String> getParentsQuery (String term) {
		ArrayList<String> reasoned_concepts = new ArrayList<String>();
		String queryString = "prefix rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+ "prefix rdfs:<http://www.w3.org/2000/01/rdf-schema#>\n"
				+ "prefix owl:<http://www.w3.org/2002/07/owl#>\n"
				+ "prefix xsd:<http://www.w3.org/2001/XMLSchema#>\n"
				+ "PREFIX prv:<http://purl.org/net/provenance/ns#>\n"
				+ "PREFIX sct:<http://www.ihtsdo.org/>\n"
				+ "PREFIX hl7:<http://test.org#>\n"
				+ "SELECT DISTINCT ?subClassOf WHERE { "
				+ "sct:" + term + " rdfs:subClassOf ?subClassOf.}";

		TupleQuery tupleQuery;
		try {
			tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, queryString);

			total_queries=total_queries+1;
			TupleQueryResult result = tupleQuery.evaluate();
			String bindingSet_aux = new String();
			int j=0;
			while (result.hasNext()) {
				BindingSet bindingSet = result.next();
				bindingSet_aux = bindingSet.toString();
				bindingSet_aux = bindingSet_aux.substring(bindingSet_aux.indexOf("_")+1,bindingSet_aux.indexOf("]"));
				reasoned_concepts.add(bindingSet_aux);
				j=j+1;
			}
			result.close();

		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (QueryEvaluationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reasoned_concepts;
	}
	
	public String modifyQuery (String query, ArrayList<String> concept_list) {
		int index = query.indexOf("isAnySubclassOf");
		String any_line = query.substring(index);
		int index2 = any_line.indexOf(")");
		String aux = query.substring(index+16,index+index2);

		String query1 = query.substring(0, index);
		String identify = query1.substring(query1.lastIndexOf("?"), query1.lastIndexOf("=")-1);
		String query2 = query.substring(index2+index+1);

		String query_reasoned = "";
		ArrayList<String> terms = concept_list;
		for (int i = 0; i<terms.size();i++) {
			if (i==0) {
				query_reasoned = query_reasoned + "\""+terms.get(i) + "\" || ";
			}
			if (i==terms.size()-1) {
				query_reasoned = query_reasoned + identify + " = \"" + terms.get(i)+"\"";
			} else {
				query_reasoned = query_reasoned + identify + " = \"" + terms.get(i)+"\" || ";
			}
		}

		String res_final = query1+query_reasoned+query2;
		return res_final;
	}


	public ArrayList<String> executeQuery (String query, Value binding) {


		TupleQuery tupleQuery;
		ArrayList<String> final_query = new ArrayList<String>();

		try {
			tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, query);
			TupleQueryResult resultset = tupleQuery.evaluate();

			if (binding!=null){
				tupleQuery.clearBindings();
				tupleQuery.setBinding("s", binding);
			}


			String bindingSet_aux = new String();
			int j=0;
			while (resultset.hasNext()) {
				BindingSet bindingSet = resultset.next();
				bindingSet_aux = bindingSet.toString();
				final_query.add(bindingSet_aux);
				j=j+1;
			}
			resultset.close();
			return final_query;
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (QueryEvaluationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}

