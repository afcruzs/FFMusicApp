package ffmusic.com.ffmusicapp.controller;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ffmusic.com.ffmusicapp.R;

/**
 * Created by PC on 29/10/2015.
 */
public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<RoomListModelItem> list;
    private View.OnClickListener listener;

    public RoomAdapter(ArrayList<RoomListModelItem> list) {
        this.list = list;
    }



    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search_rooms_play_list_item, viewGroup, false);
        v.setOnClickListener(this);
        ViewHolder view = new ViewHolder(v);

        return view;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RoomListModelItem item = list.get(position);
        holder.bindHolder(item);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if( listener!=null ){
            listener.onClick(v);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView roomName;
        private TextView roomOwnerName;


        public ViewHolder(View v) {
            super(v);
            roomName = (TextView)v.findViewById(R.id.name_room);
            roomOwnerName = (TextView)v.findViewById(R.id.name_room_owner);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("HOLA", "CLICK ON " + roomName);
                }
            });
        }

        public void bindHolder(RoomListModelItem t) {

            roomName.setText(t.getRoomName());
            roomOwnerName.setText(t.getRoomOwnerName());

        }
    }
}
