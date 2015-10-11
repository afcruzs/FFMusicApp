package ffmusic.com.ffmusicapp.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ffmusic.com.ffmusicapp.R;

/**
 * Created by Milder on 11/10/2015.
 */
public class SettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        TextView textView = (TextView) view.findViewById(R.id.fragment_settings_title);
        textView.setText("Setttings");
        return view;
    }
}
