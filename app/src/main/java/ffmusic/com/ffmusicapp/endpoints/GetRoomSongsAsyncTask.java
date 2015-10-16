package ffmusic.com.ffmusicapp.endpoints;

import android.content.Context;

import com.ffmusic.backend.ffMusicApi.model.Room;
import com.ffmusic.backend.ffMusicApi.model.RoomCollection;
import com.ffmusic.backend.ffMusicApi.model.SongRoomCollection;

import java.io.IOException;

/**
 * Created by PC on 16/10/2015.
 */
public class GetRoomSongsAsyncTask extends ApiRequestAsyncTask<Room,Void,SongRoomCollection> {

    public GetRoomSongsAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected SongRoomCollection doInBackground(Room... params) {
        try {
            return ffMusicApi.roomEndPoint().songs(params[0]).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
