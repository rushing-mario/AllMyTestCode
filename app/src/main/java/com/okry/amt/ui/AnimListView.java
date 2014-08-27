package com.okry.amt.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by MR on 2014/3/27.
 */
public class AnimListView extends ListView{

    private int mCurrentFirstVisable = 0;

    public AnimListView(Context context) {
        this(context, null, -1);
    }
    public AnimListView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }
    public AnimListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        if(ev.getAction() == MotionEvent.ACTION_UP) {
//            mCurrentFirstVisable = getFirstVisiblePosition();
//            int top = getChildAt(0).getTop();
//            if(top < -250 / 2 && mCurrentFirstVisable < getAdapter().getCount() - 1) {
////                mCurrentFirstVisable ++;
////                smoothScrollBy(-top / 2, 200);
//            } else {
//                smoothScrollToPosition(mCurrentFirstVisable);
//            }
//        }
//        return super.onTouchEvent(ev);
//    }


    private void init() {
        setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d("statetest", "scrollState:" + scrollState);
//                if(scrollState == 0) {
            }

            @Override
            public void onScroll(AbsListView listview, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                View firstChild = listview.getChildAt(0);
                View secondChild = listview.getChildAt(1);
                View thirdChild = listview.getChildAt(2);
                if (firstChild == null) {
                    return;
                }
                int height = firstChild.getHeight();
                int top = firstChild.getTop();

                int firstHeight = 500 + top;
                int secondHeight = 250 - top;

                if (top <= 0) {
                    ((TextView) firstChild).setHeight(firstHeight);
                    ((TextView) secondChild).setHeight(secondHeight);
                }

                if(mCurrentFirstVisable != firstVisibleItem) {
                    if(firstVisibleItem < mCurrentFirstVisable) {
                        //向上滑动校验
                        ((TextView) thirdChild).setHeight(250);
                    }
                    mCurrentFirstVisable = firstVisibleItem;
                }

                Log.d("test", "firstVisItem:" + firstVisibleItem + ", t:" + top + ", firstHeight:" + firstHeight + ", secondHeight:" + secondHeight);
                for(int i = 0; i < listview.getChildCount(); i++) {
                    View v = listview.getChildAt(i);
                    if(v.getHeight() != 250 && i != 0 && i != 1) {
                        Log.e("test", "viewHeight[" + i + "]=" + v.getHeight());
                    }else {
                        Log.d("test", "viewHeight[" + i + "]=" + v.getHeight());
                    }
                }
            }
        });
    }
}
