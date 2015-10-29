package ffmusic.com.ffmusicapp.endpoints;

import android.content.Context;

import com.ffmusic.backend.ffMusicApi.model.RoomCollection;

import java.io.IOException;

/**
 * Created by PC on 28/10/2015.
 */
public class GetRoomsByPrefixAsyncTask extends ApiRequestAsyncTask<String,Void,RoomCollection> {

    public GetRoomsByPrefixAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected RoomCollection doInBackground(String... params) {
        String prefix = params[0];
        try {
            return ffMusicApi.roomEndPoint().querySongs(prefix).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
