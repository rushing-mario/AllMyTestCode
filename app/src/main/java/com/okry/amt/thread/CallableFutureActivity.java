package com.okry.amt.thread;

import android.os.Bundle;
import android.util.Log;

import com.okry.amt.allbase.BaseActivity;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by apple on 14-7-11.
 */
public class CallableFutureActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ExecutorService tp = Executors.newCachedThreadPool();
        int resultCount = 0;
        Log.d("test", "submit task");
        Future<Integer> result = tp.submit(new ComputeTask());
        Log.d("test", "task submitted");
        try {
            resultCount = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("test", "result get:" + resultCount);
    }

    private class ComputeTask implements Callable<Integer> {

        private int sum = 0;

        @Override
        public Integer call() throws Exception {
            Log.d("test", "start task compute");
            for(int i = 0; i < Integer.MAX_VALUE / 2; i++) {
                sum++;
            }
            return sum;
        }
    }
}
