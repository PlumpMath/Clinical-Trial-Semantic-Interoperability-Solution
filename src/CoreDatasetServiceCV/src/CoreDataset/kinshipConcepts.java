package CoreDataset;

import java.util.ArrayList;
import java.util.Collection;

public class kinshipConcepts {

	/**
	 * @uml.property  name="parent"
	 */
	private String parent;
	/**
	 * @uml.property  name="siblings"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="CoreDataset.Concept"
	 */
	private Collection<Concept> siblings;
	
	
	public kinshipConcepts() {
		super();
		this.parent = new String();
		this.siblings = new ArrayList<Concept>();
	}
	
	//public kinshipConcepts(String parent, Collection<Concept> siblings) {
	public kinshipConcepts(String parent, Concept[] siblings) {
		super();
		this.parent = parent;
		this.siblings = new ArrayList<Concept>();
		for (Concept concept : siblings) {
			this.siblings.add(concept);
		}
	}

	/**
	 * @return
	 * @uml.property  name="parent"
	 */
	public String getParent() {
		return parent;
	}

	/**
	 * @param parent
	 * @uml.property  name="parent"
	 */
	public void setParent(String parent) {
		this.parent = parent;
	}

	//public Collection<Concept> getSiblings() {
	public Concept[] getSiblings() {
		Concept[] result = new Concept[siblings.size()];
		int i=0;
		for (Concept concept : siblings) {
			result[i++]=concept;
		}
		return result;
	}

	//public void setSiblings(Collection<Concept> siblings) {
	public void setSiblings(Concept[] siblings) {
		this.siblings = new ArrayList<Concept>();
		for (Concept concept : siblings) {
			this.siblings.add(concept);
		}
	}
	
	public void addSibling(Concept sibling) {
		this.siblings.add(sibling);
		
	}	

}
