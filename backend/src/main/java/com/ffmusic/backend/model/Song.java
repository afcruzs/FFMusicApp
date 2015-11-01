package com.ffmusic.backend.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Song {

    @Id
    Long id;

    @Index
    String songName;

    @Index
    String artist;

    @Index
    String songYoutubeId;

    String thumbnailURL;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSongYoutubeId() {
        return songYoutubeId;
    }

    public void setSongYoutubeId(String songYoutubeId) {
        this.songYoutubeId = songYoutubeId;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}
