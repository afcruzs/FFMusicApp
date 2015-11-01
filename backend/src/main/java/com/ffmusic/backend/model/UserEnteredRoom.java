package com.ffmusic.backend.model;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

/**
 * Created by PC on 31/10/2015.
 */

@Entity
public class UserEnteredRoom {
    @Id
    Long id;

    @Load
    @Index
    Ref<Room> room;

    @Load
    @Index
    Ref<User> user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom(){
        return room.get();
    }

    public void setRoom(Room r){
        this.room = Ref.create(r);
    }

    public User getUser(){
        return user.get();
    }

    public void setUser(User u){
        this.user = Ref.create(u);
    }


}
