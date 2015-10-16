package com.ffmusic.backend.model;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

/**
 * Created by PC on 16/10/2015.
 */

@Entity
public class SongRoom {
    @Id
    Long id;

    @Load
    @Index
    Ref<Room> room;

    @Load
    @Index
    Ref<Song> song;

    @Load
    @Index
    Ref<User> createdBy;

    Integer idxInQueue;


    public User getCreatedBy(){
        return createdBy.get();
    }

    public void setCreatedBy(User user){
        createdBy = Ref.create(user);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room.get();
    }

    public void setRoom(Room room) {
        this.room = Ref.create(room);
    }

    public Song getSong() {
        return song.get();
    }

    public void setSong(Song song) {
        this.song = Ref.create(song);
    }

    public Integer getIdxInQueue() {
        return idxInQueue;
    }

    public void setIdxInQueue(Integer idxInQueue) {
        this.idxInQueue = idxInQueue;
    }


}
