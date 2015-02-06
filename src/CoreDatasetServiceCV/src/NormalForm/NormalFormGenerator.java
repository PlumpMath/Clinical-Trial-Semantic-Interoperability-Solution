package NormalForm;

import java.util.ArrayList;
import java.util.Collection;

import org.openrdf.model.Value;
import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
//import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryException;
//import org.openrdf.repository.http.HTTPRepository;


import SesameServer.*;
import TermBinding.TermBinding;

public class NormalFormGenerator {


	private final static String prefixes="PREFIX prv:<http://purl.org/net/provenance/ns#> " +
			"					PREFIX :<http://www.ihtsdo.org/> " +
			"					PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> " +
			"					PREFIX hl7:<http://test.org#> " +
			"					PREFIX loinc:<http://www.loinc.org/> " +
			"					PREFIX owl:<http://www.w3.org/2002/07/owl#> " +
			"					PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> " +
			"					PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
			"					PREFIX skos:<http://www.w3.org/2004/02/skos/core#> " +
			"					PREFIX hgnc:<http://www.hgnc.org/>\n";

	/**
	 * Obtains the short normal form of a concept with the structure:
	 * 		primitiveParent -> {relationship : value}
	 * where value has the same structure. This bucle continues until all 
	 * concepts in the normalized expression are primitive
	 * @param conceptID
	 * @return Short normal form of the given concept
	 */
	public static NormalizedExpression getShortNormalForm(String conceptID){

		NormalizedExpression result=new NormalizedExpression();
		// We include the relationships of the concept in the normalized expression
		result.addRelationships(NormalFormGenerator.getConceptRelationships(conceptID));
		// We obtain the primitive parent of the concept and set the focus concept of the normalized expression
		String primPar = getPrimitiveParent("0 "+conceptID);
		primPar=primPar.substring(primPar.indexOf(" ")+1);
		result.setFocusConcept(primPar);
		result.setFocusConceptTitle(getLabel(primPar));

		// We remove the relationships that come from the concept taken as focus concept (needed for short normal form)
		// whether the primitive concept is not an entity
		if (isPrimitive(conceptID)) {
			ArrayList<String> rimClass = new TermBinding().getRIMClass(conceptID);
			if (!(rimClass.get(0).equals("Entity") && rimClass.get(1).equals("code"))) {
				result.removeRelationships(NormalFormGenerator.getConceptRelationships(primPar));
			}
		}
		return result;
	}

	public static NormalizedExpression getSnomedShortNormalForm(String conceptID){

		NormalizedExpression result=new NormalizedExpression();
		// We include the relationships of the concept in the normalized expression
		result.addRelationships(NormalFormGenerator.getConceptRelationships(conceptID));
		// We obtain the primitive parent of the concept and set the focus concept of the normalized expression
		String primPar = getPrimitiveParent("0 "+conceptID);
		primPar=primPar.substring(primPar.indexOf(" ")+1);
		result.setFocusConcept(primPar);
		result.setFocusConceptTitle(getLabel(primPar));

		// We remove the relationships that come from the concept taken as focus concept (needed for short normal form)
		// whether the primitive concept is not an entity
		if (isPrimitive(conceptID)) {
			result.removeRelationships(NormalFormGenerator.getConceptRelationships(primPar));
		}
		return result;
	}
	
	public static NormalizedExpression getSnomedLongNormalForm(String conceptID){

		NormalizedExpression result=new NormalizedExpression();
		// We include the relationships of the concept in the normalized expression
		result.addRelationships(NormalFormGenerator.getConceptRelationships(conceptID));
		// We obtain the primitive parent of the concept and set the focus concept of the normalized expression
		String primPar = getPrimitiveParent("0 "+conceptID);
		primPar=primPar.substring(primPar.indexOf(" ")+1);
		result.setFocusConcept(primPar);
		result.setFocusConceptTitle(getLabel(primPar));

		return result;
	}

	/**
	 * Get whether the given concept is primitive or not
	 * @param conceptID the concept we want to know if it's primitive or not
	 * @return True if conceptID is primitive, False otherwise
	 */
	public static boolean isPrimitive(String conceptID){
		boolean result=true;
		String queryString = "SELECT ?z WHERE {"
				+ ":SCT_"+ conceptID + " owl:equivalentClass ?z . }";
		Collection<Value> res=evaluateQuery(queryString, null);
		// We check whether the concept is equivalentClass of another, if it is then the concept isn't primitive, otherwise it is primitive
		if (res!=null) result=false;
		return result;
	}

