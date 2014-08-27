package com.okry.amt.ui.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import com.okry.amt.log.L;

/**
 * Created by MR on 2014/5/19.
 */
public class LogRelativeLayout extends RelativeLayout{
    public LogRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public LogRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs, -1);
    }

    public LogRelativeLayout(Context context) {
        super(context, null, -1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        L.d("onTouchEvent:[%s]", getTag());
        if(getTag().equals("child3_1"))return true;
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        L.d("onInterceptTouchEvent:[%s]", getTag());
        return super.onInterceptTouchEvent(ev);
    }
}
