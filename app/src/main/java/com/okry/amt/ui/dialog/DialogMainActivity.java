package com.okry.amt.ui.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.okry.amt.R;
import com.okry.amt.allbase.BaseActivity;
import com.okry.amt.log.L;

/**
 * Created by mr on 14-8-8.
 */
public class DialogMainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        setContentView(ll);
        Button b1 = new Button(this);
        b1.setText("b1:fragment dialog");
        b1.setTag(1);
        b1.setOnClickListener(this);

        Button b2 = new Button(this);
        b2.setText("b2:no fragment dialog");
        b2.setTag(2);
        b2.setOnClickListener(this);

        Button b3 = new Button(this);
        b3.setText("b3:list dialog");
        b3.setTag(3);
        b3.setOnClickListener(this);

        Button b4 = new Button(this);
        b4.setText("b4:single choice list dialog");
        b4.setTag(4);
        b4.setOnClickListener(this);

        Button b5 = new Button(this);
        b5.setText("b5:multi choice list dialog");
        b5.setTag(5);
        b5.setOnClickListener(this);

        Button b6 = new Button(this);
        b6.setText("b6:custom view dialog");
        b6.setTag(6);
        b6.setOnClickListener(this);

        Button b7 = new Button(this);
        b7.setText("b7:show embedded view dialog");
        b7.setTag(7);
        b7.setOnClickListener(this);

        Button b8 = new Button(this);
        b8.setText("b8:show embedded as fragment");
        b8.setTag(8);
        b8.setOnClickListener(this);

        Button b9 = new Button(this);
        b9.setText("b9:show embedded as fragment");
        b9.setTag(9);
        b9.setOnClickListener(this);

        // add views
        ll.addView(b1);
        ll.addView(b2);
        ll.addView(b3);
        ll.addView(b4);
        ll.addView(b5);
        ll.addView(b6);
        ll.addView(b7);
        ll.addView(b8);
        ll.addView(b9);
    }

    @Override
    public void onClick(View v) {
        if (v.getTag().toString().equals("1")) {
            MyDialogFragment f = new MyDialogFragment();
            f.show(getSupportFragmentManager(), "dialog_fragment");
        } else if (v.getTag().toString().equals("2")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Dialog Message").setTitle("Dialog").setIcon(R.drawable.ic_launcher)
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            L.e("which:" + which);
                        }
                    });
            builder.show();
        } else if (v.getTag().toString().equals("3")) {
            ListDialogFragment f = new ListDialogFragment();
            f.show(getSupportFragmentManager(), "list_dialog_fragment");
        } else if (v.getTag().toString().equals("4")) {
            SingleChoiceListDialogFragment f = new SingleChoiceListDialogFragment();
            f.show(getSupportFragmentManager(), "single_choice_list_dialog_fragment");
        } else if (v.getTag().toString().equals("5")) {
            MultiChoiceListDialogFragment f = new MultiChoiceListDialogFragment();
            f.show(getSupportFragmentManager(), "multi_choice_list_dialog_fragment");
        } else if (v.getTag().toString().equals("6")) {
            CustomViewDialogFragment f = new CustomViewDialogFragment();
            f.show(getSupportFragmentManager(), "custom_view_dialog_fragment");
        } else if (v.getTag().toString().equals("7")) {
            EmbeddedDialogFragment f = new EmbeddedDialogFragment();
            f.show(getSupportFragmentManager(), "embedded_view_dialog_fragment");
        } else if (v.getTag().toString().equals("8")) {
            EmbeddedDialogActivity.startActicvity(this);
        } else if (v.getTag().toString().equals("9")) {
            EmbeddedDialogActivityAsDialogTheme.startActicvity(this);
        }
    }

}
