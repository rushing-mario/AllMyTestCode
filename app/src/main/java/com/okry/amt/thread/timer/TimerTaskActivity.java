package com.okry.amt.thread.timer;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.okry.amt.R;
import com.okry.amt.allbase.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by mr on 15/5/25.
 */
public class TimerTaskActivity extends BaseActivity implements ICountDownTimer.OnCountDownListener{

    @InjectView(R.id.count_down_input)
    EditText input;

    @InjectView(R.id.count_down_now)
    TextView text;

    private ICountDownTimer mTimer;

    @OnClick(R.id.count_down_start)
    void startCount() {
        mTimer.setCountDown(Integer.valueOf(input.getText().toString().trim()));
        mTimer.start();
    }

    @OnClick(R.id.count_down_cancel)
    void cancelCount() {
        mTimer.cancel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down);
        ButterKnife.inject(this);
        mTimer = new CountDownTimer();
        mTimer.addOnCountDownListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mTimer.cancel();
    }

    @Override
    public void onStart(int currentTime) {
        Log.d("OnCountDownListener", "onStart:" + currentTime);
        text.setText(String.valueOf(currentTime));
    }

    @Override
    public void onCanceled() {
        Log.d("OnCountDownListener", "onCanceled");
        text.setText("Cancel");
    }

    @Override
    public void onCounting(int currentTime) {
        Log.d("OnCountDownListener", "onCounting:" + currentTime);
        text.setText(String.valueOf(currentTime));
    }

    @Override
    public void onFinished() {
        Log.d("OnCountDownListener", "onFinished");
        text.setText("Finish");
    }
}
