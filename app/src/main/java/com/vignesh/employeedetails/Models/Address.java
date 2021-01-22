package com.vignesh.employeedetails.Models;

import java.util.List;

public class Address {
    //    "street": "Douglas Extension",
//            "suite": "Suite 847",
//            "city": "McKenziehaven",
//            "zipcode": "59590-4157"
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    public Address() {
    }

    public Address(String street, String suite, String city, String zipcode) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
    }


    public String getStreet() {
        return street;
    }

    public String getSuite() {
        return suite;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }
}
