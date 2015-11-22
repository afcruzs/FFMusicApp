package ffmusic.com.ffmusicapp.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
    private AppCompatActivity context;

    public RoomAdapter(ArrayList<RoomListModelItem> list, AppCompatActivity context) {
        this.list = list;
        this.context = context;
    }



    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search_rooms_play_list_item, viewGroup, false);
        v.setOnClickListener(this);
        ViewHolder view = new ViewHolder(v,context);

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
        private Long id;
        private String name;

        public ViewHolder(View v, final AppCompatActivity mContext) {
            super(v);
            roomName = (TextView)v.findViewById(R.id.name_room);
            roomOwnerName = (TextView)v.findViewById(R.id.name_room_owner);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, RoomActivity.class);
                    intent.putExtra(RoomActivity.CURRENT_ROOM, id);
                    intent.putExtra(RoomActivity.CURRENT_ROOM_NAME, name);
                    mContext.startActivityForResult(intent, RoomsFragment.GO_TO_ROOM_ACTION);
                }
            });
        }

        public void bindHolder(RoomListModelItem t) {
            roomName.setText(t.getRoomName());
            roomOwnerName.setText(t.getRoomOwnerName());
            this.id = t.getRoomId();
            name = t.getRoomName();
        }
    }
}
