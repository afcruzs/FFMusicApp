package ffmusic.com.ffmusicapp.endpoints;

import android.content.Context;
import android.util.Log;

import com.ffmusic.backend.ffMusicApi.model.Room;
import com.ffmusic.backend.ffMusicApi.model.RoomCollection;
import com.ffmusic.backend.ffMusicApi.model.SongRoomCollection;

import java.io.IOException;

/**
 * Created by PC on 16/10/2015.
 */
public class GetRoomSongsAsyncTask extends ApiRequestAsyncTask<Long,Void,SongRoomCollection> {

    public GetRoomSongsAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected SongRoomCollection doInBackground(Long... params) {
        try {
            Long room = params[0];
            return ffMusicApi.roomEndPoint().songs(room).execute();
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }

    }
}
