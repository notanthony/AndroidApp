public class Service { 
	private String name;
	private double price;
	private String[] forms;
	private String[] documents;
	
	public void Service(String name, double price, String[] forms, String[] documents){
		this.name = name;
		this.price = price;
		this.forms = forms;
		this.documents = documents;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String[] getForms() {
		return forms;
	}
	
	public String[] getDocuments() {
		return documents;
	}
	
	public void setDocuments(int index, String document) {
		documents[index] = document;
	}
	
	/*public void setForms(int index, String document) {
		forms[index] = forms;
	}*/
	
}