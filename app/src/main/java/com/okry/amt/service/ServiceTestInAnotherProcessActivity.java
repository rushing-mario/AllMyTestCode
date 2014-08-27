package com.okry.amt.service;

import android.content.Intent;
import android.os.Bundle;
import com.okry.amt.allbase.BaseActivity;

/**
 * Created by MR on 2014/4/6.
 * 该Activity启动的Service位于另外一个进程中
 */
public class ServiceTestInAnotherProcessActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent service = new Intent(this, ServicePrintLogInAnotherProcess.class);
        startService(service);
    }
}
