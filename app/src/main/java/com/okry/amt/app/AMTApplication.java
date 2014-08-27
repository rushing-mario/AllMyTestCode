package com.okry.amt.app;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import com.okry.amt.util.SysUtil;

/**
 * Created by MR on 14-3-20.
 */
public class AMTApplication extends Application{

    private final String TAG = "app_lifecycle";
    private final String PROCESS_TAG = "process_tag";
    private final String CLASS_TAG = getClass().getSimpleName();

    public String value;

    private static AMTApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Log.d(TAG, CLASS_TAG + ":onCreate");
        ApplicationInfo info = getApplicationInfo();
        Log.d(PROCESS_TAG, CLASS_TAG + ":processName:" + info.processName + ", pname2:" + SysUtil.getCurProcessName(this));
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        mInstance = null;
        Log.d(TAG, CLASS_TAG + ":onTerminate");
    }

    public static AMTApplication getInstatnce() {
        return mInstance;
    }

}
