package ffmusic.com.ffmusicapp.controller;

import java.io.Serializable;

/**
 * Created by PC on 29/10/2015.
 */
public class RoomListModelItem implements Serializable {
    private String roomName;
    private String roomOwnerName;

    public RoomListModelItem(String roomName, String roomOwnerName) {
        this.roomName = roomName;
        this.roomOwnerName = roomOwnerName;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getRoomOwnerName() {
        return roomOwnerName;
    }
}
