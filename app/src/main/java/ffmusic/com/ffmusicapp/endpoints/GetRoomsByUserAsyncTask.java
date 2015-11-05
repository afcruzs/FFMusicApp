package ffmusic.com.ffmusicapp.endpoints;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.ffmusic.backend.ffMusicApi.FfMusicApi;
import com.ffmusic.backend.ffMusicApi.model.Room;
import com.ffmusic.backend.ffMusicApi.model.RoomCollection;
import com.ffmusic.backend.ffMusicApi.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andres F. Cruz on 11/10/2015.
 *
 * This class gets a collection of rooms given a User Instance.
 * It is important that the given instance is synchronized with the data store.
 *
 * Sample:
 *
 *
         new GetRoomsByUserAsyncTask(){
            @Override
            public void onPostExecute(RoomCollection result){
                Log.d("Rooms","INICIO ROOMS");
                for(Room room : result.getItems()){
                    Log.d("Rooms",room.toString());
                }
                Log.d("Rooms","FIN ROOMS");
            }
        }.execute(LoginActivity.currentUser);

    Usually the user we need to know will be in LoginActivity, but it could be any other user instance.
 */
public class GetRoomsByUserAsyncTask extends ApiRequestAsyncTask<User, Void, List<Room>> {


    public GetRoomsByUserAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected List<Room> doInBackground(User... params) {

        User user = params[0];
        try {
            RoomCollection data = ffMusicApi.roomEndPoint().roomsByUser(user).execute();
            if( data != null && data.getItems() != null ) return data.getItems();
            else{

                return new ArrayList<>();
            }

        } catch (IOException e) {
            throw new RuntimeException("An error ocurred retrieving user rooms..." + e);
        }


    }
}
