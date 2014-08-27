package com.okry.amt.messagequeue;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import com.okry.amt.allbase.BaseActivity;

/**
 * Created by MR on 2014/6/17.
 */
public class MessageQueueTestActivity extends BaseActivity{

    private static final String TAG = MessageQueueTestActivity.class.getSimpleName();

    private Handler h1;
    private Handler h2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();
        t1.start();
        t2.start();
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        h1 = new MyHandler(t1.getLooper());
        h2 = new MyHandler(t2.getLooper());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            h1.sendEmptyMessage(0);
        } else if(event.getAction() == MotionEvent.ACTION_UP) {
            h2.sendEmptyMessage(0);
        }
        return super.onTouchEvent(event);
    }

    private static class MyHandler extends Handler {

        static int count;

        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            String threadName = getLooper().getThread().getName();
            Log.i(TAG, threadName + ":handleMessage " + count++);
        }
    }

    private static class Thread1 extends Thread {

        private Looper mLooper;

        public Thread1() {
            setName("Thread1");
        }

        public Looper getLooper() {
            return mLooper;
        }

        @Override
        public void run() {
            Log.i(TAG, getName() + "run");
            Looper.prepare();
            mLooper = Looper.myLooper();
            Looper.loop();
            while(true) {
                Log.i(TAG, getName() + "loop");
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Thread2 extends Thread {

        private Looper mLooper;

        public Thread2() {
            setName("Thread2");
        }

        public Looper getLooper() {
            return mLooper;
        }

        @Override
        public void run() {
            Log.i(TAG, getName() + "run");
            Looper.prepare();
            mLooper = Looper.myLooper();
            Looper.loop();
            while(true) {
                Log.i(TAG, getName() + "loop");
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
