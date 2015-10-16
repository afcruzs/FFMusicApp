package ffmusic.com.ffmusicapp.controller;

import java.io.Serializable;


public class ListModelItem implements Serializable {

    private String name;
    private String songId;
    private boolean value;

    public ListModelItem( String name, String songId){
        this.name = name;
        this.songId = songId;
    }

    public String getName(){
        return name;
    }
    public String getSongId(){
        return songId;
    }


}
