package CoreDataset;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.RollingFileAppender;
import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryException;

import Config.ServiceProperties;
import NormalForm.NormalFormGenerator;
import NormalForm.NormalizedExpression;
import SesameServer.Sesame_Server;
import TermBinding.TermBinding;



public class CoreDatasetService {

	public static String serverDirectory;
	public static String tomcatDirectory;
	private static String serverIndex = "WEB-INF";
	private static String tomcatIndex = "webapps";

	static Sesame_Server sesame;
	static String log4jConfig;	
	static Logger log;
	public static String configFile;
	static String database;
	static String user;
	static String pass;
	static String service_version;
	static String SNOMED_version;
	static String OWL_version;
	static String TermBinding_version;
	static String NormalForm_version;

	static {
		init_servers();
	}

	private static void init_servers () {

		//Get execution directory
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		int index = classLoader.getResource("").getFile().indexOf(serverIndex);
		serverDirectory = classLoader.getResource("").getFile().substring(0, index);
		index = classLoader.getResource("").getFile().indexOf(tomcatIndex);
		tomcatDirectory = classLoader.getResource("").getFile().substring(0, index);

		File miDir = new File (".");
		try {
			System.out.println ("Actual Directory: " + miDir.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}


		configFile = serverDirectory + "config" + File.separator + "properties.config";

		sesame = sesame.getInstance();

		service_version = ServiceProperties.getServiceVersion(configFile);
		SNOMED_version = ServiceProperties.getSNOMED_version(configFile);
		database = ServiceProperties.getDatabaseName(configFile);
		user = ServiceProperties.getUserDB(configFile);
		pass = ServiceProperties.getPassDB(configFile);
		OWL_version = ServiceProperties.getOWL_version(configFile);
		TermBinding_version = ServiceProperties.getTermBinding_version(configFile);
		NormalForm_version = ServiceProperties.getNormalForm_version(configFile);

		//LOG
		log4jConfig = serverDirectory + ServiceProperties.getLogConfig(configFile);
		PropertyConfigurator.configure(log4jConfig);  
		log = Logger.getLogger(CoreDatasetService.class); 
	}

	private void reset_servers () {
		sesame=Sesame_Server.setInstance();
	}

	/**
	 * @see h
	 * @param query
	 * @return
	 * @throws RepositoryException
	 * @throws MalformedQueryException
	 * @throws QueryEvaluationException
	 */
	public Collection<String> expandQuery (String concept){

		sesame = sesame.getInstance();

		ArrayList<String> result = new ArrayList<String>();


		try {
			result = sesame.expandedQuery (concept, sesame);
			//result.add(concept);
		} catch (RepositoryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedQueryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (QueryEvaluationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// LOG
		String logMsg = "";
		logMsg += "["+concept+":"+result.size()+"]";
		for (String string : result) {
			logMsg = logMsg +" "+string;
		}
		log.info("[RESULT] - \n" + logMsg);
		return result;
	}


	public Collection<String> getParents (String concept){

		sesame = sesame.getInstance();

		//String final_query ="";
		ArrayList<String> result = new ArrayList<String>();


		try {
			result = sesame.getParentsSesame (concept, sesame);
			//result.add(concept);
		} catch (RepositoryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedQueryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (QueryEvaluationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// LOG
		String logMsg = "";
		logMsg += "["+concept+":"+result.size()+"]";
		for (String string : result) {
			logMsg = logMsg +" "+string;
		}
		log.info("[GET_PARENTS - RESULT] - \n" + logMsg);

		return result;
	}

	/** 
	 * Returns a collection of strings that contains the codes of the fathers and grandparents of the given concept
	 * 
	 * @param term Code of the concept to bind to the CDM
	 * @return Collection of codes
	 */
	public Collection<String> getTillGranparents (String concept){

		sesame = sesame.getInstance();

		ArrayList<String> result = new ArrayList<String>();


		try {
			result = sesame.getFatherGrandfathers (concept, sesame);
			//result.add(concept);
		} catch (RepositoryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedQueryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (QueryEvaluationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// LOG
		String logMsg = "";
		logMsg += "["+concept+":"+result.size()+"]";
		for (String string : result) {
			logMsg = logMsg +" "+string;
		}
		log.info("[GET_GRANPA - RESULT] - \n" + logMsg);

		return result;
	}

	private Collection<String> executeQuery (String concept, Value binding){

		sesame = sesame.getInstance();

		Collection<String> final_query;

		final_query = sesame.executeQuery (concept, binding);


		// LOG
		log.info("[RESULT] - " +  final_query);

		return final_query;
	}

	/** 
	 * Returns an array of strings that contains the result of a query to the sesame server 
	 * 
	 * @param term Query + binding
	 * @return Array of results
	 */
	public String[] publicExecuteQuery (String concept, Value binding){
		Collection<String> result = executeQuery(concept, binding);
		return result.toArray(new String[result.size()]);
	}

	/** 
	 * Returns a collection of strings that contains the binding to the Common Data Model of the concept 
	 * specified as parameter. This collection of strings contains the binding of the normalized expression
	 * of the given concept
	 * 
	 * @param term Code of the concept to bind to the CDM
	 * @return Bindings following the pattern: "table", "attribute", "mainConcept", "table", "attribute", "normalizedConcept1", "table", "attribute", "normalizedConcept2" and so on...
	 */
	public Collection<String> CD2CDM (String term) {

		ArrayList<String> result = new ArrayList<String>();

		// Get the short normal form of a concept 		
		ArrayList<String[]> relPairs =NormalFormGenerator.getShortNormalForm(term).relationshipPairs();

		//We check the term on the terminfo
		ArrayList<String> rimAtt = new TermBinding().getRIMClass(term);

		//if the term is not directly on the terminfo we check its focus concept
		if(rimAtt.get(0).equals("Unknown"))
		{		
			//First pair {1,focusConceptCode}
			if (relPairs.get(0)[0].equals("1"))
			{
				rimAtt = new TermBinding().getRIMClass(relPairs.get(0)[1]);
				result.add(rimAtt.get(0));
				result.add(rimAtt.get(1));
				result.add(term);
			}
			else
			{
				System.err.println("Found concept without focus concept");
				return null;
			}
		}
		else
		{
			result.add(rimAtt.get(0));
			result.add(rimAtt.get(1));
			result.add(term);
		}


		relPairs.remove(0);

		for (String[] pair : relPairs) {
			rimAtt = new TermBinding().getRIMAtt(pair[0]);
			// If the attribute is included on the TermInfo, we add the value to the method Class Attribute (e.g. Attribute found "Method", the value "Biopsy" will be matched to Method_code)
			if (!rimAtt.get(0).equals("Unknown"))
			{
				result.add(rimAtt.get(0));
				result.add(rimAtt.get(1));
				result.add(pair[1]);

			}
			//When the attribute is not found on the TermInfo, the value could be found, so we check if it is included
			else
			{

				//Whenever the attribute is an "Associated morphology", we should replace the original code with the one from the value
				// of "Associated morphology", and the original code should be stored at codeOrig, and its label to the field "text" from act.
				rimAtt = new TermBinding().getRIMAtt(pair[1]);
				if (pair[0].contains("Associated morphology")||pair[0].contains("116676008"))
				{
					result.add("Observation");
					result.add("code");
					result.add(pair[1]);					
				}
				else
				{


					if (!rimAtt.get(0).equals("Unknown"))
					{
						result.add(rimAtt.get(0));
						result.add(rimAtt.get(1));
						result.add(pair[1]);
					}

					//If neither the attribute and the value are included on the TermInfo we leave it as unkown
					else
					{
						result.add(rimAtt.get(0));
						result.add(rimAtt.get(1));
						result.add(pair[1]);
					}
				}
			}

			for (int i=0;i<result.size();i=i+3)
				if (result.get(i+1).equals("methodCode")||result.get(i+1).equals("targetSiteCode"))
					result.set(i,result.get(0));


		}

		return result;
	}

	/**
	 * 
	 * 

	 * Returns a collection of strings that contains the binding to the Common Data Model of the concept 
	 * specified as parameter. The returned value contains the information and binding needed to stored
	 * the normalized concept at the Common Data Model, and all the information that can be extracted
	 * from the normalization of the concept
	 * 
	 * @param term Code of the concept to bind to the CDM
	 * @return normalizedConcept object containing the binding and information needed to stored the given concept
	 */

	public normalizedConcept CD2CDMNEW (String term) {

		//ArrayList<String> result = new ArrayList<String>();

		normalizedConcept result = new normalizedConcept();

		result.setCodeOrig(term);


		// Get the short normal form of a concept 		
		ArrayList<String[]> relPairs =NormalFormGenerator.getShortNormalForm(term).relationshipPairs();

		//We check the term on the terminfo
		ArrayList<String> rimAtt = new TermBinding().getRIMClass(term);

		//if the term is not directly on the terminfo we check its focus concept
		if(rimAtt.get(0).equals("Unknown"))
		{		
			//First pair {1,focusConceptCode}
			if (relPairs.get(0)[0].equals("1"))
			{
				rimAtt = new TermBinding().getRIMClass(relPairs.get(0)[1]);
				result.getCode().setCode(term);
				result.getCode().setLabel(NormalFormGenerator.getLabel(term));
				result.getCode().setTable(rimAtt.get(0));
				result.getCode().setAttribute(rimAtt.get(1));

				if(rimAtt.size()>2)
				{
					classifiedConcept[] alternatives = new classifiedConcept[(rimAtt.size()-2)/2];
					int c=0;
					for (int i=2;i<rimAtt.size();i=i+2)
					{
						alternatives[c].setCode(term);
						alternatives[c].setLabel(NormalFormGenerator.getLabel(term));
						alternatives[c].setTable(rimAtt.get(i));
						alternatives[c].setAttribute(rimAtt.get(i+1));
						c++;
					}

					result.setAlternatives(alternatives);
				}

			}
			else
			{
				System.err.println("Found concept without focus concept");
				return null;
			}
		}
		else
		{

			result.getCode().setCode(term);
			result.getCode().setLabel(NormalFormGenerator.getLabel(term));
			result.getCode().setTable(rimAtt.get(0));
			result.getCode().setAttribute(rimAtt.get(1));

			if(rimAtt.size()>2)
			{
				classifiedConcept[] alternatives = new classifiedConcept[(rimAtt.size()-2)/2];
				int c=0;
				for (int i=2;i<rimAtt.size();i=i+2)
				{
					alternatives[c] = new classifiedConcept();
					alternatives[c].setCode(term);
					alternatives[c].setLabel(NormalFormGenerator.getLabel(term));
					alternatives[c].setTable(rimAtt.get(i));
					alternatives[c].setAttribute(rimAtt.get(i+1));
					c++;
				}

				result.setAlternatives(alternatives);
			}
		}

		if (result.getCode().getTable().equals("Observation"))
			result.setClassCode("OBS");		
		else if (result.getCode().getTable().equals("Procedure"))
			result.setClassCode("PROC");
		else if (result.getCode().getTable().equals("SubstanceAdministration"))
			result.setClassCode("SBADM");
		else
			result.setClassCode("");

		//365853002 | Imaging finding
		if (term.equals("365853002"))
			result.setClassCode("DGIMG");
		//444269000 | Analysis using gene copy number technique
		else if (term.equals("444269000"))
			result.setClassCode("DOC");
		//185923000 | Patient entered into trial
		else if (term.equals("185923000"))
			result.setClassCode("CLNTRL");		
		//ARMCODE
		else if (term.equals("ARMCODE"))
			result.setClassCode("CLNTRLARM");
		//422549004 | Patient-related Identification code
		else if (term.equals("422549004"))
			result.setClassCode("DOC");	


		relPairs.remove(0);


		Collection<classifiedRelationship>	relationshipPair = new ArrayList<classifiedRelationship>();	
		Collection<entityRelationship> entities = new ArrayList<entityRelationship>();


		for (String[] pair : relPairs) {
			rimAtt = new TermBinding().getRIMAtt(pair[0]);
			// If the attribute is included on the TermInfo, we add the value to the method Class Attribute (e.g. Attribute found "Method", the value "Biopsy" will be matched to Method_code)
			if (!rimAtt.get(0).equals("Unknown"))
			{
				if (!rimAtt.get(0).equals("Entity"))
					relationshipPair.add(new classifiedRelationship(new Concept(pair[0], pair[2]), new classifiedConcept(pair[1], pair[3], rimAtt.get(0), rimAtt.get(1)), term));
				else
				{
					entityRelationship entity = entityClassification(pair[0]);
					entities.add(new entityRelationship(new Concept(pair[0], pair[2]), new Concept(pair[1], pair[3]), term, entity.getEntityClassCode(), entity.getParticipationTypeCode(), entity.getRoleClassCode(), entity.getRoleCode()));
				}


			}
			//When the attribute is not found on the TermInfo, the value could be found, so we check if it is included
			else
			{

				//Whenever the attribute is an "Associated morphology", we should replace the original code with the one from the value
				// of "Associated morphology", and the original code should be stored at codeOrig, and its label to the field "text" from act.
				rimAtt = new TermBinding().getRIMAtt(pair[1]);
				if (pair[0].contains("Associated morphology")||pair[0].contains("116676008"))
				{

					//We move the original act.title to the field act.text
					result.setText(result.getCode().getLabel());

					//The act.code and act.title are set to the values indicated from the Associated morphology relationship
					result.getCode().setCode(pair[1]);
					result.getCode().setLabel(pair[3]);

				}
				else
				{


					if (!rimAtt.get(0).equals("Unknown"))
					{

						if (!rimAtt.get(0).equals("Entity"))
							relationshipPair.add(new classifiedRelationship(new Concept(pair[0], pair[2]), new classifiedConcept(pair[1], pair[3], rimAtt.get(0), rimAtt.get(1)), term));
						//result.getRelationshipPair().add(new classifiedRelationship(new Concept(pair[0], pair[2]), new classifiedConcept(pair[1], pair[3], rimAtt.get(0), rimAtt.get(1)), term));
						else
						{
							entityRelationship entity = entityClassification(pair[0]);
							entities.add(new entityRelationship(new Concept(pair[0], pair[2]), new Concept(pair[1], pair[3]), term, entity.getEntityClassCode(), entity.getParticipationTypeCode(), entity.getRoleClassCode(), entity.getRoleCode()));
							//result.getEntities().add(new entityRelationship(new Concept(pair[0], pair[2]), new Concept(pair[1], pair[3]), term, entityClassCode, participationTypeCode, roleClassCode, roleCode));			
						}
					}

					//If neither the attribute and the value are included on the TermInfo we leave it as unkown
					else
					{

						relationshipPair.add(new classifiedRelationship(new Concept(pair[0], pair[2]), new classifiedConcept(pair[1], pair[3], rimAtt.get(0), rimAtt.get(1)), term));

					}
				}
			}
		}

		result.setRelationshipPair(relationshipPair.toArray(new classifiedRelationship[relationshipPair.size()]));
		result.setEntities(entities.toArray(new entityRelationship[entities.size()]));

		for (classifiedRelationship relationship : result.getRelationshipPair()) {
			if (relationship.getConcept().getAttribute().equals("methodCode")||relationship.getConcept().getAttribute().equals("targetSiteCode"))
				relationship.getConcept().setTable(result.getCode().getTable());
		}

		result.setJSON(result.toJSON());

		return result;
	}

	/**
	 * Local method that classifies different codes for relationships of SNOMED CT terminology
	 * 
	 * @param relCode Code of the relationship to be classified
	 * @return entityRelationship object that contains information about the classification of the relationship
	 */

	private entityRelationship entityClassification(String relCode)
	{

		entityRelationship entity = new entityRelationship();

		//Devices:
		//Using device; Direct Device; Using access device; Procedure device; Indirect device
		if ((relCode.equals("424226004"))||(relCode.equals("363699004"))||(relCode.equals("425391005"))||(relCode.equals("405815000"))||(relCode.equals("363710007")))
		{
			entity.setEntityClassCode("DEV");
			entity.setParticipationTypeCode("DEV");
			entity.setRoleClassCode("STOR");
			entity.setRoleCode("-");
		}
		//Active ingredients 
		// Has active ingredient;
		else if ((relCode.equals("127489000")))
		{			
			entity.setEntityClassCode("CHEM");
			entity.setParticipationTypeCode("DIR");
			entity.setRoleClassCode("ACTI");
			entity.setRoleCode("-");
		}
		//Substances
		// Direct substance; Using substance; Component
		else if ((relCode.equals("363701004"))||(relCode.equals("424361007"))||(relCode.equals("246093002")))
		{			
			entity.setEntityClassCode("CHEM");
			entity.setParticipationTypeCode("DIR");
			entity.setRoleClassCode("INGR");
			entity.setRoleCode("-");
		}
		//Specimen substances
		//Specimen substance;
		else if ((relCode.equals("370133003")))
		{			
			entity.setEntityClassCode("CHEM");
			entity.setParticipationTypeCode("DIR");
			entity.setRoleClassCode("SPEC");
			entity.setRoleCode("-");
		}
		else
		{
			entity.setEntityClassCode("-");
			entity.setParticipationTypeCode("-");
			entity.setRoleClassCode("-");
			entity.setRoleCode("-");
		}
		return entity;
	}

	/**
	 *  
	 * @param term
	 * @return
	 */
	public Collection<String> CDM2CD (String term) {

		return (new TermBinding().getCDTerm(term));
	}

	/**
	 * Local method that extracts the Normal Form of SNOMED CT from a given concept
	 * 
	 * @param term Code of the concept 
	 * @return NormalizedExpression object that contains the Normal Form of SNOMED CT
	 */
	public NormalizedExpression getShortNormalForm (String term) {
		return NormalFormGenerator.getShortNormalForm(term);		
	}

	private class CustomComparator implements Comparator<Concept> {
		public int compare(Concept o1, Concept o2) {
			return o1.getCode().compareTo(o2.getCode());
		}
	}

	/**
	 * Returns the root concept of the Concept
	 * @param term Code from which its root concept is requested
	 * @return string that specifies the root concept of the concept provided
	 */
	public String getRootConcept (String term)
	{
		ArrayList<String> rimClass = new TermBinding().getRIMClass(term);

		if ((rimClass.get(0).equals("Entity"))&&rimClass.get(1).equals("code"))
			return "Entity";
		else if ((rimClass.get(0).equals("Procedure"))&&rimClass.get(1).equals("code"))
			return "Procedure";
		else if ((rimClass.get(0).equals("SubstanceAdministration"))&&rimClass.get(1).equals("code"))
			return "SubstanceAdministration";
		else if ((rimClass.get(0).equals("Observation"))&&rimClass.get(1).equals("code"))
		{
			if (expandQuery("122869004").contains(term))
				return "Measurement";
			else
				return "Diagnosis";
		}
		else
			return "Unknown";


	}


	/**
	 * Returns the direct offspring of the given concept or concepts (performance improvement)
	 * @param terms Code or codes from which its offspring is queried
	 * @return kinshipConcepts collection that contains the offspring information of each concept requested as parameter
	 */
	public Collection<kinshipConcepts> getNextGen (String[] terms) {

		ArrayList<kinshipConcepts> result = new ArrayList<kinshipConcepts>();

		for (String term : terms) {

			kinshipConcepts rQuery = new kinshipConcepts();

			ArrayList<Concept> siblings = new ArrayList<Concept>();

			rQuery.setParent(term);

			String queryString=
					"PREFIX :<http://www.w3.org/2002/07/owl#>\n"
							+"PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>\n"
							+"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>\n"
							+"PREFIX owl:<http://www.w3.org/2002/07/owl#>\n"
							+"PREFIX loinc:<http://www.loinc.org/>\n"
							+"PREFIX hgnc:<http://www.hgnc.org/>\n"
							+"PREFIX www:<http://www.ihtsdo.org/>\n"
							+"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"

				      		  +"SELECT DISTINCT ?subClassOf ?x WHERE {{ \n"
				      		  +"?subClassOf rdfs:subClassOf www:SCT_"+term+";"
				      		  + "rdfs:label ?x} UNION {?subClassOf rdfs:subClassOf loinc:"+term+"; rdfs:label ?x}"
				      		  +" UNION {?subClassOf rdfs:subClassOf hgnc:"+term+"; rdfs:label ?x}}";



			try {

				TupleQuery tupleQuery = Sesame_Server.getCon().prepareTupleQuery(QueryLanguage.SPARQL, queryString);

				TupleQueryResult queryResult = tupleQuery.evaluate();

				while (queryResult.hasNext()) {
					BindingSet bindingSet = queryResult.next();

					Concept concept = new Concept();

					if (bindingSet.toString().contains("loinc.org"))
					{
						concept.setCode(bindingSet.toString().substring(bindingSet.toString().indexOf("org/")+4, bindingSet.toString().indexOf(";")));
						concept.setLabel(bindingSet.toString().substring(bindingSet.toString().indexOf("x=\"")+3, bindingSet.toString().indexOf("\"@en")));

					}
					else if (bindingSet.toString().contains("ihtsdo.org"))
					{
						concept.setCode(bindingSet.toString().substring(bindingSet.toString().indexOf("SCT_")+4, bindingSet.toString().indexOf(";")));
						concept.setLabel(bindingSet.toString().substring(bindingSet.toString().indexOf("x=\"")+3, bindingSet.toString().indexOf("\"@en")));
					}
					else if (bindingSet.toString().contains("hgnc.org"))
					{
						concept.setCode(bindingSet.toString().substring(bindingSet.toString().indexOf("org/")+4, bindingSet.toString().indexOf(";")));
						concept.setLabel(bindingSet.toString().substring(bindingSet.toString().indexOf("x=\"")+3, bindingSet.toString().indexOf("\"@en")));

					}

					siblings.add(concept);

				}

				queryResult.close();

				Collections.sort(siblings, new CustomComparator());

				for (Concept concept : siblings) {
					rQuery.addSibling(concept);
				}

				result.add(rQuery);

			} catch (RepositoryException e) {
				e.printStackTrace();
			} catch (MalformedQueryException e) {
				e.printStackTrace();
			} catch (QueryEvaluationException e) {
				e.printStackTrace();
			}
		}
		return result;
	}


	/**
	 * Returns the version of the service. Each time the service is revised or updated, this value will be modified
	 * @return current version numerical x.xx
	 */
	public String getServiceVersion() {
		return service_version;
	}

	/**
	 * Returns the version of the OWL. Each time the OWL of the CoreDataset is modified, this value will change. 
	 * The expected time to get a new version is each 6 month, when the IHTSDO update the SNOMED CT ontology
	 * @return current version numerical x.xx
	 */
	public String getOWLVersion() {
		return OWL_version;
	}

	/**
	 * Returns the version of the TermBinding class. Each time the class is revised or updated, this value will be modified
	 * 
	 * @return current version numerical x.xx
	 */

	public String getTermBindingVersion() {
		return TermBinding_version;
	}
	/**
	 * Returns the version of the NormalForm class. Each time the class is revised or updated, this value will be modified
	 * @return
	 */
	public String getNormalFormVersion() {
		return NormalForm_version;
	}

}