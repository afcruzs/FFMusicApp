package com.ffmusic.backend;

import com.ffmusic.backend.model.Room;
import com.ffmusic.backend.model.Song;
import com.ffmusic.backend.model.SongRoom;
import com.ffmusic.backend.model.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

import java.util.List;

import static com.ffmusic.backend.OfyService.ofy;

/**
 * Created by PC on 11/10/2015.
 */

@Api(
        name = "ffMusicApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.ffmusic.com",
                ownerName = "backend.ffmusic.com",
                packagePath = ""
        )
)
public class RoomEndPoint {

        @ApiMethod(httpMethod = "POST")
        public Room insertRoom(Room data){
                ofy().save().entity(data).now();
                return data;
        }

        @ApiMethod(httpMethod = "POST")
        public List<Room> roomsByUser(final User user){
                return ofy().load().type(Room.class).filter("roomOwner",user).list();
        }

        @ApiMethod(httpMethod = "POST")
        public List<Room> nearByRooms(final User user){
                return ofy().load().type(Room.class).filter("roomOwner !=", user ).list();
        }

        @ApiMethod(httpMethod = "GET")
        public final Room getRoomById(@Named("idRoom") final Long id){
                return ofy().load().type(Room.class).id(id).now();
        }

        @ApiMethod(httpMethod = "POST")
        public SongRoom songRoom(final SongRoom songRoom){
                ofy().save().entity(songRoom).now();
                return songRoom;
        }

        @ApiMethod(httpMethod = "POST")
        public List<SongRoom> songs(final Room room){
                return ofy().load().type(SongRoom.class).filter("room",room).list();
        }

        @ApiMethod(httpMethod = "POST")
        public Song insertSong(final Song data){
                ofy().save().entity(data).now();
                return data;
        }
}
