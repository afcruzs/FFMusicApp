package ffmusic.com.ffmusicapp.endpoints;

import android.content.Context;

import com.ffmusic.backend.ffMusicApi.model.Room;
import com.ffmusic.backend.ffMusicApi.model.RoomCollection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 28/10/2015.
 */
public class GetRoomsByPrefixAsyncTask extends ApiRequestAsyncTask<String,Void,List<Room>> {

    public GetRoomsByPrefixAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected List<Room> doInBackground(String... params) {
        String prefix = params[0];
        try {
            RoomCollection data = ffMusicApi.roomEndPoint().querySongs(prefix).execute();
            if( data != null && data.getItems() != null )
                return data.getItems();
            else
                return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