	/**
	 * Get the nearest primitive parent of a concept. This process is recursive over the entire tree of parents and we stop the moment we find a primitive concept
	 * @param lconceptID Level of primitive parent found (needed to get the nearest primitive parent) followed by a space and 
	 * the concept from which we want to know the proximal primitive parent
	 * @return The conceptID of the nearest primitive parent
	 */
	public static String getPrimitiveParent(String lconceptID){
		// Separate the concept and the level from the given argument
		String conceptID=lconceptID.substring(lconceptID.indexOf(" ")+1);
		int level=Integer.valueOf(lconceptID.substring(0, lconceptID.indexOf(" ")));
		//Check whether the concept is primitive or not, if it is we return the given argument
		if(isPrimitive(conceptID))
			return lconceptID;
		Collection<Value> queryResult;
		// Get the list of parents
		String queryString = "SELECT ?z WHERE { "
				+ ":SCT_"+ conceptID + " rdfs:subClassOf ?z ."
				+ "}";
		queryResult=evaluateQuery(queryString, null);
		if(queryResult==null) return null;
		// Filter the given parents and get their primitives
		Collection<String> primitiveParents=new ArrayList<String>();
		for (Value value : queryResult) {
			if(!value.stringValue().contains("SCT"))continue;
			String parent=value.stringValue().substring(value.stringValue().indexOf("_")+1);
			String pp=getPrimitiveParent(level+1+" "+parent);
			if(pp==null) continue;
			primitiveParents.add(pp);
		}
		// Get nearest primitive parent
		int levelObtained=1000;
		String result=null;
		for (String string : primitiveParents) {
			int levelObtainedAux=Integer.valueOf(string.substring(0, string.indexOf(" ")));
			if (levelObtainedAux<levelObtained) {
				result=string.substring(string.indexOf(" ")+1);
				levelObtained=levelObtainedAux;
			} 
		}
		return levelObtained+" "+result;
	}

	/**
	 * Obtain the defining relationships of a given concept
	 * @param conceptID Concept from which we want to get the defining relationships
	 * @return A collection of SnomedRelationships
	 * @see SnomedRelationship
	 */
	public static SnomedRelationship[] getConceptRelationships(String conceptID){
		Collection<SnomedRelationship> result=new ArrayList<SnomedRelationship>();
		String queryString = "SELECT ?z WHERE { "
				+ "{:SCT_"+ conceptID + " rdfs:subClassOf ?y} UNION { :SCT_"+ conceptID + " owl:equivalentClass ?y} ."
				+ "?y owl:intersectionOf ?z . "
				+ "}";
		Collection<Value> queryResult=evaluateQuery(queryString,null);
		Collection<Value> auxResult = queryResult;
		// Move until the start of the relationships over the blank nodes
		while (auxResult!=null) {
			String queryString2 = "SELECT ?z WHERE { "
					+ "?s rdf:first ?y . " 
					+ "?y ?z owl:Class ."
					+ "}";
			auxResult=evaluateQuery(queryString2,(Value)queryResult.toArray()[0]);
			if (auxResult!=null) {
				queryString = "SELECT ?z WHERE { "
						+ "?s rdf:rest ?z . }"; 
				queryResult=evaluateQuery(queryString,(Value)queryResult.toArray()[0]);
			}
		}
		// Arrived to the relationships and go over all the roleGroups
		Collection<Value> moreRoleGroups;
		while (queryResult!=null){
			moreRoleGroups=queryResult;
			// Get to the relationships themselves over the roleGroups
			queryString = "SELECT ?z WHERE { "
					+ " ?s rdf:first ?y ." 
					+ " ?y owl:someValuesFrom ?t ."
					+ " ?t owl:intersectionOf ?z ."
					+ "}";
			auxResult=evaluateQuery(queryString,(Value)queryResult.toArray()[0]);
			if (auxResult==null) {
				queryString = "SELECT ?z WHERE { "
						+ " ?s rdf:first ?y ." 
						+ " ?y owl:someValuesFrom ?z ."
						+ "}";
				auxResult=evaluateQuery(queryString,(Value)queryResult.toArray()[0]);
			}
			Collection<Value> relType;
			Collection<Value> relValue;
			if(auxResult!=null&&!((Value)auxResult.toArray()[0]).stringValue().contains("SCT_")) queryResult=auxResult;
			// Go over all the relationships
			while (queryResult!=null) {
				String queryString2 = "SELECT ?z WHERE { "
						+ " ?s rdf:first ?y ." 
						+ " ?y owl:onProperty ?z ."
						+ "}";
				relType=evaluateQuery(queryString2,(Value)queryResult.toArray()[0]);
				if (relType==null) {
					queryString2 = "SELECT ?z WHERE { "
							+ " ?s owl:onProperty ?z ."
							+ "}";
					relType=evaluateQuery(queryString2,(Value)queryResult.toArray()[0]);
					if(relType==null||!((Value) relType.toArray()[0]).stringValue().contains("SCT_")) break;
				}
				String queryString3 = "SELECT ?z WHERE { "
						+ " ?s rdf:first ?y ." 
						+ " ?y owl:someValuesFrom ?z ."
						+ "}";
				relValue=evaluateQuery(queryString3,(Value)queryResult.toArray()[0]);
				if (relValue==null) {
					queryString3 = "SELECT ?z WHERE { "
							+ " ?s owl:someValuesFrom ?z ."
							+ "}";
					relValue=evaluateQuery(queryString3,(Value)queryResult.toArray()[0]);
				}
				String relationType=((Value)relType.toArray()[0]).stringValue();
				String relationValue=((Value)relValue.toArray()[0]).stringValue();
				// Add the relationship to the result array
				SnomedRelationship newRel=new SnomedRelationship(relationType.substring(relationType.indexOf("_")+1), relationValue.substring(relationValue.indexOf("_")+1));
				newRel.setRelationshipTitle(getLabel(newRel.getRelationship()));
				newRel.setRelationshipValueTitle(getLabel(newRel.getRelationshipValue().getFocusConcept()));
				result.add(newRel);
				// Check if there are more relationships
				queryString ="SELECT ?z WHERE { "
						+ "?s rdf:rest ?z ." 
						+" }";
				queryResult=evaluateQuery(queryString,(Value)queryResult.toArray()[0]);
			}
			// Check if there are more roleGroups
			queryResult=moreRoleGroups;
			queryString = "SELECT ?z WHERE { "
					+ "?s rdf:rest ?z . }"; 
			queryResult=evaluateQuery(queryString,(Value)queryResult.toArray()[0]);
		}
		SnomedRelationship[] resultArray = new SnomedRelationship[result.size()];
		return result.toArray(resultArray);
	}

