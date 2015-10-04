package com.ffmusic.backend.model;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class User {
    public static final String FACEBOOK = "facebook";
    public static final String GOOGLE = "google";

    private String idFacebook;
    private String idGoogle;
    private String firstName;
    private String lastName;
    private String email;

    @Id
    private Long id;

    public User (  ) {

    }



    public User ( String type, String id, String firstName, String lastName, String email ) {
        if ( type.equals(FACEBOOK) ) {
            idFacebook = id;
        } else {
            idGoogle = id;
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId(){ return id; }

    public String getIdFacebook() {
        return idFacebook;
    }

    public void setIdFacebook(String idFacebook) {
        this.idFacebook = idFacebook;
    }

    public String getIdGoogle() {
        return idGoogle;
    }

    public void setIdGoogle(String idGoogle) {
        this.idGoogle = idGoogle;
    }

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
