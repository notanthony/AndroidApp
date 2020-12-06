package com.example.servicenovigrad;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ServiceRequest{
    private boolean approved = false;
    private boolean checked = false;
    ArrayList<String> form;
    String filePath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    private String name;
    private List<String> formEntries;
    private List<String> documentReferences;
    public ServiceRequest(ArrayList<String> form, String filePath) {
        this.form = form;
        this.filePath= filePath;

    }

    public ServiceRequest(String id, String name, List<String> formEntries, List<String> documentReferences) {
        this.id = id;
        this.name = name;
        this.formEntries = formEntries;
        this.documentReferences = documentReferences;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFormEntries() {
        return formEntries;
    }

    public void setFormEntries(List<String> formEntries) {
        this.formEntries = formEntries;
    }

    public List<String> getDocumentReferences() {
        return documentReferences;
    }

    public void setDocumentReferences(List<String> documentReferences) {
        this.documentReferences = documentReferences;
    }

    public boolean isApproved() {
        return approved;
    }
    public boolean isChecked() {
        return checked;
    }
    public void setApproved(boolean approve) {
        approved = approve;
        checked = true;
    }

    public String toString() {
        return "The request" +name + "("+id+")"+" has " + ((isChecked()) ? (isApproved() ? "been approved" : "not been approved"):"not been checked") ;
    }

}
