package com.okry.amt.ui.seekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.okry.amt.util.UiUtil;

/**
 * Created by marui on 13-12-24.
 * SeekBar,支持中空的thumb;
 * 支持横向和竖向，默认竖向，使用setOrientation改变方向;
 * 使用LinePaint1、LinePaint2、ThumbPaint控制绘制参数;
 */
public class PGSeekBar extends View {

    public static final int SCALE_INFINITY =  -1;
    //seek parameters
    private float mSeekRate;
    private int mSeekLength;
    private int mSeekLineStart;
    private int mSeekLineEnd;
    private OnSeekChangedListener mListener;
    private OnDrawListener mOnDrawListener;
    //thumb parameters
    private Paint mThumbPaint;
    private int mThumbOffset;
    protected int mThumbRadius = UiUtil.dpToPixel(6);
    @Deprecated private int mThumbStorkeWidth = 0;
    protected Drawable mThumbDrawable;
    //line parameters
    private Paint mLinePaint1;
    private Paint mLinePaint2;
    private int mLineWidth = 1;
    //touch parameters
    protected GestureDetector mGestureDetector;
    //animation parameters
    private Scroller mScroller;
    //orientation parameters
    private boolean mIsVertical = true;
    private int mScaleCount = SCALE_INFINITY;
    // Scale parameters
    private Drawable mScaleDrawable;
    private String[] mScaleText;
    private boolean mShowScaleTip;
    private Paint mScalePaint;
    private float mExtraSizeForScaleText;
    private float mScaleTextPaddingBottom;

    public PGSeekBar(Context context) {
        this(context, null);
    }

