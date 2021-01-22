package com.vignesh.employeedetails.Models;

import java.util.List;

public class User {
    //        "id": 1,
//                "name": "Leanne Graham",
//                "username": "Bret",
//                "email": "Sincere@april.biz",
//                "address": {
//        "street": "Kulas Light",
//                "suite": "Apt. 556",
//                "city": "Gwenborough",
//                "zipcode": "92998-3874",
//                "geo": {
//            "lat": "-37.3159",
//                    "lng": "81.1496"
//        }
//    },
//            "phone": "1-770-736-8031 x56442",
//            "website": "hildegard.org",
//            "company": {
//        "name": "Romaguera-Crona",
//                "catchPhrase": "Multi-layered client-server neural-net",
//                "bs": "harness real-time e-markets"
//    }
    private int id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;


    public User() {
    }

    public User(int id, String name, String username, String email, String phone, String website) {
        this.id = id;
        this.name = name;
        this.name = username;
        this.email = email;
        this.phone = phone;
        this.website = website;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }
}
