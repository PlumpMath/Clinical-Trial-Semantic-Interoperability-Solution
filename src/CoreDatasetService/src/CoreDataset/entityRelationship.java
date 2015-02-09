package CoreDataset;

public class entityRelationship {

	private Concept relationship;	
	private Concept concept;

	private String codeOrig;
	private String entityClassCode;	

	private String participationTypeCode;	

	private String roleClassCode;
	private String roleCode;




	public entityRelationship()
	{
		super();
		this.relationship = new Concept();
		this.concept = new Concept();

		this.codeOrig = new String();
		this.entityClassCode = new String();

		this.participationTypeCode = new String();

		this.roleClassCode = new String();
		this.roleCode = new String();
	}




	public entityRelationship(Concept relationship, Concept concept, String codeOrig, String entityClassCode,
			String participationTypeCode, String roleClassCode,
			String roleCode) {
		super();
		this.relationship = relationship;
		this.concept = concept;

		this.codeOrig = codeOrig;
		this.entityClassCode = entityClassCode;

		this.participationTypeCode = participationTypeCode;

		this.roleClassCode = roleClassCode;
		this.roleCode = roleCode;
	}




	public Concept getRelationship() {
		return relationship;
	}

	public void setRelationship(Concept relationship) {
		this.relationship = relationship;
	}

	public Concept getConcept() {
		return concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	public String getParticipationTypeCode() {
		return participationTypeCode;
	}

	public void setParticipationTypeCode(String participationTypeCode) {
		this.participationTypeCode = participationTypeCode;
	}

	public String getRoleClassCode() {
		return roleClassCode;
	}

	public void setRoleClassCode(String roleClassCode) {
		this.roleClassCode = roleClassCode;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getEntityClassCode() {
		return entityClassCode;
	}

	public void setEntityClassCode(String entityClassCode) {
		this.entityClassCode = entityClassCode;
	}

	public String getCodeOrig() {
		return codeOrig;
	}

	public void setCodeOrig(String codeOrig) {
		this.codeOrig = codeOrig;
	}


}


