package com.ffmusic.backend.model;

import com.google.api.server.spi.auth.common.*;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Entity
public class Room {

    @Id
    Long id;

    @Index
    private String password;

    @Index
    private String name;

    @Load
    @Index
    Ref<User> roomOwner;

    public Room(){}

    public void setRoomOwner(User value) { roomOwner = Ref.create(value); }
    public User getRoomOwner(){ return roomOwner.get(); }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
