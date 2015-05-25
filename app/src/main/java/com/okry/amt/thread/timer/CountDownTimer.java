package com.okry.amt.thread.timer;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by mr on 15/5/25.
 */
public class CountDownTimer implements ICountDownTimer {

    private final static int DEFAULT_COUNT_TIME = 3;

    private int mCountTime = DEFAULT_COUNT_TIME;

    private int mCurrentTime;

    private Set<OnCountDownListener> mListenerSet;

    private Timer mTimer;

    private AtomicBoolean mIsInCounting;

    private Handler mMainHandler;

    public CountDownTimer() {
        mMainHandler = new Handler(Looper.getMainLooper());
        mIsInCounting = new AtomicBoolean(false);
        mListenerSet = new HashSet();
    }

    @Override
    public void setCountDown(int countDown) {
        if(countDown <= 0) {
            throw new IllegalArgumentException("Count down time must > 0, current is :" + countDown);
        }
        if(mIsInCounting.get() && mTimer != null) {
            // reset count down, cancel
            mTimer.cancel();
        }
        mCountTime = countDown;
    }

    @Override
    public void start() {
        if(mIsInCounting.get() && mTimer != null) {
            mTimer.cancel();
        }
        mTimer = new Timer();
        mCurrentTime = mCountTime;
        callbackStart(mCurrentTime);
        mIsInCounting.set(true);
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mCurrentTime--;
                mMainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callbackCount(mCurrentTime);
                    }
                });
                if(mCurrentTime <= 0) {
                    mIsInCounting.set(false);
                    mMainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callbackFinished();
                        }
                    });
                    mTimer.cancel();
                }
            }
        }, 1000, 1000);
    }

    @Override
    public void cancel() {
        if(mIsInCounting.getAndSet(false)) {
            callbackCanceled();
        }
        if(mTimer != null) {
            mTimer.cancel();
        }
    }

    @Override
    public void addOnCountDownListener(OnCountDownListener listener) {
        mListenerSet.add(listener);
    }

    @Override
    public void removeOnCountDownListener(OnCountDownListener listener) {
        mListenerSet.remove(listener);
    }

    private void callbackStart(int time) {
        if(mListenerSet != null) {
            Iterator<OnCountDownListener> it = mListenerSet.iterator();
            while(it.hasNext()) {
                OnCountDownListener listener = it.next();
                listener.onStart(time);
            }
        }
    }

    private void callbackCount(int time) {
        if(mListenerSet != null) {
            Iterator<OnCountDownListener> it = mListenerSet.iterator();
            while(it.hasNext()) {
                OnCountDownListener listener = it.next();
                listener.onCounting(time);
            }
        }
    }

    private void callbackFinished() {
        if(mListenerSet != null) {
            Iterator<OnCountDownListener> it = mListenerSet.iterator();
            while(it.hasNext()) {
                OnCountDownListener listener = it.next();
                listener.onFinished();
            }
        }
    }

    private void callbackCanceled() {
        if(mListenerSet != null) {
            Iterator<OnCountDownListener> it = mListenerSet.iterator();
            while(it.hasNext()) {
                OnCountDownListener listener = it.next();
                listener.onCanceled();
            }
        }
    }


}
