package com.okry.amt.ui.animhoriscroll;

import android.content.Context;
import android.graphics.Interpolator;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.okry.amt.log.L;

/**
 * Created by marui on 13-11-21.
 */
public class LinearHoriScrollView extends HorizontalScrollView implements BaseHoriScrollItemAdapter.HoriDataSetObserver{

    //
    private int mShowCount;//一屏显示的个数
    private float mHalfCount = .5f;//一屏显示的个数超出的部分
    private int mChildWidth;
    private boolean mNeedMeasureChild = true;
    //
    private BaseHoriScrollItemAdapter mAdapter;
    private Context mContext;
    //Views
    private LinearLayout mContainer;

    public LinearHoriScrollView(Context context) {
        this(context, null);
    }

    public LinearHoriScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mContainer = new LinearLayout(context);
        mContainer.setGravity(Gravity.CENTER_VERTICAL);
        addView(mContainer, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public void setAdapter(BaseHoriScrollItemAdapter adapter) {
        if (adapter != null) {
            mAdapter = adapter;
            mAdapter.registerDataSetObserver(this);
            mAdapter.notifyDataSetChange();
        }else{
            mContainer.removeAllViews();
            mAdapter.unregisterDataSetObserver(this);
            mAdapter = null;
        }
    }

    private void resetViews() {
        mContainer.removeAllViews();
        for (int i = 0; i < mAdapter.getCount(); i++) {
            mContainer.addView(mAdapter.initView(this, getContext(), i), mChildWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onAdd(int position) {
        if(mNeedMeasureChild) {
            measureChildWidth();
        }
        mContainer.addView(mAdapter.initView(this, mContext, position), mChildWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onRemove(int position) {
        mContainer.removeViewAt(position);
    }

    @Override
    public void onInvalidated() {
        if(mNeedMeasureChild) {
            measureChildWidth();
        }
        resetViews();
    }

    private void measureChildWidth(){
        if(mShowCount <= 0) {
            mChildWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else {
            int screenWidth = getResources().getDisplayMetrics().widthPixels;
            mChildWidth = (int) (screenWidth / (mShowCount + mHalfCount));
        }
    }


    public LinearLayout getLinearContainer() {
        return mContainer;
    }

    /**
     * 平滑滑动至指定子view的位置
     * @param index
     */
    public void smoothScrollTo(int index){
        View childView = getItemView(index);
        if(childView != null){
            int scrollX = getScrollX();
            int viewWidth = getResources().getDisplayMetrics().widthPixels;
            int childLeft = childView.getLeft();
            if(childLeft < scrollX || childLeft > scrollX + viewWidth){
                smoothScrollTo(childLeft, 0);
            }
        }
    }

    /**
     * 平滑指定子view滚动至屏幕中央的位置
     * @param index
     */
    public void smoothScrollItemToCenter(int index){
        View childView = getItemView(index);
        if(childView != null){
            int childLeft = childView.getLeft();
            int containerWidth = getWidth();
            // 将item滚动至屏幕中间
            int scrollX = childLeft - (containerWidth - childView.getWidth()) / 2;
            smoothScrollTo(scrollX, 0);
        }
    }

    public View getItemView(int index){
        if(index >= 0 && index <=mContainer.getChildCount() - 1){
            return mContainer.getChildAt(index);
        }else{
            return null;
        }
    }

    public void deselectAll() {
        for (int i = 0; i < mContainer.getChildCount(); i++) {
            View v = mContainer.getChildAt(i);
            v.setSelected(false);
        }
    }

    /**
     * 设置一屏显示个数，可以设置为float形式，小数部分为一屏显示突出的部分
     * @param count
     */
    public void setItemCountOnScreen(float count) {
        mShowCount = (int)count;
        mHalfCount = count - mShowCount;
        mNeedMeasureChild = true;
    }


    /**
     * @param expandPosition 展开位置
     */
    public void startExpandAnim(int expandPosition) {
        final int firstVisible = getFirstVisibleChildIndex();
        final int lastVisible = getLastVisibleChildIndex();
        OvershootInterpolator interpolator = new OvershootInterpolator();
        for(int i = firstVisible; i <= lastVisible; i++) {
            View child = mContainer.getChildAt(i);
            TranslateAnimation anim = new TranslateAnimation(expandPosition - child.getWidth() * i ,0,0,0);
            anim.setDuration(600);
            anim.setInterpolator(interpolator);
            child.startAnimation(anim);
        }
    }

    /**
     * @param expandPosition 展开位置
     */
    public void startCollapseAnim(int expandPosition) {
        final int firstVisible = getFirstVisibleChildIndex();
        final int lastVisible = getLastVisibleChildIndex();
        AnticipateInterpolator interpolator = new AnticipateInterpolator();
        for(int i = firstVisible; i <= lastVisible; i++) {
            View child = mContainer.getChildAt(i);
            TranslateAnimation anim = new TranslateAnimation(0, expandPosition - child.getWidth() * i, 0,0);
            anim.setDuration(600);
            anim.setInterpolator(interpolator);
            anim.setFillAfter(true);
            child.startAnimation(anim);
        }
    }

    /**
     * 取得第一个可见子View
     * @return
     */
    public int getFirstVisibleChildIndex() {
        final int scrollX = getScrollX();
        for(int i = 0; i < mContainer.getChildCount(); i++) {
            View child = mContainer.getChildAt(i);
            int right = child.getRight();
            if(right > scrollX) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 取得最后一个可见子View
     * @return
     */
    public int getLastVisibleChildIndex() {
        final int firstVisible = getFirstVisibleChildIndex();
        final int scrollX = getScrollX();
        final int visibleWidth = getWidth();
        int lastVisible = firstVisible;
        if(firstVisible >= 0) {
            for(int i = firstVisible + 1; i < mContainer.getChildCount(); i++) {
                View child = mContainer.getChildAt(i);
                int left = child.getLeft();
                if (left > scrollX + visibleWidth) {
                    break;
                }
                lastVisible = i;
            }
        }
        return lastVisible;
    }


}

