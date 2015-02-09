package CoreDataset;


public class classifiedConcept {

	private String code;
	private String label;
	private String table;
	private String attribute;
	

	public classifiedConcept() {
		super();
		this.code = new String();
		this.label = new String();
		this.table = new String();
		this.attribute = new String();
	}

	public classifiedConcept(String code, String label, String table, String attribute) {
		super();
		this.code = code;
		this.label = label;
		this.table = table;
		this.attribute = attribute;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}


}
