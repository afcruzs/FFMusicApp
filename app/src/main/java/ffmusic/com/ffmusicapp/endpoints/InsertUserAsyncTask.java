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

import java.io.IOException;

/**
 * Created by Andres Felipe Cruz on 04/10/2015.
 *
 * Inserta un usuario en el DataStore.
 *
 *
 */
public class InsertUserAsyncTask extends AsyncTask<User, Void, User> {
    private static FfMusicApi ffMusicApi = null;

    @Override
    protected User doInBackground(User... params) {

        if(ffMusicApi == null){
            FfMusicApi.Builder builder = new FfMusicApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl(Constants.ROOT_URL);

            ffMusicApi = builder.build();
        }

        User user = params[0];

        try {

            return ffMusicApi.userEndPoint().insertUser(user).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  null;
    }


}
