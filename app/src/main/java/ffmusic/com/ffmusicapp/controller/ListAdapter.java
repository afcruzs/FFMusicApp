package ffmusic.com.ffmusicapp.controller;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ffmusic.com.ffmusicapp.R;

/**
 * Created by fabiankasUN on 14/10/2015.
 */
public class ListAdapter extends ArrayAdapter<ListModelItem> {


    private Context context;
    private List<ListModelItem> items;
    private int layoutResourceId;

    public ListAdapter(Context context, int layoutResourceId, ArrayList<ListModelItem> dataItem) {
        super(context, layoutResourceId, dataItem);
        this.items = dataItem;
        this.context = context;
        this.layoutResourceId = layoutResourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        AtomPaymentHolder holder = null;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);

        holder = new AtomPaymentHolder();
        holder.list = items.get(position);
        holder.deleteSong = (ImageButton)row.findViewById(R.id.delete_song);
        holder.name = (TextView)row.findViewById(R.id.name_song);
        //holder.like = (ImageButton) row.findViewById(R.id.like_buttom);
        row.setTag(holder);

        setupItem(holder);
        return row;
    }

    private void setupItem(AtomPaymentHolder holder) {
        holder.name.setText(holder.list.getName());
    }

    public static class AtomPaymentHolder {
        ListModelItem list;
        TextView name;
        ImageButton deleteSong;
        ImageButton like;
    }
}
