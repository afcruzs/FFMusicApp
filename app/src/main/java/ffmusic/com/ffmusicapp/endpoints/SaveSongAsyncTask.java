package ffmusic.com.ffmusicapp.endpoints;

import android.content.Context;

import com.ffmusic.backend.ffMusicApi.model.Song;

import java.io.IOException;

/**
 * Created by PC on 16/10/2015.
 */
public class SaveSongAsyncTask extends ApiRequestAsyncTask<Song,Void,Song> {

    public SaveSongAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected Song doInBackground(Song... params) {
        try {
            return ffMusicApi.roomEndPoint().insertSong(params[0]).execute();
        } catch (IOException e) {
          //  e.printStackTrace();
            onError(e);
            return null;
        }
        //return null;
    }
}
