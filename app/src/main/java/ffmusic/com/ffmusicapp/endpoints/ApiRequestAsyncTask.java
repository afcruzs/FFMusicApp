package ffmusic.com.ffmusicapp.endpoints;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.ffmusic.backend.ffMusicApi.FfMusicApi;

/**
 * Created by PC on 15/10/2015.
 */
public abstract class ApiRequestAsyncTask<A,B,C> extends AsyncTask<A,B,C> {
    private static FfMusicApi ffMusicApi = null;
    private ProgressDialog dialog;
    private Context context;

    protected void initApi(){

    }
}
