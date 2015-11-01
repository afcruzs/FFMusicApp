package ffmusic.com.ffmusicapp.controller;

import com.ffmusic.backend.ffMusicApi.model.Room;

import java.io.Serializable;

/**
 * Created by PC on 29/10/2015.
 */
public class RoomListModelItem implements Serializable {
    private String roomName;
    private String roomOwnerName;
    private Long roomId;

    public RoomListModelItem(String roomName, String roomOwnerName, Long roomId) {
        this.roomName = roomName;
        this.roomOwnerName = roomOwnerName;
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getRoomOwnerName() {
        return roomOwnerName;
    }

    public Long getRoomId() {
        return roomId;
    }
}
