package CoreDataset;


public class classifiedRelationship {

	private Concept relationship;
	private classifiedConcept concept;
	
	private String codeOrig;
	
	
	public classifiedRelationship()
	{
		super();
		this.relationship = new Concept();
		this.concept = new classifiedConcept();
		this.codeOrig = new String();
		
	}
	
	public classifiedRelationship(Concept relationship,
			classifiedConcept concept, String codeOrig) {
		super();
		this.relationship = relationship;
		this.concept = concept;
		this.codeOrig = codeOrig;
	}
	
	
	public Concept getRelationship() {
		return relationship;
	}
	public void setRelationship(Concept relationship) {
		this.relationship = relationship;
	}
	public classifiedConcept getConcept() {
		return concept;
	}
	public void setConcept(classifiedConcept concept) {
		this.concept = concept;
	}

	public String getCodeOrig() {
		return codeOrig;
	}

	public void setCodeOrig(String codeOrig) {
		this.codeOrig = codeOrig;
	}
	
	
}
