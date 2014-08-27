package com.okry.amt.thread;

import android.os.Bundle;
import android.util.Log;

import com.okry.amt.allbase.BaseActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by apple on 14-7-11.
 */
public class JoinThreadTestActivity extends BaseActivity {

    private static final String TAG = JoinThreadTestActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainThread mt = new MainThread();
        mt.start();
    }

    private int mSum = 0;

    private class MainThread extends Thread {

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            Log.d(TAG, "start MainThread task");
            SubThread st = new SubThread();
            st.start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mSum += 1;
            Log.d(TAG, "Main thread compute end sum:" + mSum);
            try {
                st.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long end = System.currentTimeMillis();
            Log.d(TAG, "Thread end consume:" + (end - start) + ", sum:" + mSum);
            return;
        }
    }

    private class SubThread extends Thread {

        @Override
        public void run() {
            Log.d(TAG, "start SubThread task");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mSum += 2;
            Log.d(TAG, "SubThread end, sum" + mSum);
            return;
        }
    }

}
