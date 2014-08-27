package com.okry.amt.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import com.okry.amt.R;
import com.okry.amt.allbase.BaseActivity;

/**
 * Created by MR on 2014/4/15.
 */
public class PicMakerActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView imageView = new ImageView(this);
        setContentView(imageView);

        PicMaker maker = new PicMaker();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.net_1000x1000);
        Bitmap bitmap1 = Bitmap.createBitmap(900, 1600, Bitmap.Config.ALPHA_8);
        Bitmap bitmap2 = Bitmap.createBitmap(1200, 1600, Bitmap.Config.ALPHA_8);
        Bitmap bitmap3 = Bitmap.createBitmap(640, 960, Bitmap.Config.ALPHA_8);
        Bitmap bitmap4 = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ALPHA_8);
        Bitmap bitmap5 = Bitmap.createBitmap(1600, 900, Bitmap.Config.ALPHA_8);

        bitmap = maker.make(bitmap, 600, 800);
        bitmap1 = maker.make(bitmap1, 600, 800);
        bitmap2 = maker.make(bitmap2, 600, 800);
        bitmap3 = maker.make(bitmap3, 600, 800);
        bitmap4 = maker.make(bitmap4, 600, 800);
        bitmap5 = maker.make(bitmap5, 600, 800);

        imageView.setImageBitmap(bitmap);
    }

}
