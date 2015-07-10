package com.okry.amt.io;

import android.os.Bundle;

import com.okry.amt.allbase.BaseActivity;

import java.nio.ByteBuffer;

/**
 * Created by mr on 15/6/19.
 */
public class NioActivity extends BaseActivity {

    byte[] b = new byte[5 * 1024 * 1024];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ByteBuffer.allocate(4 * 1024 * 1024);
        ByteBuffer buffer = ByteBuffer.allocateDirect(3 * 1024 * 1024);
        buffer.remaining();
    }

}
