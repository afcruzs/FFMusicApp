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
                int aux = 0;
                for(SongRoom sr : data.getItems()){
                    if(sr.getIdxInQueue() != -1) aux++;
                }
                return aux;
            }else
                return 0;

        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }

    }
}
