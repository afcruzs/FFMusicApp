package com.ffmusic.backend.model;


import com.google.appengine.repackaged.com.google.gson.JsonObject;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import org.json.JSONObject;


@Entity
public class User {

    private String firstName;
    private String lastName;
    private String email;

    @Id
    private Long id;

    public User (  String firstName, String lastName, String email ) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId(){ return id; }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
