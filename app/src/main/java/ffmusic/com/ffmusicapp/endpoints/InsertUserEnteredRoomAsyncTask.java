package ffmusic.com.ffmusicapp.endpoints;

import android.content.Context;

import com.ffmusic.backend.ffMusicApi.model.UserEnteredRoom;

import java.io.IOException;

/**
 * Created by PC on 31/10/2015.
 */
public class InsertUserEnteredRoomAsyncTask extends ApiRequestAsyncTask<UserEnteredRoom,Void,UserEnteredRoom>{

    public InsertUserEnteredRoomAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected UserEnteredRoom doInBackground(UserEnteredRoom... params) {
        try {
            return ffMusicApi.roomEndPoint().userEnteredRoom(params[0]).execute();
        } catch (IOException e) {
          //  e.printStackTrace();
            onError(e);
            return null;
        }
        //return null;
    }
}
