package TermBinding;

import java.util.ArrayList;

import org.openrdf.query.BindingSet;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryException;

import SesameServer.Sesame_Server;


public class TermBinding {


	public TermBinding(){}


	public ArrayList<String> getRIMAtt (String term) 
	{

		ArrayList<String> queryResult = query(term);

		ArrayList<String> ret = new ArrayList<String>();

		if (queryResult==null)
		{
			ret.add("Unknown");
			ret.add("Unknown");
		}
		else if (queryResult.size()>1)
		{
			ret.add(parseClass(resolveOverlapAtt(queryResult)));
			ret.add(parseAtt(resolveOverlapAtt(queryResult)));			
		}
		else
		{			
			ret.add(parseClass(queryResult.get(0)));
			ret.add(parseAtt(queryResult.get(0)));			
		}

		if (ret.get(0).equals("SubstanceAdministration"))
			ret.set(0, "Entity");

		if (ret.get(0).equals("Observation")&&ret.get(1).equals("code"))
			ret.set(1, "targetSiteCode");


		return ret;


	}

	public ArrayList<String> getRIMClass (String term) 
	{

		ArrayList<String> queryResult = query(term);

		ArrayList<String> ret = new ArrayList<String>();

		if (queryResult==null)
		{
			ret.add("Unknown");
			ret.add("Unknown");
		}
		else if(queryResult.size()==1)
		{
			ret.add(parseClass(queryResult.get(0)));
			ret.add(parseAtt(queryResult.get(0)));	
		}
		//PROCEDURE = PROC CODE + PROC METHODCODE + OBS METHODCODE
		else if(queryResult.size()==3)
		{
			int i=0;
			for (String string : queryResult) {
				if((parseClass(string)).equals("Procedure")&&parseAtt(string).equals("code"))
					i=i+1;
				else if((parseClass(string)).equals("Procedure")&&parseAtt(string).equals("methodCode"))
					i=i+10;
				else if ((parseClass(string)).equals("Observation")&&parseAtt(string).equals("methodCode"))
					i=i+100;				
			}
			if (i==111)
			{
				ret.add("Procedure");ret.add("code");
				ret.add("Procedure");ret.add("methodCode");
				ret.add("Observation");ret.add("methodCode");
			}
			else				
			{
				ret.add(parseClass(resolveOverlapClass(queryResult)));
				ret.add(parseAtt(resolveOverlapClass(queryResult)));
			}

		}
		//EVALUATION PROCEDURE = PROC CODE + PROC METHODCODE + OBS CODE + OBS METHODCODE
		//ADM OF SUBSTANCE = PROC CODE + PROC METHODCODE + OBS METHODCODE +SBADM CODE 
		else if(queryResult.size()==4)
		{
			int i=0;
			for (String string : queryResult) {
				if((parseClass(string)).equals("Procedure")&&parseAtt(string).equals("code"))
					i=i+1;
				else if((parseClass(string)).equals("Procedure")&&parseAtt(string).equals("methodCode"))
					i=i+10;
				else if ((parseClass(string)).equals("Observation")&&parseAtt(string).equals("methodCode"))
					i=i+100;
				else if ((parseClass(string)).equals("Observation")&&parseAtt(string).equals("code"))
					i=i+1000;
				else if ((parseClass(string)).equals("SubstanceAdministration")&&parseAtt(string).equals("code"))
					i=i+2000;
			}
			if (i==1111)
			{
				ret.add("Observation");ret.add("code");
				ret.add("Procedure");ret.add("code");
				ret.add("Procedure");ret.add("methodCode");
				ret.add("Observation");ret.add("methodCode");

			}
			else if(i==2111)
			{
				ret.add("SubstanceAdministration");ret.add("code");
				ret.add("Procedure");ret.add("code");
				ret.add("Procedure");ret.add("methodCode");
				ret.add("Observation");ret.add("methodCode");
			}
			else				
			{
				ret.add(parseClass(resolveOverlapClass(queryResult)));
				ret.add(parseAtt(resolveOverlapClass(queryResult)));
			}
		}
		else
		{			
			ret.add(parseClass(resolveOverlapClass(queryResult)));
			ret.add(parseAtt(resolveOverlapClass(queryResult)));		
		}

		return ret;


	}

