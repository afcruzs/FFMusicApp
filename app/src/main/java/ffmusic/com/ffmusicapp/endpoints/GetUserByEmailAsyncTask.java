package ffmusic.com.ffmusicapp.endpoints;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.ffmusic.backend.ffMusicApi.FfMusicApi;
import com.ffmusic.backend.ffMusicApi.model.User;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by PC on 11/10/2015.
 */
public abstract class GetUserByEmailAsyncTask extends ApiRequestAsyncTask<String,Void,User> {


    public GetUserByEmailAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected User doInBackground(String... params) {
        String email = params[0];

        try {
            User aux = ffMusicApi.userEndPoint().getUserByEmail(email).execute();
            if( aux == null )
                Log.d("JEJE","es null con email " + email);

            return aux;
        } catch (IOException e) {
          //  e.printStackTrace();
            onError(e);
            return null;
        }
        //Log.d("JEJE","fAIL");
        //return null;
    }
}
