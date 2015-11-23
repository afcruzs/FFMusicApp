package ffmusic.com.ffmusicapp.controller;

import java.io.Serializable;


public class ListModelItem implements Serializable {

    private String name;
    private String artist;
    private String songId;
    private String thumbnailURL;
    private Long songRoomId;

    public ListModelItem( String name, String songId, String artist, String thumbnailURL, Long songRoomId){
        this.name = name;
        this.songId = songId;
        this.artist = artist;
        this.thumbnailURL = thumbnailURL;
        this.songRoomId = songRoomId;
    }

    public Long getDBId(){ return songRoomId; }

    public String getName(){
        return name;
    }
    public String getSongId(){
        return songId;
    }
    public String getArtist() { return artist; }


    public String getThumbnailURL() {
        return thumbnailURL;
    }
}
