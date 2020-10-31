package com.example.servicenovigrad;
public class Service {
	private String id;
	private String name;
	private double price;
	private String[] forms;
	private String[] documents;

	public Service() {

	}

	public Service(String id, String name, double price, String[] forms, String[] documents){
		this.id = id;
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
	
	public void setForms(int index, String form) {
		forms[index] = form;
	}
	
	public String toString(){
		return name;
	}
	
}