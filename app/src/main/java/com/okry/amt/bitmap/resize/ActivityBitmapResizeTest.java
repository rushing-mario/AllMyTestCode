package com.okry.amt.bitmap.resize;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import com.okry.amt.R;
import com.okry.amt.allbase.BaseActivity;
import com.okry.amt.log.L;

/**
 * Created by MR on 2014/4/22.
 */
public class ActivityBitmapResizeTest extends BaseActivity{

    Bitmap bitmap;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        image = new ImageView(this);
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        setContentView(image);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.net_1000x1000);
        L.e(ActivityBitmapResizeTest.class.getSimpleName() + "src, w:[%d], h:[%d]", bitmap.getWidth(), bitmap.getHeight());
        image.setImageBitmap(bitmap);
        image.postDelayed(new Runnable() {
            @Override
            public void run() {
                Bitmap bmp2 = ResizeBitmap.resizeBitmap(bitmap);
                L.e(ActivityBitmapResizeTest.class.getSimpleName() + "resize, w:[%d], h:[%d]", bmp2.getWidth(), bmp2.getHeight());
                image.setImageBitmap(bmp2);
            }
        }, 2000);

    }
}
