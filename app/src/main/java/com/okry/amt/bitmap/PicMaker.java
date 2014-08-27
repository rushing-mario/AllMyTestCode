package com.okry.amt.bitmap;

/**
 * Created by MR on 2014/4/16.
 */

import android.graphics.Bitmap;
import com.okry.amt.log.L;

/**
 * 制作标准的图片尺寸
 */
public class PicMaker {

    private static final String TAG = PicMaker.class.getSimpleName() + ", ";

    public static Bitmap make(Bitmap bitmap, int targetWidth, int targetHeight) {
        if(bitmap == null || bitmap.isRecycled() || bitmap.getWidth() == 0 || bitmap.getHeight() == 0) {
            L.d(TAG + "Illegal input bitmap!");
            return null;
        }
        float standardRate = (float)targetWidth / targetHeight;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if(width == targetWidth && height == targetHeight) {
            L.d(TAG + "Original bitmap match the require size");
            return bitmap;
        } else if(width < targetWidth || height < targetHeight){
            // 尺寸不符合要求
            L.d(TAG + "Illegal input bitmap size![w:%d]x[h:%d]", width, height);
            return bitmap;
        } else {
            // 需要缩放或裁减
            // 缩放
            Bitmap outBitmap;
            float rate = (float)width / height;
            int requireWidth, requireHeight;
            if(rate > standardRate) {
                requireHeight = targetHeight;
                requireWidth = (int) (targetHeight * rate);
            } else {
                requireWidth = targetWidth;
                requireHeight = (int) (targetWidth / rate);
            }
            int maxLength = requireHeight > requireWidth ? requireHeight : requireWidth;
            outBitmap = BitmapUtils.scaleBitmap(bitmap, maxLength);
            outBitmap = getCropImage(outBitmap, standardRate);
            int outWidth = outBitmap.getWidth();
            int outHeight = outBitmap.getHeight();
            L.d(TAG + "outBitmap[w:%d]x[h:%d]", outWidth, outHeight);
            return outBitmap;
        }
    }

    private Bitmap scale(Bitmap bitmap) {
        return null;
    }

    public static Bitmap getCropImage(Bitmap bitmap, float rate) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= 0 || height <= 0) {
            return null;
        }

        if (width < height) {
            float currentRate = (float)width / height;
            if (currentRate == rate) {
                return bitmap;
            }

            if (currentRate < rate) {
                int h = (int)(width / rate + 0.5f);
                int w = width;
                int x = 0;
                int y = (height - h) / 2;
                Bitmap newBitmap = Bitmap.createBitmap(bitmap, x, y, w, h);
                return newBitmap;
            } else {
                int h = height;
                int w = (int)(height * rate + 0.5f);
                int x = (width - w) / 2;
                int y = 0;
                Bitmap newBitmap = Bitmap.createBitmap(bitmap, x, y, w, h);
                return newBitmap;
            }
        } else {
            float currentRate = (float)width / height;
            if (currentRate == rate) {
                return bitmap;
            }

            if (currentRate > rate) {
                int h = height;
                int w = (int)(height * rate + 0.5f);
                int x = (width - w) / 2;
                int y = 0;
                Bitmap newBitmap = Bitmap.createBitmap(bitmap, x, y, w, h);
                return newBitmap;
            } else {
                int h = (int)(width / rate + 0.5f);
                int w = width;
                int x = 0;
                int y = (height - h) / 2;
                Bitmap newBitmap = Bitmap.createBitmap(bitmap, x, y, w, h);
                return newBitmap;
            }
        }
    }

}
