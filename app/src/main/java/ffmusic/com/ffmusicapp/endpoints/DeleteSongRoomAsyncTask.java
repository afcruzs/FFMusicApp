package ffmusic.com.ffmusicapp.endpoints;

import android.content.Context;
import android.util.Log;

import com.ffmusic.backend.ffMusicApi.model.SongRoom;

import java.io.IOException;

/**
 * Created by PC on 22/11/2015.
 */
public class DeleteSongRoomAsyncTask extends ApiRequestAsyncTask<Long,Void,SongRoom>  {
    public DeleteSongRoomAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected SongRoom doInBackground(Long... params) {
        try {
            Log.d("xd","Deleting: " + params[0]);
            return ffMusicApi.roomEndPoint().deleteSongRoom(params[0]).execute();
        } catch (IOException e) {
            e.printStackTrace();
            onError(e);
            return null;
            //throw new RuntimeException("An error has occurred deleting");
        }
        //  return null;
    }
}
