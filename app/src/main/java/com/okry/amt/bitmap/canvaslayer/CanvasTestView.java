package com.okry.amt.bitmap.canvaslayer;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by MR on 2014/5/16.
 */
public class CanvasTestView extends View {
    private static final int RECOVER_COLOR = 0x80ffe116;

    private Paint mPaint;
    private Paint mCurrentPathPaint;
    private Paint mPathRecoverPaint;
    private Paint mPathErasePaint;
    private Paint mClearPaint;

    public CanvasTestView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CanvasTestView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CanvasTestView(Context context) {
        this(context, null, -1);
    }

    private void init() {
        mPaint = new Paint(Paint.DITHER_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(30);

        mPathErasePaint = new Paint(Paint.DITHER_FLAG);
        mPathErasePaint.setAntiAlias(true);
        mPathErasePaint.setDither(true);
        mPathErasePaint.setColor(Color.WHITE);
        mPathErasePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPathErasePaint.setStrokeJoin(Paint.Join.ROUND);
        mPathErasePaint.setStrokeCap(Paint.Cap.ROUND);
        mPathErasePaint.setStrokeWidth(30);

        mPathRecoverPaint = new Paint(Paint.DITHER_FLAG);
        mPathRecoverPaint.setAntiAlias(true);
        mPathRecoverPaint.setDither(true);
        mPathRecoverPaint.setColor(RECOVER_COLOR);
        mPathRecoverPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPathRecoverPaint.setStrokeJoin(Paint.Join.ROUND);
        mPathRecoverPaint.setStrokeCap(Paint.Cap.ROUND);
        mPathRecoverPaint.setStrokeWidth(30);
        mPathRecoverPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));

        mPaint.setStrokeWidth(30);
        mPathErasePaint.setStrokeWidth(30);
            mPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
            mPathErasePaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));

        mClearPaint = new Paint();
        mClearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mCurrentPathPaint = mPathErasePaint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();

        canvas.translate(100, 100);

        canvas.drawLine(0, 0, 100, 100, mPaint);

        canvas.translate(0, 100);
        canvas.drawLine(0, 0, 100, 100, mPathErasePaint);

        canvas.translate(0, 100);
        canvas.drawLine(0, 0, 100, 100, mPathRecoverPaint);
        canvas.drawLine(50,50, 150,150,mPathRecoverPaint);

        canvas.translate(0, 200);
        canvas.drawLine(0, 0, 100, 0, mPathRecoverPaint);

        canvas.restore();
    }
}
