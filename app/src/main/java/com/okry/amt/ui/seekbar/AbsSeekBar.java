package com.okry.amt.ui.seekbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by apple on 14-7-7.
 */
public class AbsSeekBar extends View{


    private boolean mIsVertical;
    private OnSeekChangedListener mListener;

    public AbsSeekBar(Context context) {
        this(context, null);
    }

    public AbsSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    /**
     * 设置SeekBar方向，默认为vertical
     * @param isVertical
     */
    public void setOrientation(boolean isVertical) {
        mIsVertical = isVertical;
        requestLayout();
    }

    /**
     * 设置滑动监听
     * @param listener
     */
    public void setOnSeekChangedListener(OnSeekChangedListener listener) {
        mListener = listener;
    }

}