	public static String getRelated(String concept){
		String queryString="SELECT DISTINCT ?related ?relation " +
				"WHERE{" +
				" 	?n owl:someValuesFrom :SCT_"+concept+". " +
				" 	?n owl:onProperty ?relation. " +
				"	?b owl:someValuesFrom*/owl:intersectionOf?/owl:someValuesFrom* ?n" +
				"	OPTIONAL{ ?a rdf:rest*/rdf:first ?b. " +
				"	OPTIONAL{ ?related rdfs:subClassOf/owl:intersectionOf* ?a.}} " +
				"	FILTER(BOUND(?c))" +
				"}";
		String result="{ ";
		String query=prefixes+queryString;
		try {
			TupleQuery tupleQuery = Sesame_Server.getCon().prepareTupleQuery(QueryLanguage.SPARQL, query);
			TupleQueryResult queryResult = tupleQuery.evaluate();
			while (queryResult.hasNext()) {
				BindingSet bindingSet = queryResult.next();
				result+=bindingSet.getValue("relation").stringValue();
				result+=": ";
				result+=bindingSet.getValue("related").stringValue();
				if(queryResult.hasNext()) result+=", ";
			}
			queryResult.close();
		} catch (RepositoryException e) {
			e.printStackTrace();
		} catch (MalformedQueryException e) {
			e.printStackTrace();
		} catch (QueryEvaluationException e) {
			e.printStackTrace();
		}
		result+=" }";
		return result;
	}

	public static String getParents(String concept) {
		String query="SELECT DISTINCT ?z WHERE { :SCT_"+concept+" rdfs:subClassOf ?z. }";
		Collection<Value> result = evaluateQuery(query, null);
		String res=" {";
		for (Value value : result) {
			if(!res.equalsIgnoreCase(" {")) res+=", ";
			res+=value.stringValue();
		}
		res+=" }";
		return res;
	}

	public static String getChildren(String concept) {
		String query="SELECT DISTINCT ?z WHERE { ?z rdfs:subClassOf :SCT_"+concept+". }";
		Collection<Value> result = evaluateQuery(query, null);
		String res=" {";
		for (Value value : result) {
			if(!res.equalsIgnoreCase(" {")) res+=", ";
			res+=value.stringValue();
		}
		res+=" }";
		return res;
	}

	public static String getLabel(String concept) {
		String query="SELECT ?z WHERE {"+
				" {:SCT_"+concept+" rdfs:label ?z.} UNION "+
				" {loinc:"+concept+" rdfs:label ?z.} UNION "+
				" {hgnc:"+concept+" rdfs:label ?z.} "+
				"}";
		Collection<Value> result = evaluateQuery(query, null);
		String res = null;
		if(result!=null)
			for (Value value : result)
				res=value.stringValue();
		return res;
	}

	/**
	 * Evaluate a query with an optional binding value. This allows us to perform a query over a blank node (usually this isn't allowed)
	 * @param queryPassed Query we want to evaluate
	 * @param value Value we want to bind to the query
	 * @return The result of the query (max 1 result)
	 */
	private static Collection<Value> evaluateQuery(String queryPassed,Value value){
		Collection<Value> result = new ArrayList<Value>();
		String query=prefixes+queryPassed;
		try {
			TupleQuery tupleQuery = Sesame_Server.getCon().prepareTupleQuery(QueryLanguage.SPARQL, query);
			tupleQuery.clearBindings();
			if(value!=null) {
				tupleQuery.setBinding("s", value);
			}
			TupleQueryResult queryResult = tupleQuery.evaluate();
			while (queryResult.hasNext()) {
				BindingSet bindingSet = queryResult.next();
				result.add(bindingSet.getValue("z"));
			}
			if (result.isEmpty()) result=null;
			queryResult.close();
		} catch (RepositoryException e) {
			e.printStackTrace();
		} catch (MalformedQueryException e) {
			e.printStackTrace();
		} catch (QueryEvaluationException e) {
			e.printStackTrace();
		}
		return result;
	}

}
