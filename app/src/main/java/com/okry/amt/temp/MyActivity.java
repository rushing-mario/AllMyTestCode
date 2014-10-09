package com.okry.amt.temp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.okry.amt.R;
import com.okry.amt.allbase.BaseActivity;

public class MyActivity extends BaseActivity {

    ViewGroup mContainer;
    ImageView[] mImageViews;

    int[] imgRes = {R.drawable.img0, R.drawable.img1, R.drawable.img2,
            R.drawable.img3, R.drawable.img4, R.drawable.img5,
            R.drawable.img6, R.drawable.img7, R.drawable.img8,
            R.drawable.img9};
    private Handler myHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_container);
        mContainer = (ViewGroup) findViewById(R.id.img_container);
        mImageViews = new ImageView[mContainer.getChildCount()];
        for (int i = 0; i < mImageViews.length; i++) {
            mImageViews[i] = (ImageView) mContainer.getChildAt(i);
        }
//        loadImage();
        loadImage2();
    }

    private void loadImage() {
        for (int i = 0; i < mImageViews.length; i++) {
            mImageViews[i].setImageResource(imgRes[i]);
        }
    }

    private void loadImage2() {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < mContainer.getChildCount(); i++) {
                    final int index = i;
                    final Bitmap b = BitmapFactory.decodeResource(getResources(), imgRes[i]);
                    myHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mImageViews[index].setImageBitmap(b);
                        }
                    });
                }
            }
        }.start();
    }

}
