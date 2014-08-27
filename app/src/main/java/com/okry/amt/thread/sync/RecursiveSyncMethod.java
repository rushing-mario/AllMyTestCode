package com.okry.amt.thread.sync;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.okry.amt.allbase.BaseActivity;

/**
 * Created by mr on 14-8-27.
 */
public class RecursiveSyncMethod extends BaseActivity {

    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout f = new FrameLayout(this);
        setContentView(f);
        Button b = new Button(this);
        b.setText("Start");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTest();
            }
        });
        f.addView(b, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void startTest() {
        a();
    }

    private void a() {
        count++;
        if (count == 4) {
            return;
        }
        Log.d("test", "1");
        synchronized (this) {
            Log.d("test", "2");
            a();
        }
        Log.d("test", "3");
    }

}
