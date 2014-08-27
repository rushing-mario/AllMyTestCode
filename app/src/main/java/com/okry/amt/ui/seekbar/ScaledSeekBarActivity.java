package com.okry.amt.ui.seekbar;

import android.os.Bundle;

import com.okry.amt.R;
import com.okry.amt.allbase.BaseActivity;

/**
 * Created by apple on 14-7-7.
 */
public class ScaledSeekBarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);
        PGSeekBar bar = (PGSeekBar) findViewById(R.id.pg_seek_bar);
        bar.setThumbDrawable(R.drawable.thumb_drawable);
        bar.setScaleDrawable(R.drawable.scale_drawable);
        bar.setLineWidth(2);
//        bar.setScaleWithTips(new String[]{"+0","+1","+2","+3","+4","+5","+6","+7","+8","+9","+10","+11","+12"});
        bar.setOrientation(false);
    }

}
