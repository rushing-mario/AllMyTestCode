package com.okry.amt.ui.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.okry.amt.R;
import com.okry.amt.allbase.BaseActivity;

/**
 * Created by mr on 14-8-11.
 */
public class EmbeddedDialogActivity extends BaseActivity {

    public static void startActicvity(Context context) {
        Intent i  = new Intent(context, EmbeddedDialogActivity.class);
        context.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        EmbeddedDialogFragment f = new EmbeddedDialogFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f).commit();
    }


}
