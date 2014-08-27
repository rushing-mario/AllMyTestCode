package com.okry.amt.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.okry.amt.R;
import com.okry.amt.log.L;

/**
 * Created by mr on 14-8-8.
 */
public class ListDialogFragment extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("DialogFragment").setIcon(R.drawable.ic_launcher)
        .setItems(new String[]{"item1", "item2", "item3"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "which=" + which, Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
    }

}
