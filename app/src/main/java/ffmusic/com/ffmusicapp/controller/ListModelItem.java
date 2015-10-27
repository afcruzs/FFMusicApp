package ffmusic.com.ffmusicapp.controller;

import java.io.Serializable;


public class ListModelItem implements Serializable {

    private String name;
    private String artist;
    private String songId;


    public ListModelItem( String name, String songId, String artist){
        this.name = name;
        this.songId = songId;
        this.artist = artist;
    }

    public String getName(){
        return name;
    }
    public String getSongId(){
        return songId;
    }
    public String getArtist() { return artist; }


}
