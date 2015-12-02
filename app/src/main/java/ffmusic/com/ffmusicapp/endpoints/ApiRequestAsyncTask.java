package ffmusic.com.ffmusicapp.endpoints;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.ffmusic.backend.ffMusicApi.FfMusicApi;

import ffmusic.com.ffmusicapp.util.Constants;
import ffmusic.com.ffmusicapp.R;
import ffmusic.com.ffmusicapp.util.ErrorHandlerUI;

/**
 * Created by PC on 15/10/2015.
 */
public abstract class ApiRequestAsyncTask<A,B,C> extends AsyncTask<A,B,C> {
    protected static FfMusicApi ffMusicApi = null;
    protected ProgressDialog dialog;
    protected Context context;
    private Exception theError;

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

    protected void onError(Exception e){
        if( this.dialog != null ) dialog.dismiss();
        theError = e;
        Log.d("KHA","ja");

    }

    @Override
    public void onPostExecute(C c){

        if( this.dialog != null ) dialog.dismiss();
        if(theError != null){
            ErrorHandlerUI.showError(context, theError);
        }
        theError = null;
    }
}
