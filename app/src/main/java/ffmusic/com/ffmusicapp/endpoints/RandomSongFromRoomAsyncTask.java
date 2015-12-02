package ffmusic.com.ffmusicapp.endpoints;

import android.content.Context;

import com.ffmusic.backend.ffMusicApi.model.Room;
import com.ffmusic.backend.ffMusicApi.model.Song;

import java.io.IOException;

/**
 * Created by PC on 01/11/2015.
 */
public class RandomSongFromRoomAsyncTask extends  ApiRequestAsyncTask<Room,Void,Song> {

    public RandomSongFromRoomAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected Song doInBackground(Room... params) {
        try {
            return ffMusicApi.roomEndPoint().randomSongFromRoom(params[0]).execute();
        } catch (IOException e) {
            //e.printStackTrace();
            onError(e);
            return null;
        }

        //return null;
    }
}
