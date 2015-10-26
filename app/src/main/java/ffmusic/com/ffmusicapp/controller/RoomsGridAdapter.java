package ffmusic.com.ffmusicapp.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ffmusic.backend.ffMusicApi.model.Room;

import java.util.ArrayList;
import java.util.List;

import ffmusic.com.ffmusicapp.R;

/**
 * {@link android.widget.BaseAdapter} personalizado para el gridview
 */
public class RoomsGridAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Room> items;

    public RoomsGridAdapter(Context c, List<Room> items) {
        mContext = c;
        if ( items == null )
            this.items = new ArrayList<>();
        else this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Room getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cardview_room, viewGroup, false);
        }

        final Room item = getItem(position);

        // Seteando Imagen
        // ImageView image = (ImageView) view.findViewById(R.id.imagen);
        // Glide.with(image.getContext()).load(1).into(image);

        // Seteando Nombre
        TextView name = (TextView) view.findViewById(R.id.nombre);
        name.setText(item.getName());

        // Seteando Descripción
        TextView descripcion = (TextView) view.findViewById(R.id.descripcion);
        descripcion.setText(item.getRoomOwner().getFullName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RoomActivity.class);
                intent.putExtra(RoomActivity.CURRENT_ROOM, item.getId());
                ((AppCompatActivity)mContext).startActivityForResult(intent, RoomsFragment.GO_TO_ROOM_ACTION);
            }
        });
        return view;
    }
}