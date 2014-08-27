package com.okry.amt.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.okry.amt.log.L;

/**
 * Created by MR on 2014/4/6.
 */
public class ServicePrintLog extends Service {

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
    public boolean onUnbind(Intent intent) {
        L.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        L.d(TAG, "onRebind");
        super.onRebind(intent);
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
