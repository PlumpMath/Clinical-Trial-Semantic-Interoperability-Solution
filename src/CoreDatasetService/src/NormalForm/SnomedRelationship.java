package NormalForm;

public class SnomedRelationship {


	/**
	 * ID of the relationship type
	 * @uml.property  name="relationship"
	 */

	private String relationship;

	/**
	 * Normalized Expression that forms the relationship value
	 * @see  NormalizedExpression
	 * @uml.property  name="relationshipValue"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="relationships:NormalForm.NormalizedExpression"
	 */
	private NormalizedExpression relationshipValue;

	private String relationshipTitle;
	
	private String relationshipValueTitle;
	
	public SnomedRelationship(String relationship,String relValue){
		this.relationship=relationship;
		this.relationshipValue=NormalFormGenerator.getShortNormalForm(relValue);
	}

	/**
	 * Getter for the type of relationship
	 * @return  the type of relationship
	 * @uml.property  name="relationship"
	 */
	public String getRelationship() {
		return relationship;
	}


	/**
	 * Setter for the type of relationship
	 * @param relationship  the new type of relationship
	 * @uml.property  name="relationship"
	 */
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}


	/**
	 * Getter for the normalized relationship value
	 * @return  the normalized relationship value
	 * @uml.property  name="relationshipValue"
	 */
	public NormalizedExpression getRelationshipValue() {
		return relationshipValue;
	}


	/**
	 * Setter for the normalized relationship value
	 * @param relationshipValue  the normalized relationship value
	 * @uml.property  name="relationshipValue"
	 */
	public void setRelationshipValue(NormalizedExpression relationshipValue) {
		this.relationshipValue = relationshipValue;
	}

	public String getRelationshipTitle() {
		return relationshipTitle;
	}

	public void setRelationshipTitle(String relationshipTitle) {
		this.relationshipTitle = relationshipTitle;
	}

	public String getRelationshipValueTitle() {
		return relationshipValueTitle;
	}

	public void setRelationshipValueTitle(String relationshipValueTitle) {
		this.relationshipValueTitle = relationshipValueTitle;
	}

	@Override
	public boolean equals(Object obj) {
		if (this==obj) return true;
		if (!(obj instanceof SnomedRelationship)) return false;
		SnomedRelationship aux=(SnomedRelationship) obj;
		if (this.relationship.equalsIgnoreCase(aux.getRelationship())&&this.relationshipValue.equals(aux.getRelationshipValue())) 
			return true;
		else
			return false;
	}
	
	@Override
	public String toString() {
		return "\trelType = "+this.getRelationship()+"\t relValue = {\n"+this.getRelationshipValue().toString().replace("\n", "\n\t")+ "\n\t}";
	}
}
