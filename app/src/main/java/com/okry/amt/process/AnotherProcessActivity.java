package com.okry.amt.process;

import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.Log;
import com.okry.amt.allbase.BaseActivity;
import com.okry.amt.app.AMTApplication;
import com.okry.amt.util.SysUtil;

/**
 * Created by MR on 14-3-21.
 */
public class AnotherProcessActivity extends BaseActivity {

    private final String PROCESS_TAG = "process_tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationInfo info = getApplicationInfo();
        int pid = android.os.Process.myPid();
        Log.d(PROCESS_TAG, CLASS_TAG +", processName:" + info.processName + ", pid:" + pid + ", pname2:" + SysUtil.getCurProcessName(this));
        AMTApplication app = (AMTApplication) getApplication();
        Log.d(PROCESS_TAG, "pre appValue:" + app.value + ", " + app.getApplicationInfo().processName);
        app.value = "AnotherProcess";
        Log.d(PROCESS_TAG, "post appValue:" + app.value);

        //SharePreference Test
        SharedPreferences setting = getSharedPreferences("sp1",  MODE_PRIVATE);
        Log.d(PROCESS_TAG, "share_pref_test:" + setting.getString(ActivityProcessMain.SHARE_PREF_TEST1, null));
        setting.edit().putString(ActivityProcessMain.SHARE_PREF_TEST1, "AnotherProcessActivity").commit();

        SharedPreferences setting2 = getSharedPreferences("sp2",  MODE_MULTI_PROCESS);
        Log.d(PROCESS_TAG, "share_pref_test2:" + setting2.getString(ActivityProcessMain.SHARE_PREF_TEST2, null));
        setting2.edit().putString(ActivityProcessMain.SHARE_PREF_TEST2, "AnotherProcessActivity").commit();
    }
}
