package queryBuilder;

import java.util.ArrayList;

public class Triples {

	/**
	 * @uml.property  name="classes"
	 * @uml.associationEnd  
	 */
	String classes;
	/**
	 * @uml.property  name="attribute"
	 * @uml.associationEnd  
	 */
	String attribute;
	/**
	 * @uml.property  name="id"
	 * @uml.associationEnd  
	 */
	String id;
	
	public Triples(String classes, String attribute, String id) {
		super();
		this.classes = classes;
		this.attribute = attribute;
		this.id = id;
	}
	
	/**
	 * @return
	 * @uml.property  name="classes"
	 */
	public String getClasses() {
		return classes;
	}
	/**
	 * @param classes
	 * @uml.property  name="classes"
	 */
	public void setClasses(String classes) {
		this.classes = classes;
	}
	/**
	 * @return
	 * @uml.property  name="attribute"
	 */
	public String getAttribute() {
		return attribute;
	}
	/**
	 * @param attribute
	 * @uml.property  name="attribute"
	 */
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	/**
	 * @return
	 * @uml.property  name="id"
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id
	 * @uml.property  name="id"
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
