package com.okry.amt.thread.timer;

/**
 * Created by mr on 15/5/25.
 */
public interface ICountDownTimer {

    public void setCountDown(int countDown);

    public void start();

    public void cancel();

    public void addOnCountDownListener(OnCountDownListener listener);

    public void removeOnCountDownListener(OnCountDownListener listener);

    public interface OnCountDownListener {

        public void onStart(int currentTime);

        public void onCanceled();

        public void onCounting(int currentTime);

        public void onFinished();
    }

}
