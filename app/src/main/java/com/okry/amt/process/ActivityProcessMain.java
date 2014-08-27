package com.okry.amt.process;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.okry.amt.allbase.BaseActivity;
import com.okry.amt.app.AMTApplication;
import com.okry.amt.util.SysUtil;

/**
 * Created by MR on 14-3-20.
 */
public class ActivityProcessMain extends BaseActivity{

    private final String PROCESS_TAG = "process_tag";

    public static final String SHARE_PREF_TEST1 = "share_pref_test1";
    public static final String SHARE_PREF_TEST2 = "share_pref_test2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationInfo info = getApplicationInfo();
        int pid = android.os.Process.myPid();
        Log.d(PROCESS_TAG, CLASS_TAG +", processName:" + info.processName + ", pid:" + pid + ", pname2:" + SysUtil.getCurProcessName(this));
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        Button b = new Button(this);
        b.setText("Another Process");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityProcessMain.this, AnotherProcessActivity.class);
                startActivity(i);
            }
        });
        Button b2 = new Button(this);
        b2.setText("Another Process2");
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityProcessMain.this, AnotherProcessActivity2.class);
                startActivity(i);
            }
        });

        Button b3 = new Button(this);
        b3.setText("Same Process");
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityProcessMain.this, SameProcessActivity.class);
                startActivity(i);
            }
        });
        ll.addView(b);
        ll.addView(b2);
        ll.addView(b3);
        setContentView(ll);

        AMTApplication app = (AMTApplication) getApplication();
        Log.d(PROCESS_TAG, "pre appValue:" + app.value + ", " + app.getApplicationInfo().processName);
        app.value = "ProcessMain";
        Log.d(PROCESS_TAG, "post appValue:" + app.value);

        //SharePreference Test
        SharedPreferences setting = getSharedPreferences("sp1",  MODE_PRIVATE);
        Log.d(PROCESS_TAG, "share_pref_test:" + setting.getString(ActivityProcessMain.SHARE_PREF_TEST1, null));
        setting.edit().putString(ActivityProcessMain.SHARE_PREF_TEST1, "ActivityProcessMain").commit();

        SharedPreferences setting2 = getSharedPreferences("sp2", MODE_MULTI_PROCESS);
        Log.d(PROCESS_TAG, "share_pref_test2:" + setting2.getString(ActivityProcessMain.SHARE_PREF_TEST2, null));
        setting2.edit().putString(ActivityProcessMain.SHARE_PREF_TEST2, "ActivityProcessMain").commit();
    }
}
