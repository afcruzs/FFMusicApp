package com.ffmusic.backend;

import com.ffmusic.backend.model.Room;
import com.ffmusic.backend.model.Song;
import com.ffmusic.backend.model.SongRoom;
import com.ffmusic.backend.model.User;
import com.ffmusic.backend.model.UserEnteredRoom;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.googlecode.objectify.Work;
import com.googlecode.objectify.cmd.*;
import java.util.List;
import java.util.Random;

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
        public Room insertRoom(final Room data){
                return ofy().transact(new Work<Room>() {
                        public Room run() {
                                ofy().clear();
                                ofy().save().entity(data).now();
                                return data;
                        }
                });
        }

        @ApiMethod(httpMethod = "POST")
        public List<Room> roomsByUser(final User user){
                ofy().clear();
                return ofy().load().type(Room.class).filter("roomOwner",user).list();
        }

        @ApiMethod(httpMethod = "POST")
        public List<Room> nearByRooms(final User user){
                ofy().clear();

                return ofy().load().type(Room.class).filter("roomOwner !=", user).list();
        }

        @ApiMethod(httpMethod = "GET")
        public final Room getRoomById(@Named("idRoom") final Long id){
                ofy().clear();
                Room r = ofy().load().type(Room.class).id(id).now();
                //r.setRoomOwner( r.getRoomOwner() );
                return r;
        }

        @ApiMethod(httpMethod = "POST")
        public SongRoom songRoom(final SongRoom songRoom){
                return ofy().transact(new Work<SongRoom>() {
                        @Override
                        public SongRoom run() {
                                ofy().clear();
                                ofy().save().entity(songRoom).now();
                                return songRoom;
                        }
                });
        }

        @ApiMethod(httpMethod = "POST")
        public Song randomSongFromRoom(final Room room){
                ofy().clear();
                List<SongRoom> list = ofy().load().type(SongRoom.class).filter("room",room).list();
                if(list == null || list.isEmpty()) return null;
                SongRoom sr = list.get( new Random().nextInt(list.size()) );
                while( sr.getIdxInQueue() == -1  )
                        sr = list.get( new Random().nextInt(list.size()) );
                return sr.getSong();
        }

        @ApiMethod(httpMethod = "POST")
        public UserEnteredRoom userEnteredRoom(final UserEnteredRoom userEnteredRoom){
                ofy().clear();
                List<UserEnteredRoom> aux = ofy().load().type(UserEnteredRoom.class).
                        filter("user", userEnteredRoom.getUser()).
                        filter("room", userEnteredRoom.getRoom()).list();

                if( aux == null || aux.size() == 0 ){
                        ofy().save().entity(userEnteredRoom).now();
                }else{
                        return aux.get(0);
                }

                return userEnteredRoom;
        }

        @ApiMethod(httpMethod = "POST")
        public List<SongRoom> songs(@Named("idRoom") final Long roomId){
                ofy().clear();
                Room room = ofy().load().type(Room.class).id(roomId).now();
                return ofy().load().type(SongRoom.class).filter("room",room).list();
        }

        @ApiMethod(httpMethod = "POST")
        public List<UserEnteredRoom> enteredRooms(@Named("idRoom") final Long userId){
                ofy().clear();
                User user = ofy().load().type(User.class).id(userId).now();
                return ofy().load().type(UserEnteredRoom.class).filter("user",user).list();
        }

        @ApiMethod(httpMethod = "POST")
        public Song insertSong(final Song data){

                return ofy().transact(new Work<Song>() {
                        @Override
                        public Song run() {
                                ofy().save().entity(data).now();
                                return data;
                        }
                });
        }

        @ApiMethod(httpMethod = "POST")
        public SongRoom deleteSongRoom(@Named("idSongRoom") final Long songRoomId){
                return ofy().transact(new Work<SongRoom>() {
                        @Override
                        public SongRoom run() {
                                ofy().clear();
                                SongRoom sr = ofy().load().type(SongRoom.class).id(songRoomId).now();
                                sr.setIdxInQueue(new Integer(-1));
                                ofy().save().entity(sr).now();
                                return sr;
                        }
                });
        }

        @ApiMethod(httpMethod = "POST")
        public List<Room> querySongs(@Named("prefix")String prefix){
                return ofy().load().type(Room.class).
                        filter("name >=",prefix).filter("name <",prefix + "\ufffd").list();
        }

        @ApiMethod(httpMethod = "POST")
        public SongRoom vote(final SongRoom songRoom){
                return ofy().transact(new Work<SongRoom>() {
                        @Override
                        public SongRoom run() {
                                ofy().clear();
                                songRoom.setVotes( songRoom.getVotes() + 1);
                                ofy().save().entity(songRoom).now();
                                return songRoom;
                                /*ofy().clear();
                                Room room = ofy().load().type(Room.class).id(songRoom.getSong().getId()).now();
                                List<SongRoom> xd = ofy().load().type(SongRoom.class).filter("room",room).list();
                                for(int i=0; i<xd.size(); i++){
                                        if( xd.get(i).getIdxInQueue().equals(i) == false ){
                                                SongRoom tmp = xd.get(i);
                                                tmp.setIdxInQueue(i);
                                                ofy().save().entity(tmp).now();
                                        }
                                }*/

                        }
                });
        }


}
