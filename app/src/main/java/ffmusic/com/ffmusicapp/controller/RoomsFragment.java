package ffmusic.com.ffmusicapp.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageView;

import com.ffmusic.backend.ffMusicApi.model.Room;
import com.ffmusic.backend.ffMusicApi.model.RoomCollection;
import com.ffmusic.backend.ffMusicApi.model.Song;
import com.ffmusic.backend.ffMusicApi.model.UserEnteredRoom;
import com.ffmusic.backend.ffMusicApi.model.UserEnteredRoomCollection;

import java.util.ArrayList;
import java.util.List;

import ffmusic.com.ffmusicapp.R;
import ffmusic.com.ffmusicapp.endpoints.GetEnteredRoomsAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.GetNearyByRoomsAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.GetRoomsByUserAsyncTask;
import ffmusic.com.ffmusicapp.endpoints.RandomSongFromRoomAsyncTask;
import ffmusic.com.ffmusicapp.util.NoResults;

/**
 * Un fragmento que contiene una grilla de Rooms
 */
public class RoomsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener
, NoticeDialog{

    private static List<Room> userRooms,otherRooms,enteredRooms;

    private GridView grid;
    private RoomsGridAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    int myLastVisiblePos;

    private View rootView;

    public final static int CREATE_NEW_ROOM_ACTION = 1;
    public final static int GO_TO_ROOM_ACTION = 2;


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

    public static List<Room> getUserRooms() {
        return userRooms;
    }

    public static List<Room> getOtherRooms() {
        return otherRooms;
    }

    public static List<Room> getEnteredRooms() {
        return enteredRooms;
    }



    public static void setUserRooms(List<Room> data){
        userRooms = data;
    }

    public static void setOtherRooms(List<Room> data){
        otherRooms = data;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateRoomsGrid();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_rooms, container, false);

        // Catching grid view
        grid = (GridView) rootView.findViewById(R.id.gridview);

        // Initializing grid view
        setUpGridView(grid);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);

        /*
        * Floating action button to create new rooms
        * */
        FloatingActionButton newRoomButton = (FloatingActionButton) rootView.findViewById(R.id.create_new_room_button);

        newRoomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //its private ????
                startActivityForResult(new Intent(getActivity(), CreateRoomActivity.class), CREATE_NEW_ROOM_ACTION);
            }
        });

        myLastVisiblePos = grid.getFirstVisiblePosition();

        grid.setOnScrollListener( new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int currentFirstVisPos = view.getFirstVisiblePosition();
                if(currentFirstVisPos == myLastVisiblePos)
                    swipeRefreshLayout.setEnabled(true);
                else
                    swipeRefreshLayout.setEnabled(false);
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
        switch (section_number) {
            case 1:
                adapter = new RoomsGridAdapter(getActivity(), getUserRooms(), getChildFragmentManager());
                grid.setAdapter(adapter);
                if ( adapter.getItems().isEmpty() ) NoResults.show(rootView);
                else NoResults.hide(rootView);
                break;
            case 2:
                adapter = new RoomsGridAdapter(getActivity(), getOtherRooms(), getChildFragmentManager());
                grid.setAdapter(adapter);
                if ( adapter.getItems().isEmpty() ) NoResults.show(rootView);
                else NoResults.hide(rootView);
                break;
            case 3:
                adapter = new RoomsGridAdapter(getActivity(), getEnteredRooms(), getChildFragmentManager());
                grid.setAdapter(adapter);
                if ( adapter.getItems().isEmpty() ) NoResults.show(rootView);
                else NoResults.hide(rootView);
                break;

        }
    }

    void cacheRoomsThumbnails(List<Room> list){
        for(Room r : list) {
            final Room room = r;
            new RandomSongFromRoomAsyncTask(getContext()){
            @Override
            public void onPreExecute(){}

            @Override
            public void onPostExecute(Song song){
                //super.onPostExecute(song);
                if(song != null)
                    new DownloadImageTask(null, room.getId().toString()).execute(song.getThumbnailURL());
            }
            }.execute(r);
        }
    }

    void updateRoomsGrid(){
        final Context fragment = getContext();
        new GetRoomsByUserAsyncTask(fragment){

            @Override
            public void onPreExecute(){}

            @Override
            public void onPostExecute(List<Room> result){

                userRooms = result;
                Log.d("xd",LoginActivity.currentUser.getId()+"");
                cacheRoomsThumbnails(userRooms);

                new GetNearyByRoomsAsyncTask(fragment){

                    @Override
                    public void onPreExecute(){}

                    @Override
                    public void onPostExecute(List<Room> rooms){
                        otherRooms = rooms;
                        cacheRoomsThumbnails(otherRooms);
                        new GetEnteredRoomsAsyncTask(fragment){
                            @Override
                            public void onPreExecute(){}

                            @Override
                            public void onPostExecute( List<UserEnteredRoom> data ){
                                //super.onPostExecute(data);
                                enteredRooms = new ArrayList<>();
                                cacheRoomsThumbnails(enteredRooms);
                                for(UserEnteredRoom d : data){
                                    enteredRooms.add(d.getRoom());
                                }
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
        }.execute(LoginActivity.currentUser);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CREATE_NEW_ROOM_ACTION:
            case GO_TO_ROOM_ACTION:
                if ( resultCode == AppCompatActivity.RESULT_OK )
                    updateRoomsGrid();
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRefresh() {
        updateRoomsGrid();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Log.e("TAG", "HOLA MUNDO");
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}