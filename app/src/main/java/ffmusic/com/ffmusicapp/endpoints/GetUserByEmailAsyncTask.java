package ffmusic.com.ffmusicapp.endpoints;

import android.os.AsyncTask;
import android.util.Log;

import com.ffmusic.backend.ffMusicApi.FfMusicApi;
import com.ffmusic.backend.ffMusicApi.model.User;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by PC on 11/10/2015.
 */
public abstract class GetUserByEmailAsyncTask extends AsyncTask<String,Void,User> {

    private static FfMusicApi ffMusicApi = null;

    @Override
    protected User doInBackground(String... params) {
        if(ffMusicApi == null){
            FfMusicApi.Builder builder = new FfMusicApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl(Constants.ROOT_URL);

            ffMusicApi = builder.build();
        }

        String email = params[0];

        try {
            User aux = ffMusicApi.userEndPoint().getUserByEmail(email).execute();
            if( aux == null )
                Log.d("JEJE","es null con email " + email);

            return aux;
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("JEJE","fAIL");
        return null;
    }
}
