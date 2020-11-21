package com.example.servicenovigrad;
public class Address {

    public enum Province {
        AB, BC, MB, NB, NL, NT, NS, NU, ON, PE, QC, SK, YT;
    }
    private Province province;
    private String postalCode;
    private String street;
    private String city = "";
    private final String COUNTRY = "Canada";

    public Address(Province province, String postalCode, String street, String city) {
        this.province = province;
        this.postalCode = postalCode;
        this.street = street;
        this.city = city;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCOUNTRY() {
        return COUNTRY;
    }

}
