package NormalForm;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Class that represents the normalized form of a concept in Snomed
 *
 */
public class NormalizedExpression {
	/**
	 * Concept ID of the focus concept in the expression
	 * @uml.property  name="focusConcept"
	 */
	private String focusConcept;

	/**
	 * Title of the focus concept
	 */
	private String focusConceptTitle;
	
	/**
	 * Collection of relationships
	 * @see  SnomedRelationship
	 * @uml.property  name="relationships"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="relationshipValue:NormalForm.SnomedRelationship"
	 */
	private Collection<SnomedRelationship> relationships;
	private SnomedRelationship[] relationshipsArray;
	/**
	 * Constructor of the normalized expression given a Focus Concept.
	 * Initializes collection of relationships
	 * @param focusConcept Focus concept of the expression
	 */
	public NormalizedExpression (String focusConcept, String focusConceptTitle){
		this.focusConcept=focusConcept;
		this.focusConceptTitle=focusConceptTitle;
		this.relationships=new ArrayList<SnomedRelationship>();
	}

	/**
	 * Constructor of the normalized expression. Initializes collection of relationships
	 */
	public NormalizedExpression (){
		this.relationships=new ArrayList<SnomedRelationship>();
	}

	/**
	 * Getter for focus concept
	 * @return  The focus concept of the expression
	 * @uml.property  name="focusConcept"
	 */
	public String getFocusConcept() {
		return focusConcept;
	}

	/**
	 * Setter for focus concept
	 * @param focusConcept  The new focus concept for the expression
	 * @uml.property  name="focusConcept"
	 */
	public void setFocusConcept(String focusConcept) {
		this.focusConcept = focusConcept;
	}
	
	/**
	 * Getter for title of focus concept
	 * @return the title of the focus concept
	 */
	public String getFocusConceptTitle() {
		return focusConceptTitle;
	}

	/**
	 * Setter for the title of the focus concept
	 * @param focusConceptTitle The new focus concept title
	 */
	public void setFocusConceptTitle(String focusConceptTitle) {
		this.focusConceptTitle = focusConceptTitle;
	}


	/**
	 * Getter for collection of relationships
	 * @return The collection of relationships of the concept
	 */
	public SnomedRelationship[] getRelationships() {
		this.relationshipsArray = new SnomedRelationship[this.relationships.size()];
		return relationships.toArray(this.relationshipsArray);
	}
	
	/**
	 * Setter for collection of relationships
	 * @param relationships The new collection of relationships of the expression
	 */
	public void setRelationships(SnomedRelationship[] relationships) {
		this.relationshipsArray = relationships;
		for (SnomedRelationship snomedRelationship : relationships) {
			this.relationships.add(snomedRelationship);
		}
	}
	
	/**
	 * Add relationship to the collection of relationships of the expression
	 * @param relationship Relationship to add to the collection
	 */
	public void addRelationship(SnomedRelationship relationship){
		if(!this.relationships.contains(relationship))
			this.relationships.add(relationship);
		
		this.relationshipsArray = new SnomedRelationship[this.relationships.size()];
		this.relationships.toArray(this.relationshipsArray);
	}

	/**
	 * Add a collection of relationships to the local collection of relationships
	 * @param relationships Collection of relationships to add
	 */
	public void addRelationships(SnomedRelationship[] relationships){
		for (SnomedRelationship snomedRelationship : relationships){ 
			addRelationship(snomedRelationship);}

		this.relationshipsArray = new SnomedRelationship[this.relationships.size()];
		this.relationships.toArray(this.relationshipsArray);
	}

	/**
	 * Remove a relationship from the collection of relationships of the expression
	 * @param relationship Relationship to remove from the collection
	 */
	public void removeRelationship(SnomedRelationship relationship){
		this.relationships.remove(relationship);
		this.relationshipsArray = new SnomedRelationship[this.relationships.size()];
		this.relationships.toArray(this.relationshipsArray);
	}

