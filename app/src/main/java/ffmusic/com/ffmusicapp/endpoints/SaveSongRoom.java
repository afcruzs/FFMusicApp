package ffmusic.com.ffmusicapp.endpoints;

import android.content.Context;


import com.ffmusic.backend.ffMusicApi.model.SongRoom;

import java.io.IOException;

/**
 * Created by PC on 16/10/2015.
 */
public class SaveSongRoom extends ApiRequestAsyncTask<SongRoom,Void,SongRoom> {

    public SaveSongRoom(Context context) {
        super(context);
    }

    @Override
    protected SongRoom doInBackground(SongRoom... params) {
        try {
            return ffMusicApi.roomEndPoint().songRoom(params[0]).execute();
        } catch (IOException e) {
         //   e.printStackTrace();
            onError(e);
            return null;
        }
        //return null;
    }
}
