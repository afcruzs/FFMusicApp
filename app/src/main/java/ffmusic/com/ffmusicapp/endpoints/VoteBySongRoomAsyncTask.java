package ffmusic.com.ffmusicapp.endpoints;

import android.content.Context;

import com.ffmusic.backend.ffMusicApi.model.Song;
import com.ffmusic.backend.ffMusicApi.model.SongRoom;

import java.io.IOException;

/**
 * Created by PC on 24/11/2015.
 */
public class VoteBySongRoomAsyncTask extends  ApiRequestAsyncTask<SongRoom,Void,SongRoom> {

    public VoteBySongRoomAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected SongRoom doInBackground(SongRoom... params) {

        try {
            return ffMusicApi.roomEndPoint().vote(params[0]).execute();
        } catch (IOException e) {
            throw new RuntimeException("Vote failed");
        }
    }
}
