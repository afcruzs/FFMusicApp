package ffmusic.com.ffmusicapp.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ffmusic.com.ffmusicapp.R;
import ffmusic.com.ffmusicapp.endpoints.EndpointsAsyncTask;

public class HomeFragment extends Fragment {

    public static final String TITLE = "title";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final String title = getArguments().getString(TITLE);

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView titleTextView = (TextView) view.findViewById(R.id.fragment_home_title);

        titleTextView.setText(title);
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        new EndpointsAsyncTask().execute(new Pair<Context, String>(getActivity(), "Pecora UN"));
    }

}
