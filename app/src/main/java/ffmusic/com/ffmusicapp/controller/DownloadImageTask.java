package ffmusic.com.ffmusicapp.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by PC on 01/11/2015.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    protected ImageView bmImage;
    private static Map<String,Bitmap> cache = new HashMap<>();
    private String imageKey;

    public DownloadImageTask(ImageView bmImage, String key) {
        this.bmImage = bmImage;
        this.imageKey = key;
    }


    public static Bitmap getBitmaskFromCache(String key){
        return cache.get(key);
    }

    protected Bitmap doInBackground(String... urls) {

        if( getBitmaskFromCache(imageKey) != null )
            return getBitmaskFromCache(imageKey);

        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            URL newurl = new URL(urldisplay);
            mIcon11 = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
            addBitmapToMemoryCache(imageKey,mIcon11);
        } catch (Exception e) {
            Log.d("HOLA", e.getMessage() + urldisplay);
            e.printStackTrace();
        }
        return mIcon11;
    }

    private void addBitmapToMemoryCache(String imageKey, Bitmap mIcon11) {
        Log.d("HOLA","ADDING TO CACHE " + imageKey);
        cache.put(imageKey,mIcon11);
    }

    protected void onPostExecute(Bitmap result) {
        Log.d("HOLA","Finished downloadings " + (result != null));
        if(bmImage!=null)
            bmImage.setImageBitmap(result);
    }
}
