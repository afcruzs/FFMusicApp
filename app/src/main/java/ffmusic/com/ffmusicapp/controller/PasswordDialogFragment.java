package ffmusic.com.ffmusicapp.controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ffmusic.backend.ffMusicApi.model.Room;

import ffmusic.com.ffmusicapp.R;

public class PasswordDialogFragment extends DialogFragment {

    private Room room;
    private EditText text;
    NoticeDialog mListener;
    private Context mContext;

    public  RoomsFragment newInstance(Room r, Context mContext) {
        RoomsFragment fragment = new RoomsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        this.room = r;
        this.mContext = mContext;

        return fragment;
    }

    public PasswordDialogFragment(){

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        final LayoutInflater inflater = getActivity().getLayoutInflater();



        //builder.setView(inflater.inflate(R.layout.dialog_password, null));
        final View inflator = inflater.inflate(R.layout.dialog_password, null);
        builder.setView(inflator);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                final EditText et1 = (EditText) inflator.findViewById(R.id.input_password_room_dialog);
                if( et1.getText().toString().equals( room.getPassword() )){
                    RoomsGridAdapter.STATE = room.getPassword();
                    final Intent intent = new Intent(mContext, RoomActivity.class);
                    intent.putExtra(RoomActivity.CURRENT_ROOM, room.getId());
                    ((AppCompatActivity) mContext).startActivityForResult(intent, RoomsFragment.GO_TO_ROOM_ACTION);

                }else {
                    Toast.makeText(getContext(),R.string.incorrect_pass, Toast.LENGTH_SHORT).show();
                    RoomsGridAdapter.STATE = "-1";
                }
            }
        });

        builder.setNegativeButton(R.string.cancel_exam, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        return builder.create();
    }

    /*@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialog) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }*/


}
