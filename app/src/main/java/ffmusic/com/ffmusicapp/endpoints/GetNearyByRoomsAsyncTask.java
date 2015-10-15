package ffmusic.com.ffmusicapp.endpoints;

import android.os.AsyncTask;

import com.ffmusic.backend.ffMusicApi.FfMusicApi;
import com.ffmusic.backend.ffMusicApi.model.RoomCollection;
import com.ffmusic.backend.ffMusicApi.model.User;

import java.io.IOException;

/**
 * Created by PC on 15/10/2015.
 */
public class GetNearyByRoomsAsyncTask extends AsyncTask<User, Void, RoomCollection> {

    private static FfMusicApi ffMusicApi = null;

    @Override
    protected RoomCollection doInBackground(User... params) {

        if(ffMusicApi == null){
            FfMusicApi.Builder builder = Constants.getApiBuilder();
            ffMusicApi = builder.build();
        }


        User user = params[0];
        try {
            return ffMusicApi.roomEndPoint().nearByRooms(user).execute();
        } catch (IOException e) {
            throw new RuntimeException("An error ocurred retrieving user rooms..." + e);
        }
    }
}
