package com.okry.amt.bitmap.touchpaint;

import android.graphics.*;
import android.view.MotionEvent;

/**
 * Created by MR on 2014/4/30.
 */
public class AlphaPicMaker {

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPaint;

    public OnBitmapInvalidateListener mListener;

    public static interface OnBitmapInvalidateListener {
        public void onBitmapInvalidate(Bitmap bitmap);
    }

    public AlphaPicMaker(Bitmap bitmap) {
        initPaint();
        mPath = new Path();
        if(!bitmap.isMutable()) {
            mBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        } else {
            mBitmap = bitmap;
        }
        mCanvas = new Canvas(mBitmap);
    }

    private void initPaint() {
        mPaint = new Paint(Paint.DITHER_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(0xff000000);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(30);
        mPaint.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.NORMAL));
    }

    public void setOnBitmapInvalidateListener(OnBitmapInvalidateListener listener) {
        mListener = listener;
    }

    private void touch_start(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;
            mCanvas.drawPath(mPath, mPaint);
            if(mListener != null) {
                mListener.onBitmapInvalidate(mBitmap);
            }
            mPath.reset();
            mPath.moveTo(mX, mY);
        }
    }
    private void touch_up() {
        mPath.lineTo(mX, mY);
        // commit the path to our offscreen
        mCanvas.drawPath(mPath, mPaint);
        if(mListener != null) {
            mListener.onBitmapInvalidate(mBitmap);
        }
        // kill this so we don't double draw
//            mPath.reset();
    }

    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                break;
        }
        return true;
    }

}