	/**
	 * Remove a collection of relationships from the local collection of relationships
	 * @param relationships Collection of relationships to remove
	 */
	public void removeRelationships(SnomedRelationship[] relationships){
		for (SnomedRelationship snomedRelationship : relationships) {
			this.relationships.remove(snomedRelationship);
		}
		this.relationshipsArray = new SnomedRelationship[this.relationships.size()];
		this.relationships.toArray(this.relationshipsArray);
	}
	
	/**
	 * Get a collection of all the focus concepts in the NormalizedExpression
	 * @return a collection including the focus concept of the expression and all the focus concepts of the relationships it includes
	 */
	public Collection<String> arrayConceptValues(){
		Collection<String> result=new ArrayList<String>();
		result.add(this.focusConcept);
		for (SnomedRelationship rel : this.relationships) 
			result.addAll(rel.getRelationshipValue().arrayConceptValues());
		return result;
	}
	
	public ArrayList<String[]> relationshipPairs(){
		ArrayList<String[]> result=new ArrayList<String[]>();
		//[0] relCode
		//[1] conceptCode
		//[2] relTitle
		//[3] conceptTitle
		result.add(new String[]{new String("1"),this.focusConcept,"",this.focusConceptTitle});
		result.addAll(relationshipPairsR());
		return result;
	}
	/**
	 * Get a collection of arrays that contain pairs of relationship-value
	 * @return a collection of arrays containing relationship type and relationship value
	 */
	public ArrayList<String[]> relationshipPairsR(){
		ArrayList<String[]> result=new ArrayList<String[]>();
		for (SnomedRelationship rel : this.relationships){
			//[0] relCode
			//[1] conceptCode
			//[2] relTitle
			//[3] conceptTitle
			result.add(new String[]{rel.getRelationship(),rel.getRelationshipValue().getFocusConcept(),rel.getRelationshipTitle(),rel.getRelationshipValue().getFocusConceptTitle()});
			result.addAll(rel.getRelationshipValue().relationshipPairsR());
			//result.add(new String[]{"0","0"});
		}
		return result;
	}

	/**
	 * Check whether a given object is equal to this normalized expression
	 * @param obj Object to compare with the normalized expression
	 * @return true if the given object is identical to the normalized expression, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this==obj) return true;
		if (!(obj instanceof NormalizedExpression)) return false;
		NormalizedExpression aux=(NormalizedExpression) obj;
		boolean b=false;
		if (!this.focusConcept.equalsIgnoreCase(aux.getFocusConcept())) return false;
		SnomedRelationship[] auxRelationships=aux.getRelationships();
		for (SnomedRelationship rel : auxRelationships) 
			if (!this.relationships.contains(rel)) return false;
		for (SnomedRelationship rel : this.relationships){
			//if (!auxRelationships.contains(rel)) return false;
			for (SnomedRelationship snomedRelationship : auxRelationships) {
				if(rel.equals(snomedRelationship)){
					b=true;
				}
			}
			if(!b){
				return false;
			}
			b=false;
		}
		return true;
	}

	/**
	 * To string method for the normalized expression
	 * @return A string with all the information stored in the normalized expression
	 */
	@Override
	public String toString() {
		String imp=this.getFocusConcept();
		if (this.getRelationships().length!=0) {
			imp+=":{";
			int i=0;
			for (SnomedRelationship snomedRelationship : this.getRelationships()) {
				imp+="\t"+snomedRelationship.toString();
				i++;
				if (this.getRelationships().length<i) imp+=",";
			}
			imp+="}";
		}
		
		return imp;
	}
	
	public String toJSON(){
		StringBuffer json=new StringBuffer("{ focusConcept: "+focusConcept+"|"+focusConceptTitle);
		int i=0, size=relationships.size();
		if(size>0) {
			json.append(",\n  relationships: [ \n\t");
			for (SnomedRelationship rel : relationships) {
				json.append(rel.getRelationship()+"|"+rel.getRelationshipTitle()+": ");
				if(rel.getRelationshipValue().relationships.size()==0) 
					json.append(rel.getRelationshipValue().getFocusConcept()+"|"+rel.getRelationshipValueTitle());
				else json.append(rel.getRelationshipValue().toJSON());
				if(++i<size) json.append(",\n\t");
			}
			json.append(" \n]");
		}
		json.append("}");
		return json.toString();
	}

}
