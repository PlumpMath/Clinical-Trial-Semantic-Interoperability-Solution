package CoreDataset;

public class normalizedConcept {

	private classifiedConcept code;
	private String codeOrig;
	
	private classifiedConcept[] alternatives;

	private String classCode;
	private String text;

	private classifiedRelationship[] relationshipPair;

	private entityRelationship[] entities;
	
	private String JSON;


	public normalizedConcept() {
		super();	
		this.code = new classifiedConcept();
		this.alternatives = null;
		this.codeOrig = new String();
		this.classCode = new String();
		this.text= new String();
		this.relationshipPair = null;
		this.entities = null;
		this.JSON = new String();
	}



	public normalizedConcept(classifiedConcept code, classifiedConcept[] alternatives, String codeOrig,
			String classCode, String text, 
			classifiedRelationship[] relationshipPair,
			entityRelationship[] entities, String JSON)
	{
		super();
		this.code = code;
		this.alternatives = alternatives;
		this.codeOrig = codeOrig;
		this.classCode = classCode;
		this.text = text;
		this.relationshipPair = relationshipPair;
		this.entities = entities;
		this.JSON = JSON;
	}
	
	public String toJSON(){
		String result="{";
		result+="\"code\": "+classifiedConceptToJSON(this.code)+", ";
		
		result+="\"alternatives\": [ ";
		int i=0;
		if(this.alternatives!=null){
			for (classifiedConcept alt : this.alternatives) {
				result+=classifiedConceptToJSON(alt);
				if(++i<this.alternatives.length) result+=", ";
			}
		}
		result+=" ], ";
		
		result+="\"codeOrig\": \""+this.codeOrig+"\", ";
		
		result+="\"classCode\": \""+this.classCode+"\", ";
		
		result+="\"relationshipPairs\": [ ";
		i=0;
		if(this.relationshipPair!=null){
			for (classifiedRelationship rel : this.relationshipPair) {
				result+=classifiedRelationshipToJSON(rel);
				if(++i<this.relationshipPair.length) result+=", ";
			}
		}
		result+=" ], ";
		
		result+="\"entities\": [ ";
		i=0;
		if(this.entities!=null){
			for (entityRelationship rel : this.entities) {
				result+=entityRelationshipToJSON(rel);
				if(++i<this.entities.length) result+=", ";
			}
		}
		result+=" ] ";
		
		result+="}";
		return result;
	
	
	}

	public classifiedConcept getCode() {
		return code;
	}

	public void setCode(classifiedConcept code) {
		this.code = code;
	}
	
	public classifiedConcept[] getAlternatives() {
		return alternatives;
	}


	public void setAlternatives(classifiedConcept[] alternatives) {
		this.alternatives = alternatives;
	}

	public String getCodeOrig() {
		return codeOrig;
	}

	public void setCodeOrig(String codeOrig) {
		this.codeOrig = codeOrig;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public classifiedRelationship[] getRelationshipPair() {
		return relationshipPair;
	}


	public void setRelationshipPair(classifiedRelationship[] relationshipPair) {
		this.relationshipPair = relationshipPair;
	}


	public entityRelationship[] getEntities() {
		return entities;
	}


	public void setEntities(entityRelationship[] entities) {
		this.entities = entities;
	}

	
	private static String conceptToJSON(Concept concept){
		String result="{";
		result+="\"code\": \""+concept.getCode()+"\", ";
		result+="\"label\": \""+concept.getLabel()+"\" ";
		result+="}";
		return result;
	}
	
	private static String classifiedConceptToJSON(classifiedConcept concept){
		String result="{";
		result+="\"code\": \""+concept.getCode()+"\", ";
		result+="\"label\": \""+concept.getLabel()+"\", ";
		result+="\"table\": \""+concept.getTable()+"\", ";
		result+="\"attribute\": \""+concept.getAttribute()+"\" ";
		result+="}";
		return result;
	}
	
	
	private static String classifiedRelationshipToJSON(classifiedRelationship rel){
		String result="{";
		result+="\"relationship\": "+conceptToJSON(rel.getRelationship())+", ";
		result+="\"concept\": "+classifiedConceptToJSON(rel.getConcept())+", ";
		result+="\"codeOrig\": \""+rel.getCodeOrig()+"\" ";
		result+="}";
		return result;
	}
	
	private static String entityRelationshipToJSON(entityRelationship rel){
		String result="{";
		result+="\"relationship\": "+conceptToJSON(rel.getRelationship())+", ";
		result+="\"concept\": "+conceptToJSON(rel.getConcept())+", ";
		result+="\"codeOrig\": \""+rel.getCodeOrig()+"\", ";
		result+="\"participationTypeCode\": \""+rel.getParticipationTypeCode()+"\", ";
		result+="\"roleClassCode\": \""+rel.getRoleClassCode()+"\", ";
		result+="\"roleCode\": \""+rel.getRoleCode()+"\", ";
		result+="\"entityClassCode\": \""+rel.getEntityClassCode()+"\" ";
		result+="}";
		return result;
	}


	public String getJSON() {
		return JSON;
	}


	public void setJSON(String jSON) {
		JSON = jSON;
	}
	


}
