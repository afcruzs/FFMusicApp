package ffmusic.com.ffmusicapp.endpoints;

import android.content.Context;
import android.util.Log;

import com.ffmusic.backend.ffMusicApi.model.Room;
import com.ffmusic.backend.ffMusicApi.model.RoomCollection;
import com.ffmusic.backend.ffMusicApi.model.SongRoom;
import com.ffmusic.backend.ffMusicApi.model.SongRoomCollection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 16/10/2015.
 */
public class GetRoomSongsAsyncTask extends ApiRequestAsyncTask<Long,Void,List<SongRoom>> {

    public GetRoomSongsAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected List<SongRoom> doInBackground(Long... params) {
        try {
            Long room = params[0];
            SongRoomCollection data = ffMusicApi.roomEndPoint().songs(room).execute();
            if( data != null && data.getItems() != null )
                return data.getItems();
            else
                return new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }

    }
}