	private String resolveOverlapAtt(ArrayList<String> queryResult)
	{
		if (queryResult.size()==2)
		{			
			if (parseClass(queryResult.get(0)).equals("Observation")&&parseAtt(queryResult.get(0)).equals("targetSiteCode"))
				return queryResult.get(0);
			else if (parseClass(queryResult.get(1)).equals("Observation")&&parseAtt(queryResult.get(1)).equals("methodCode"))
				return queryResult.get(1);
			else if (parseClass(queryResult.get(0)).equals("Procedure")&&parseAtt(queryResult.get(0)).equals("targetSiteCode"))
				return queryResult.get(0);
			else if (parseClass(queryResult.get(1)).equals("Procedure")&&parseAtt(queryResult.get(1)).equals("methodCode"))
				return queryResult.get(1);
			else 
				return ("#Unknown_att2");

		}
		else if(queryResult.size()==3)
		{			
			for (String string : queryResult) {
				if (parseClass(string).equals("Procedure")&&parseAtt(string).equals("methodCode"))
					return string;
				if (parseClass(string).equals("Observation")&&parseAtt(string).equals("methodCode"))
					return string;
			}
		}
		else if (queryResult.size()==4)
		{
			for (String string : queryResult) {
				if (parseClass(string).equals("Procedure")&&parseAtt(string).equals("methodCode"))
					return string;
				if (parseClass(string).equals("Observation")&&parseAtt(string).equals("methodCode"))
					return string;
			}
		}
		return ("#Unknown_att3x4");
	}

	private String resolveOverlapClass(ArrayList<String> queryResult)
	{
		if (queryResult.size() ==2)
		{
			//The more specific class of the overlap will be returned i.e. Observation code and SubstanceAdministration code instead of Procedure code

			if (parseClass(queryResult.get(0)).equals("Observation")&&parseAtt(queryResult.get(0)).equals("code"))
				return queryResult.get(0);
			else if (parseClass(queryResult.get(1)).equals("Observation")&&parseAtt(queryResult.get(1)).equals("code"))
				return queryResult.get(1);
			else if (parseClass(queryResult.get(0)).equals("SubstanceAdministration")&&parseAtt(queryResult.get(0)).equals("code"))
				return queryResult.get(0);
			else if (parseClass(queryResult.get(1)).equals("SubstanceAdministration")&&parseAtt(queryResult.get(1)).equals("code"))
				return queryResult.get(1);
			else if ((parseAtt(queryResult.get(0)).equals("targetSiteCode"))&&(parseAtt(queryResult.get(1)).equals("targetSiteCode")))
				return("#Observation_targetSiteCode");				
			else if ((parseAtt(queryResult.get(0)).equals("methodCode"))&&(parseAtt(queryResult.get(1)).equals("methodeCode")))
				return("#Observation_methodCode");
			
			else 
				return ("#Unknown_class2");
				//return ("#Observation_code");

		}
		else if(queryResult.size()==3)
		{			
			for (String string : queryResult) {
				if (parseClass(string).equals("Procedure")&&parseAtt(string).equals("code"))
					return string;
			}
		}
		else if (queryResult.size()==4)
		{
			for (String string : queryResult) {
				if (parseClass(string).equals("Observation")&&parseAtt(string).equals("code"))
					return string;
				if (parseClass(string).equals("SubstanceAdministration")&&parseAtt(string).equals("code"))
					return string;
			}
		}
		return ("#Unknown_class3x4");
	}

	private String parseClass(String string)
	{
		return string.substring(string.indexOf("#")+1,string.indexOf("_"));
	}

	private String parseAtt(String string)
	{
		return string.substring(string.indexOf("_")+1,string.length());
	}

	private ArrayList<String> query(String term){
		ArrayList<String> result = new ArrayList<String>();
		String query = "PREFIX prv:<http://purl.org/net/provenance/ns#>\n"+
				"PREFIX :<http://www.ihtsdo.org/>\n"+
				"PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>\n"+
				"PREFIX hl7:<http://test.org#>\n"+
				"PREFIX loinc:<http://www.loinc.org/>\n"+
				"PREFIX hgnc:<http://www.hgnc.org/>\n"+
				"PREFIX owl:<http://www.w3.org/2002/07/owl#>\n"+
				"PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>\n"+
				"PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
				"SELECT ?z WHERE{\n"+
				"{:SCT_"+term+" prv:containedBy ?z} UNION {loinc:"+term+" prv:containedBy ?z} UNION {hgnc:"+term+" prv:containedBy ?z}}";
		try {
			TupleQuery tupleQuery = Sesame_Server.getCon().prepareTupleQuery(QueryLanguage.SPARQL, query);

			TupleQueryResult queryResult = tupleQuery.evaluate();

			while (queryResult.hasNext()) {
				BindingSet bindingSet = queryResult.next();
				result.add(bindingSet.getValue("z").stringValue());
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

	public ArrayList<String> getCDTerm (String term) 
	{		
		return null;	
	}

}