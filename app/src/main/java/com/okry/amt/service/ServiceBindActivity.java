package com.okry.amt.service;

import android.content.Intent;
import android.os.Bundle;
import com.okry.amt.allbase.BaseActivity;

/**
 * Created by MR on 2014/4/7.
 */
public class ServiceBindActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent serviceIntent = new Intent(this, ServicePrintLog.class);
        //todo how to bind service?
    }

}
