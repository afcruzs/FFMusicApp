package ffmusic.com.ffmusicapp.controller;

/**
 * Created by Milder on 11/10/2015.
 */

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ffmusic.com.ffmusicapp.R;

public class RoomsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rooms, container, false);

        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.create_new_room_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "CreateNewUser", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}