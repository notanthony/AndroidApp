package com.example.servicenovigrad;



public class ServiceRequest{
    //once we do the customer class we will put more implementation here
    //we are currently unsure on how we would take the image input...
    //for now all we need is a approval and denial for each requests
    private boolean approved;
    //primatives cant be null so we need to see if the service was even checked
    private boolean checked;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public ServiceRequest() {

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
        return "This service has " + ((isChecked()) ? (isApproved() ? "been approved" : "not been approved"):"not been checked") ;
    }

}
