package com.okry.amt.bitmap.jni;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.TextView;
import com.okry.amt.R;
import com.okry.amt.bitmap.PreMakeTest;

/**
 * Created by MR on 2014/4/18.
 */
public class BitmapJniActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        tv.setText("Jni Test");
        setContentView(tv);

        Bitmap test1 = Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
        byte[] b1 = PreMakeTest.bitmapToByte(test1);

        Bitmap test2 = Bitmap.createBitmap(10, 10, Bitmap.Config.ALPHA_8);
        byte[] b2 = PreMakeTest.bitmapToByte(test2);

        Bitmap test3 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        byte[] b3 = PreMakeTest.bitmapToByte(test3);

        Bitmap test4 = BitmapFactory.decodeResource(getResources(), R.drawable.red_green_test);
        byte[] b4 = PreMakeTest.bitmapToByte(test4);
        Bitmap test4t = PreMakeTest.byteToBitmap(b4);
    }
}
