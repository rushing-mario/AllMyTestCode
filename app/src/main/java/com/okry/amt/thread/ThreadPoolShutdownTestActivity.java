package com.okry.amt.thread;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.okry.amt.R;

import java.util.concurrent.*;

public class ThreadPoolShutdownTestActivity extends Activity {

    ExecutorService threadPool;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TextView tv = ((TextView) findViewById(R.id.text));
        tv.setText("Thread pool shutdown test");

        threadPool = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 15; i++) {
            MyTask task = new MyTask(i);
            task.executeOnExecutor(threadPool);
        }

        threadPool.shutdownNow();
        MyTask newTask = new MyTask(100);
        try {
            newTask.executeOnExecutor(threadPool);
        } catch (Exception e) {
            tv.setText(e.toString());
        }
    }

    private class MyTask extends AsyncTask<Void, Void, Void> {

        private int mNum;

        public MyTask(int i) {
            mNum = i;
            Log.e("test", "new num:" + mNum);
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.e("test", "run num:" + mNum);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
