package ffmusic.com.ffmusicapp.endpoints;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.ffmusic.backend.ffMusicApi.FfMusicApi;

import ffmusic.com.ffmusicapp.Constants;
import ffmusic.com.ffmusicapp.R;

/**
 * Created by PC on 15/10/2015.
 */
public abstract class ApiRequestAsyncTask<A,B,C> extends AsyncTask<A,B,C> {
    protected static FfMusicApi ffMusicApi = null;
    protected ProgressDialog dialog;
    protected Context context;

    protected void initApi(){
        if(ffMusicApi == null){
            FfMusicApi.Builder builder = Constants.getApiBuilder();
            ffMusicApi = builder.build();
        }
    }

    public ApiRequestAsyncTask(Context context){
        this.context = context;
        initApi();
    }

    @Override
    protected void onPreExecute() {
        this.dialog = ProgressDialog.show( context, "" , context.getResources().getString(R.string.loading), true );
    }

    @Override
    public void onPostExecute(C c){
        dialog.dismiss();
    }
}
