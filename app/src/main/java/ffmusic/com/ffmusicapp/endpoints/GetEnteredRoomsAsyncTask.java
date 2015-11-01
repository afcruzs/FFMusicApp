package ffmusic.com.ffmusicapp.endpoints;

import android.content.Context;

import com.ffmusic.backend.ffMusicApi.model.RoomCollection;
import com.ffmusic.backend.ffMusicApi.model.User;
import com.ffmusic.backend.ffMusicApi.model.UserEnteredRoom;
import com.ffmusic.backend.ffMusicApi.model.UserEnteredRoomCollection;

import java.io.IOException;

/**
 * Created by PC on 31/10/2015.
 */
public class GetEnteredRoomsAsyncTask extends ApiRequestAsyncTask<User,Void,UserEnteredRoomCollection> {

    public GetEnteredRoomsAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected UserEnteredRoomCollection doInBackground(User... params) {
        User user = params[0];
        try {
            return ffMusicApi.roomEndPoint().enteredRooms(user.getId()).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
