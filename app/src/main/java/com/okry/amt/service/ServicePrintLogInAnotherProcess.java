package com.okry.amt.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.okry.amt.log.L;

/**
 * Created by MR on 2014/4/6.
 * 该Service位于另外一个进程中
 */
public class ServicePrintLogInAnotherProcess extends Service {

    private static final String TAG = "service_[%s]";

    @Override
    public void onCreate() {
        super.onCreate();
        L.d(TAG, "onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        L.d(TAG, "onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.d(TAG, "onStartCommand");
        consumeLongTime();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        L.d(TAG, "onDestroy");
        super.onDestroy();
    }

    private void consumeLongTime() {
        int i = 0;
        while(i++ < 100) {
            L.d("service_consume_i = [%d]", i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
