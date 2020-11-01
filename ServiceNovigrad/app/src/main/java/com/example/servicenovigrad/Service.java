package com.example.servicenovigrad;

import java.util.List;

public class Service {
	private String _id;
	private String _name;
	private double _price;
	private List<String> _forms;
	private List<String> _docs;

	public Service() {
		super();
	}
	public Service(String id, String name, double price, List<String> forms, List<String> docs){
		_id = id;
		_name = name;
		_price = price;
		_forms = forms;
		_docs = docs;
	}
	/*
	public void setName(String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}

	public void setPrice(double price) {
		_price = price;
	}

	public double getPrice() {
		return _price;
	}
	public String toString(){
		return _name;
	}*/

	public void setId(String id) {
		_id = id;
	}
	public String getId() { return _id; }
	public void setServiceName(String name) { _name = name; }
	public String getServiceName() { return _name; }
	public void setPrice(double price) { _price = price; }
	public double getPrice() { return _price; }
	public void setForms(List<String> forms) { _forms = forms; }
	public List<String> getForms() { return _forms; }
	//public void setForms(int index, String form) { _forms[index] = form; }
	public List<String> getDocs() { return _docs; }
	//public void setDocs(int index, String doc) { _docs[index] = doc; }



}