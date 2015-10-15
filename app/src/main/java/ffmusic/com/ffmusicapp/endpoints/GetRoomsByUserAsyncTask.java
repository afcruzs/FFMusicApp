package ffmusic.com.ffmusicapp.endpoints;

import android.content.Context;
import android.os.AsyncTask;

import com.ffmusic.backend.ffMusicApi.FfMusicApi;
import com.ffmusic.backend.ffMusicApi.model.RoomCollection;
import com.ffmusic.backend.ffMusicApi.model.User;

import java.io.IOException;

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
public class GetRoomsByUserAsyncTask extends ApiRequestAsyncTask<User, Void, RoomCollection> {


    public GetRoomsByUserAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected RoomCollection doInBackground(User... params) {

        User user = params[0];
        try {
            return ffMusicApi.roomEndPoint().roomsByUser(user).execute();
        } catch (IOException e) {
            throw new RuntimeException("An error ocurred retrieving user rooms..." + e);
        }


    }
}
