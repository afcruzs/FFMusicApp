package ffmusic.com.ffmusicapp.endpoints;

import android.content.Context;

import com.ffmusic.backend.ffMusicApi.model.RoomCollection;
import com.ffmusic.backend.ffMusicApi.model.User;

/**
 * Created by PC on 31/10/2015.
 */
public class GetEnteredRoomsAsyncTask extends ApiRequestAsyncTask<User,Void,RoomCollection> {

    public GetEnteredRoomsAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected RoomCollection doInBackground(User... params) {
        return null;
    }
}
