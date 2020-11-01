package com.example.servicenovigrad;

import java.util.List;

public class Service {
	private String id;
	private String name;
	private double price;
	private List<String> forms;
	private List<String> docs;

	public Service() {
		super();
	}
	public Service(String id, String name, double price, List<String> forms, List<String> docs) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.forms = forms;
		this.docs = docs;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getId() { return this.id; }
	public void setServiceName(String name) { this.name = name; }
	public String getServiceName() { return this.name; }
	public void setPrice(double price) { this.price = price; }
	public double getPrice() { return this.price; }
	public void setForms(List<String> forms) { this.forms = forms; }
	public List<String> getForms() { return this.forms; }
	public List<String> getDocs() { return this.docs; }

	public String toString() {
		return "Name: " + getServiceName() + "\nPrice: " + getPrice() + "\nForms: " + getForms().toString() + "\nDocuments: " + getDocs().toString();

	}

}