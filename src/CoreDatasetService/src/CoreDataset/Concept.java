package CoreDataset;


public class Concept {
	/**
	 * @uml.property  name="code"
	 */
	private String code;
	/**
	 * @uml.property  name="label"
	 */
	private String label;

	public Concept() {
		super();
		this.code = new String();
		this.label = new String();
	}

	public Concept(String code, String label) {
		super();
		this.code = code;
		this.label = label;
	}
	/**
	 * @return
	 * @uml.property  name="code"
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code
	 * @uml.property  name="code"
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return
	 * @uml.property  name="label"
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label
	 * @uml.property  name="label"
	 */
	public void setLabel(String label) {
		this.label = label;
	}


}
