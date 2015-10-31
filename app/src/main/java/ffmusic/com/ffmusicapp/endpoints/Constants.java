package ffmusic.com.ffmusicapp.endpoints;

import com.ffmusic.backend.ffMusicApi.FfMusicApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by PC on 11/10/2015.
 */
public class Constants {

    public static final String DEVELOPMENT_KEY = "AIzaSyB1f6PF0mzo6SvjVyoBqkOdMEqzQk26CYE";
    /*
    *   This is the ip of YOUR local machine if you want to test the backend locally.
    *   Please set it to run the backend in your machine.
    * */
    public static final String DEVELOPMENT_IP = "192.168.0.8";

    public static final String DEVELOPMENT_ROOT_URL = "http://"+DEVELOPMENT_IP+":8080/_ah/api/";
    public static final String PRODUCTION_ROOT_URL = "https://ffmusicbackend.appspot.com/_ah/api/";

    /*
        If PRODUCTION_ROOT_URL the device will try to connect to the server in DEVELOPMENT_IP address.

        Otherwise it will connect to de api deployed in the google cloud.
    * */
    public static final String ROOT_URL = DEVELOPMENT_ROOT_URL;


    /*
        Returns the correct api builder according to the kind of ROOT_URL Choosen.
     */
    public static FfMusicApi.Builder getApiBuilder(){

        if( ROOT_URL.equals(DEVELOPMENT_ROOT_URL) ){
            FfMusicApi.Builder builder = new FfMusicApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl(Constants.ROOT_URL).setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            return builder;
        }else{
            FfMusicApi.Builder builder = new FfMusicApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl(Constants.ROOT_URL);

            return builder;
        }

    }
}
