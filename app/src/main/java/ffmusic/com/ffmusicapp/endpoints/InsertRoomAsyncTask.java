package ffmusic.com.ffmusicapp.endpoints;

import android.content.Context;
import android.os.AsyncTask;

import com.ffmusic.backend.ffMusicApi.FfMusicApi;
import com.ffmusic.backend.ffMusicApi.model.Room;

import java.io.IOException;

/**
 * Created by Andres Felipe Cruz on 11/10/2015.
 *
 * This class inserts a new Room in the data base associated to a user instance.
 *
 * Its important to associate the owner of the room BEFORE saving the room instance. Like this:
 *
     for (int i = 0; i < 5; i++){
        Room room = new Room();
        room.setName("Nombre room test"+i);
        room.setPassword("pecoraUN"+i);

        room.setRoomOwner(LoginActivity.currentUser);

        new InsertRoomAsyncTask() {
            @Override
            public void onPostExecute(Room room) {
                Log.d("LEL", "Funciono perri " + room);
            }
        }.execute(room);
     }

    The setRoomOwner its enough to set the user that owns the room, but the user Instance must
    be synchronized with the datastore.
 *
 */
public class InsertRoomAsyncTask extends ApiRequestAsyncTask<Room,Void,Room> {


    public InsertRoomAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected Room doInBackground(Room... params) {

        Room room = params[0];
        try {
            return ffMusicApi.roomEndPoint().insertRoom(room).execute();
        } catch (IOException e) {
            throw new RuntimeException("Fail at adding room: " + e.toString());

        }
        //return null;
    }
}
