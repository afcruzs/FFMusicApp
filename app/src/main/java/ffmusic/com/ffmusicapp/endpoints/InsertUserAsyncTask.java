package ffmusic.com.ffmusicapp.endpoints;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.ffmusic.backend.ffMusicApi.FfMusicApi;
import com.ffmusic.backend.ffMusicApi.model.User;
import com.ffmusic.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by Andres Felipe Cruz on 04/10/2015.
 *
 * Inserta un usuario en el DataStore.
 *
 *
 */
public class InsertUserAsyncTask extends ApiRequestAsyncTask<User, Void, User> {


    public InsertUserAsyncTask(Context context) {
        super(context);
    }

    @Override
    protected User doInBackground(User... params) {


        User user = params[0];

        try {

            return ffMusicApi.userEndPoint().insertUser(user).execute();
        } catch (IOException e) {
          //  e.printStackTrace();
            onError(e);
            return null;
        }

        //return  null;
    }


}
