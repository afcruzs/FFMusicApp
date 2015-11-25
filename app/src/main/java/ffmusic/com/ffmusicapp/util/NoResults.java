package ffmusic.com.ffmusicapp.util;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ffmusic.com.ffmusicapp.R;

/**
 * Created by Milder on 24/11/2015.
 */
public class NoResults {

    public static void hide ( View view ) {
        View v = view.findViewById(R.id.no_results);
        v.setVisibility(View.GONE);
        //TextView t = (TextView) view.findViewById(R.id.no_results_text);
        //t.setVisibility(View.GONE);
        ImageView i = (ImageView) view.findViewById(R.id.no_results_image);
        i.setVisibility(View.GONE);
    }

    public static void hide ( Activity view ) {
        View v = view.findViewById(R.id.no_results);
        v.setVisibility(View.GONE);
        //TextView t = (TextView) view.findViewById(R.id.no_results_text);
        //t.setVisibility(View.GONE);
        ImageView i = (ImageView) view.findViewById(R.id.no_results_image);
        i.setVisibility(View.GONE);
    }

    public static void show ( View view ) {
        View v = view.findViewById(R.id.no_results);
        v.setVisibility(View.VISIBLE);
        //TextView t = (TextView) view.findViewById(R.id.no_results_text);
        //t.setVisibility(View.VISIBLE);
        ImageView i = (ImageView) view.findViewById(R.id.no_results_image);
        i.setVisibility(View.VISIBLE);
    }

    public static void show ( Activity view ) {
        View v = view.findViewById(R.id.no_results);
        v.setVisibility(View.VISIBLE);
        //TextView t = (TextView) view.findViewById(R.id.no_results_text);
        //t.setVisibility(View.VISIBLE);
        ImageView i = (ImageView) view.findViewById(R.id.no_results_image);
        i.setVisibility(View.VISIBLE);
    }
}