    public PGSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext());
        mGestureDetector = new GestureDetector(getContext(), new SeekBarGestureListener());
        mThumbPaint = new Paint();
        mThumbPaint.setAntiAlias(true);
        mThumbPaint.setColor(Color.WHITE);
        mThumbPaint.setStrokeWidth(mThumbStorkeWidth);
        mThumbPaint.setStyle(Paint.Style.STROKE);
        mLinePaint1 = new Paint();
        mLinePaint1.setAntiAlias(true);
        mLinePaint1.setColor(Color.WHITE);
        mLinePaint1.setAlpha(200);
        mLinePaint2 = new Paint();
        mLinePaint2.setAntiAlias(true);
        mLinePaint2.setColor(Color.WHITE);
        mLinePaint2.setAlpha(200);

        mScalePaint = new Paint();
        mScalePaint.setAntiAlias(true);
        mScalePaint.setTextSize(45);
        mScalePaint.setColor(Color.WHITE);
        mScalePaint.setAlpha(200);
        mScaleTextPaddingBottom = 20;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mIsVertical) {
            int wmode = MeasureSpec.getMode(widthMeasureSpec);
            int wsize;
            int hsize = MeasureSpec.getSize(heightMeasureSpec);
            if (wmode == MeasureSpec.AT_MOST) {
                wsize = (mThumbRadius + mThumbStorkeWidth) * 2 > mLineWidth ? (mThumbRadius + mThumbStorkeWidth) * 2 : mLineWidth;
                if (mThumbDrawable != null) {
                    wsize = wsize > mThumbDrawable.getIntrinsicWidth() ? wsize : mThumbDrawable.getIntrinsicWidth();
                }
                wsize += getPaddingLeft() + getPaddingRight();
                if(mScalePaint != null && mShowScaleTip) {
                    mExtraSizeForScaleText = mScalePaint.getTextSize();
                    wsize += mExtraSizeForScaleText;
                    wsize += mScaleTextPaddingBottom;
                }
                setMeasuredDimension(wsize, hsize);
            } else {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        } else {
            int hmode = MeasureSpec.getMode(heightMeasureSpec);
            int hsize;
            int wsize = MeasureSpec.getSize(widthMeasureSpec);
            if (hmode == MeasureSpec.AT_MOST) {
                hsize = (mThumbRadius + mThumbStorkeWidth) * 2 > mLineWidth ? (mThumbRadius + mThumbStorkeWidth) * 2 : mLineWidth;
                if (mThumbDrawable != null) {
                    hsize = hsize > mThumbDrawable.getIntrinsicHeight() ? hsize : mThumbDrawable.getIntrinsicHeight();
                }
                hsize += getPaddingTop() + getPaddingBottom();
                if(mScalePaint != null && mShowScaleTip) {
                    mExtraSizeForScaleText = mScalePaint.getTextSize();
                    hsize += mExtraSizeForScaleText;
                    hsize += mScaleTextPaddingBottom;
                }
                setMeasuredDimension(wsize, hsize);
            } else {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mShowScaleTip) {
            canvas.save();
            if(mIsVertical) {
                canvas.translate(mExtraSizeForScaleText + mScaleTextPaddingBottom, 0);
            } else {
                canvas.translate(0, mExtraSizeForScaleText + mScaleTextPaddingBottom);
            }
        }
        if (mSeekLength == 0) {
            //初始化参数
            if (mIsVertical) {
                final int height = getHeight();
                mSeekLength = height - getPaddingTop() - getPaddingBottom() - mThumbRadius * 2 - mThumbStorkeWidth * 2;
                mSeekLineStart = getPaddingTop() + mThumbRadius + mThumbStorkeWidth;
                mSeekLineEnd = height - getPaddingBottom() - mThumbRadius - mThumbStorkeWidth;
            } else {
                final int width = getWidth();
                mSeekLength = width - getPaddingLeft() - getPaddingRight() - mThumbRadius * 2 - mThumbStorkeWidth * 2;
                mSeekLineStart = getPaddingLeft() + mThumbRadius + mThumbStorkeWidth;
                mSeekLineEnd = width - getPaddingRight() - mThumbRadius - mThumbStorkeWidth;
            }
            mThumbOffset = (int) (mSeekLength * mSeekRate);
        }
        //draw line
        if (mIsVertical) {
            final int left = getPaddingLeft() + mThumbRadius + mThumbStorkeWidth / 2 - mLineWidth / 2;
            final int right = getPaddingLeft() + mLineWidth + mThumbRadius + mThumbStorkeWidth / 2 - mLineWidth / 2;
            final int bottom1 = mSeekLineEnd - mThumbOffset + mThumbStorkeWidth / 2 - mThumbRadius;
            if (bottom1 > mSeekLineStart) {
                canvas.drawRect(left, mSeekLineStart, right, bottom1, mLinePaint2);
            }
            final int top2 = bottom1 + mThumbRadius * 2;
            if (mSeekLineEnd > top2) {
                canvas.drawRect(left, top2, right, mSeekLineEnd, mLinePaint1);
            }
        } else {
            final int top = getPaddingTop() + mThumbRadius + mThumbStorkeWidth / 2 - mLineWidth / 2;
            final int bottom = getPaddingTop() + mLineWidth + mThumbRadius + mThumbStorkeWidth / 2 - mLineWidth / 2;
            final int right1 = mSeekLineStart + mThumbOffset + mThumbStorkeWidth / 2 - mThumbRadius;

            if (right1 > mSeekLineStart) {
                canvas.drawRect(mSeekLineStart, top, right1, bottom, mLinePaint1);
            }
            final int left2 = right1 + mThumbRadius * 2;
            if (mSeekLineEnd > left2) {
                canvas.drawRect(left2, top, mSeekLineEnd, bottom, mLinePaint2);
            }

            if(mOnDrawListener!=null){
                mOnDrawListener.onHorizontalDrawLineFinish(canvas,mSeekLineStart,right1,left2,mSeekLineEnd);
            }
        }
        //draw thumb
        final int thumbX;
        final int thumbY;
        if (mIsVertical) {
            thumbX = mThumbRadius + mThumbStorkeWidth / 2 + getPaddingLeft();
            thumbY = mSeekLineEnd - mThumbOffset;
        } else {
            thumbY = mThumbRadius + mThumbStorkeWidth / 2 + getPaddingTop();
            thumbX = mSeekLineStart + mThumbOffset;
        }
        //draw scale
        if (mScaleDrawable != null && mScaleCount != SCALE_INFINITY) {
            float scaleLength = (float)mSeekLength / (mScaleCount - 1);
            for (int i = 0; i < mScaleCount; i++) {
                int width = mScaleDrawable.getIntrinsicWidth();
                int height = mScaleDrawable.getIntrinsicHeight();
                if(mIsVertical) {
                    int l = (int) (mSeekLineStart + scaleLength * i - width / 2);
                    int t = thumbY - height / 2;
                    int r = (int) (mSeekLineStart + scaleLength * i + width / 2);
                    int b = getPaddingTop() + height;
                    mScaleDrawable.setBounds(l, t, r, b);
                } else {
                    int l = (int) (mSeekLineStart + scaleLength * i - width / 2);
                    int t = thumbY - height / 2;
                    int r = (int) (mSeekLineStart + scaleLength * i + width / 2);
                    int b = thumbY + height / 2;
                    mScaleDrawable.setBounds(l, t, r, b);
                }
                mScaleDrawable.draw(canvas);
            }
        }
        //        canvas.drawCircle(thumbX, thumbY, mThumbRadius, mThumbPaint);
        if (mThumbDrawable != null) {
            int tdWidth = mThumbDrawable.getIntrinsicWidth();
            int tdHeight = mThumbDrawable.getIntrinsicHeight();
            final int thumbDrawableLeft = thumbX - tdWidth / 2;
            final int thumbDrawableTop = thumbY - tdHeight / 2;
            final int thumbDrawableRight = thumbX + tdWidth / 2;
            final int thumbDrawableBottom = thumbY + tdHeight / 2;
            mThumbDrawable.setBounds(thumbDrawableLeft, thumbDrawableTop, thumbDrawableRight, thumbDrawableBottom);
            mThumbDrawable.draw(canvas);
        }
        if(mShowScaleTip) {
            canvas.restore();
            //draw scale text
            if(mShowScaleTip && mScaleText != null) {
                float scaleLength = (float)mSeekLength / (mScaleCount - 1);
                int scaleIndex = (int)(thumbX / scaleLength);
                float width = computeTextDrawWidth(mScaleText[scaleIndex]);
                canvas.drawText(mScaleText[scaleIndex], thumbX - width / 2, getPaddingTop() + mExtraSizeForScaleText, mScalePaint);
            }
        }
        if (mScroller.computeScrollOffset()) {
            mThumbOffset = mScroller.getCurrY();
            invalidate();
        }
        super.onDraw(canvas);
    }

    private int getThumbOffset(float pos) {
        int thumb = (int) pos;
        if (thumb < 0) {
            thumb = 0;
        } else if (thumb > mSeekLength) {
            thumb = mSeekLength;
        }
        if(mScaleCount != SCALE_INFINITY) {
            float scaleLength = (float)mSeekLength / (mScaleCount - 1);
            thumb = (int) (Math.round(thumb / scaleLength) * scaleLength);
        }
        return thumb;
    }


    /**
     * 设置滑动监听
     * @param listener
     */
    public void setOnSeekChangedListener(OnSeekChangedListener listener) {
        mListener = listener;
    }

    public void setmOnDrawListener(OnDrawListener listener){
        this.mOnDrawListener = listener;
    }

    public float getCurrentSeekValue() {
        return mSeekRate;
    }

    public int getProgress(){
        return Math.round(mSeekRate*100);
    }

    public void setProgress(int progress){
        setCurrentSeekValue(progress/100f);
    }

    /**
     * 设置当前Seek数值
     * @param currentValue from[0..1]
     */
    public void setCurrentSeekValue(float currentValue) {

        if (mSeekRate > 1) {
            mSeekRate = 1;
        } else if (mSeekRate < 0) {
            mSeekRate = 0;
        }
        mSeekRate = currentValue;
        mThumbOffset = (int) (mSeekLength * mSeekRate);
        invalidate();
    }

    /**
     * 设置额外的thumbDrawable
     * @param resId
     */
    public void setThumbDrawable(int resId) {
        mThumbDrawable = getResources().getDrawable(resId);
        mThumbRadius = mThumbDrawable.getIntrinsicHeight() / 2;
    }

    public void setScaleDrawable(int resId) {
        mScaleDrawable = getResources().getDrawable(resId);
    }

    public void setScaleWithTips(String[] scaleTips) {
        if(scaleTips != null && scaleTips.length > 0) {
            mScaleText = scaleTips;
            mScaleCount = scaleTips.length;
            mShowScaleTip = true;
        }
    }

    public void setScaleNoTip(int scaleCount) {
        mScaleCount = scaleCount;
        mShowScaleTip = false;
    }

    private float computeTextDrawWidth(String text) {
        return text.getBytes().length * mScalePaint.getTextSize() / 2;
    }

    /**
     * 设置线宽
     * @param mLineWidth
     */
    public void setLineWidth(int mLineWidth) {
        this.mLineWidth = mLineWidth;
    }

    /**
     * 设置SeekBar方向，默认为vertical
     * @param isVertical
     */
    public void setOrientation(boolean isVertical) {
        mIsVertical = isVertical;
        requestLayout();
    }

    public void setLinePaintLeft(Paint paint){
        mLinePaint1 = paint;
    }

    public void setLinePaint2(Paint paint){
        mLinePaint2 = paint;
    }

    public void setThumbPaint(Paint paint){
        mThumbPaint = paint;
    }

    public void setScalePaint(Paint paint) {
        mScalePaint = paint;
    }

    public interface OnSeekChangedListener {
        /**
         * @param rate from[0..1]
         */
        void onSeekValueChanged(float rate);
    }

    public static interface OnDrawListener{
        void onHorizontalDrawLineFinish(Canvas canvas, int left1, int right1, int left2, int right2);
    }



    private class SeekBarGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
//            if (mIsVertical) {
//                float ey = e2.getY();
//                mThumbOffset = getThumbOffset(mSeekLineEnd - ey);
//            } else {
//                float ex = e2.getX();
//                mThumbOffset = getThumbOffset(ex - mSeekLineStart);
//            }

            Log.e("", "1111, d:" + distanceX + ",e1:" + e1.getX() + ",e2:" +e2.getX());

            mThumbOffset -= (distanceX);
            mThumbOffset = getThumbOffset(mThumbOffset);


            if (mListener != null && mSeekLength != 0) {
                mSeekRate = (float) mThumbOffset / mSeekLength;
                mListener.onSeekValueChanged(mSeekRate);
            }
            invalidate();
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            final int offsetLength;
            if (mIsVertical) {
                offsetLength = getThumbOffset(mSeekLineEnd - e.getY());
                mScroller.startScroll(0, mThumbOffset, 0, offsetLength - mThumbOffset, 400);
            } else {
                offsetLength = getThumbOffset(e.getX() - mSeekLineStart);
                mScroller.startScroll(0, mThumbOffset, 0, offsetLength - mThumbOffset, 400);
            }
            if (mListener != null && mSeekLength != 0) {
                mSeekRate = (float) offsetLength / mSeekLength;
                mListener.onSeekValueChanged(mSeekRate);
            }
            invalidate();

            return true;
        }
    }
}

