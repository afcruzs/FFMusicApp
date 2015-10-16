package ffmusic.com.ffmusicapp.controller;

import java.io.Serializable;


public class ListModelItem implements Serializable {

    private String name;
    private boolean value;

    public ListModelItem( String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }


}
