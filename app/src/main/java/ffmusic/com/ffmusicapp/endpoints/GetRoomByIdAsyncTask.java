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
public class GetRoomByIdAsyncTask extends AsyncTask<Long,Void,Room> {

    private static FfMusicApi ffMusicApi = null;
    private ProgressDialog dialog;
    private Context context;

    public GetRoomByIdAsyncTask(Activity context){
        this.context = context;
    }

    protected void onPreExecute() {
        this.dialog = ProgressDialog.show( context, "" , context.getResources().getString(R.string.loading), true );
    }

    @Override
    protected Room doInBackground(Long... params) {

        if(ffMusicApi == null){
            FfMusicApi.Builder builder = Constants.getApiBuilder();
            ffMusicApi = builder.build();
        }
        Long theId = params[0];

        try {
            return ffMusicApi.roomEndPoint().getRoomById(theId).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onPostExecute( Room theRoom ){
        dialog.dismiss();
    }
}
