package com.okry.amt.ui.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.okry.amt.allbase.BaseActivity;

/**
 * Created by mr on 14-8-12.
 */
public class ThemeDialogActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        setContentView(ll);
        Button b1 = new Button(this);
        b1.setText("b1:theme dialog");
        b1.setTag(1);
        b1.setOnClickListener(this);

        ll.addView(b1);
    }

    @Override
    public void onClick(View v) {
        if (v.getTag().toString().equals("1")) {
            ThemeDialogFragment f = new ThemeDialogFragment();
            f.show(getSupportFragmentManager(), "theme_dialog_fragment");
        }
    }
}
