package ffmusic.com.ffmusicapp.endpoints;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.ffmusic.backend.ffMusicApi.FfMusicApi;
import com.ffmusic.backend.ffMusicApi.model.Room;

import java.io.IOException;

import ffmusic.com.ffmusicapp.R;

/**
 * Created by PC on 15/10/2015.
 */
public class GetRoomByIdAsyncTask extends ApiRequestAsyncTask<Long,Void,Room> {

    public GetRoomByIdAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected Room doInBackground(Long... params) {


        Long theId = params[0];

        try {
            return ffMusicApi.roomEndPoint().getRoomById(theId).execute();
        } catch (IOException e) {
          //  e.printStackTrace();
            onError(e);
            return null;
        }
        //return null;
    }

    @Override
    public void onPostExecute( Room theRoom ){
        dialog.dismiss();
    }
}
