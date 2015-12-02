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
 * Created by PC on 15/10/2015.
 */
public class GetNearyByRoomsAsyncTask extends ApiRequestAsyncTask<User, Void, List<Room>> {


    public GetNearyByRoomsAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected List<Room> doInBackground(User... params) {


        User user = params[0];

        try {
            RoomCollection data = ffMusicApi.roomEndPoint().nearByRooms(user).execute();
            if( data != null && data.getItems() != null )
                return data.getItems();
            else
                return new ArrayList<>();
        } catch (IOException e) {
            //throw new RuntimeException("An error ocurred retrieving user rooms..." + e);
            onError(e);
            return null;
        }
    }
}
