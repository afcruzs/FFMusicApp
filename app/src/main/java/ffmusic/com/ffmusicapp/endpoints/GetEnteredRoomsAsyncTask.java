package ffmusic.com.ffmusicapp.endpoints;

import android.content.Context;

import com.ffmusic.backend.ffMusicApi.model.RoomCollection;
import com.ffmusic.backend.ffMusicApi.model.User;
import com.ffmusic.backend.ffMusicApi.model.UserEnteredRoom;
import com.ffmusic.backend.ffMusicApi.model.UserEnteredRoomCollection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 31/10/2015.
 */
public class GetEnteredRoomsAsyncTask extends ApiRequestAsyncTask<User,Void,List<UserEnteredRoom>> {

    public GetEnteredRoomsAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected List<UserEnteredRoom> doInBackground(User... params) {
        User user = params[0];
        try {
            UserEnteredRoomCollection data = ffMusicApi.roomEndPoint().enteredRooms(user.getId()).execute();
            if( data != null && data.getItems() != null )
                return data.getItems();
            return new ArrayList<>();

        } catch (IOException e) {
            onError(e);
            return null;
        }

        //return null;
    }
}
