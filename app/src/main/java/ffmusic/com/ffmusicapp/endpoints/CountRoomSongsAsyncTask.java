package ffmusic.com.ffmusicapp.endpoints;

import android.content.Context;

import com.ffmusic.backend.ffMusicApi.model.SongRoom;
import com.ffmusic.backend.ffMusicApi.model.SongRoomCollection;

import java.io.IOException;

/**
 * Created by PC on 02/11/2015.
 */
public class CountRoomSongsAsyncTask extends ApiRequestAsyncTask<Long,Void,Integer> {
    public CountRoomSongsAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected Integer doInBackground(Long... params) {
        try {
            Long room = params[0];
            SongRoomCollection data = ffMusicApi.roomEndPoint().songs(room).execute();
            if( data != null && data.getItems() != null ) {
                return data.getItems().size();
            }else
                return 0;

        } catch (IOException e) {
            onError(e);
            return 0;

            //throw new RuntimeException(e.toString());
        }

    }
}
