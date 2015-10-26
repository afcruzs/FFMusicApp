package ffmusic.com.ffmusicapp.endpoints;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.ffmusic.backend.ffMusicApi.FfMusicApi;
import com.ffmusic.backend.ffMusicApi.model.RoomCollection;
import com.ffmusic.backend.ffMusicApi.model.User;

import java.io.IOException;

/**
 * Created by PC on 15/10/2015.
 */
public class GetNearyByRoomsAsyncTask extends ApiRequestAsyncTask<User, Void, RoomCollection> {


    public GetNearyByRoomsAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected RoomCollection doInBackground(User... params) {


        User user = params[0];
        Log.d("HOLA", "Just do it " + user.toString());
        try {
            return ffMusicApi.roomEndPoint().nearByRooms(user).execute();
        } catch (IOException e) {
            throw new RuntimeException("An error ocurred retrieving user rooms..." + e);
        }
    }
}
