package ffmusic.com.ffmusicapp.endpoints;

import android.content.Context;

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
            return ffMusicApi.roomEndPoint().songs(room).execute().getItems().size();
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }

    }
}
