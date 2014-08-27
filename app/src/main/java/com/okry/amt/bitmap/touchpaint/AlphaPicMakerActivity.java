package com.okry.amt.bitmap.touchpaint;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import com.okry.amt.R;
import com.okry.amt.allbase.BaseActivity;

/**
 * Created by MR on 2014/4/30.
 */
public class AlphaPicMakerActivity extends BaseActivity{

    AlphaPicMaker maker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.net_1000x1000);



        int argb[] = new int[b.getWidth() * b.getHeight()];
        b.getPixels(argb, 0, b.getWidth(), 0, 0, b.getWidth(), b.getHeight());

        b = Bitmap.createBitmap(argb, b.getWidth(), b.getHeight(), Bitmap.Config.ARGB_8888);



        maker = new AlphaPicMaker(b);

        final ImageView img = new ImageView(this);
        setContentView(img);

        img.setImageBitmap(b);

        maker.setOnBitmapInvalidateListener(new AlphaPicMaker.OnBitmapInvalidateListener() {
            @Override
            public void onBitmapInvalidate(Bitmap bitmap) {
                img.setImageBitmap(bitmap);
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        maker.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
