package ffmusic.com.ffmusicapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.ffmusic.backend.ffMusicApi.model.Room;
import com.ffmusic.backend.ffMusicApi.model.RoomCollection;

import java.util.List;

import ffmusic.com.ffmusicapp.R;
import ffmusic.com.ffmusicapp.endpoints.GetNearyByRoomsAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.GetRoomsByUserAsyncTask;

/**
 * Un fragmento que contiene una grilla de Rooms
 */
public class RoomsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private static List<Room> userRooms,otherRooms;

    public static void setUserRooms(List<Room> data){
        userRooms = data;
    }

    public static void setOtherRooms(List<Room> data){
        otherRooms = data;
    }

    public static List<Room> getUserRooms() {
        return userRooms;
    }

    public static List<Room> getOtherRooms() {
        return otherRooms;
    }

    private GridView grid;
    private GridAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    /**
     * Argumento que representa el numero seccion al que pertenece
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Creacion prefabricada de un {@link RoomsFragment}
     */
    public static RoomsFragment newInstance(int sectionNumber) {
        RoomsFragment fragment = new RoomsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public RoomsFragment() {
    }

    @Override
    public void onResume(){
        super.onResume();
        updateRoomsGrid();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Lol","Creawtinggg");
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        
        // Obtención del grid view
        grid = (GridView) rootView.findViewById(R.id.gridview);


        // Inicializar el grid view
        setUpGridView(grid);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);

        FloatingActionButton floatingActionButton = (FloatingActionButton) rootView .findViewById(R.id.create_new_room_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "CreateNewUser", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), CreateRoomActivity.class));
            }
        });

        return rootView;
    }

    /**
     * Infla el grid view del fragmento dependiendo de la seccionn
     *
     * @param grid Instancia del grid view
     */
    private void setUpGridView(GridView grid) {
        int section_number = getArguments().getInt(ARG_SECTION_NUMBER);
        Log.d("Pecora", section_number + "");
        switch (section_number) {
            case 1:
                adapter = new GridAdapter(getActivity(), getUserRooms());
                grid.setAdapter(adapter);
                break;
            case 2:
                adapter = new GridAdapter(getActivity(), getOtherRooms());
                grid.setAdapter(adapter);
                break;
        }



    }

    void updateRoomsGrid(){

        new GetRoomsByUserAsyncTask(){
            @Override
            public void onPostExecute(RoomCollection result){
                userRooms = result.getItems();


                new GetNearyByRoomsAsyncTask(){
                    @Override
                    public void onPostExecute(RoomCollection rooms){
                        otherRooms = rooms.getItems();
                        setUpGridView(grid);
                        adapter.notifyDataSetChanged();
                        grid.setAdapter(adapter);
                        grid.invalidateViews();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }.execute(LoginActivity.currentUser);


            }
        }.execute(LoginActivity.currentUser);
    }

    @Override
    public void onRefresh() {

        updateRoomsGrid();

    }
}