package com.okry.amt.bitmap.canvaslayer;

import android.os.Bundle;
import com.okry.amt.allbase.BaseActivity;

/**
 * Created by MR on 2014/5/16.
 */
public class CanvasActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CanvasTestView(this));
    }
}
