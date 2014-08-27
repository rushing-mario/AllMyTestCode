package com.okry.amt.bitmap.resize;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by MR on 2014/4/22.
 */
public class ResizeBitmap {

    public static final Bitmap resizeBitmap(Bitmap src) {
        Matrix m = new Matrix();
//        m.postScale(.5f, .5f);
//        m.setScale(.5f, .5f);
        m.postScale(.5f, .5f);
//        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(),m, false);
        return Bitmap.createBitmap(src, 50, 50, src.getWidth() - 100, src.getHeight() - 100,m, true);
    }
}
