//package com.okry.amt.ui.animhoriscroll;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Rect;
//import android.os.Handler;
//import android.os.Message;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.AlphaAnimation;
//import android.view.animation.Animation;
//import android.view.animation.AnimationSet;
//import android.view.animation.DecelerateInterpolator;
//import android.view.animation.TranslateAnimation;
//import android.widget.LinearLayout;
//import android.widget.Scroller;
//import java.util.HashMap;
//import java.util.Map;
//
//import us.pinguo.androidsdk.pgedit.PGEditTools;
//
///**
// * Created by hlf on 14-4-29.
// */
//public class PGEditHorizontalLayout extends LinearLayout {
//
//    private final static float DEF_COUNT = 3.8f;
//
//    private Scroller mScroller;
//    private LinearLayout layout;
//    private float count = DEF_COUNT;
//    private PGEditPtrHorizontalScrollView scrollView;
//
//    private Map<View,OnClickListener> mViewClickMap = new HashMap<View,OnClickListener>();
//
//    public PGEditHorizontalLayout(Context context) {
//        super(context);
//
//        LayoutInflater.from(context).inflate(PGEditTools.getLayout(context, "pg_sdk_edit_horizontal_layout"), this, true);
//        layout = (LinearLayout)findViewById(
//                PGEditTools.getId(context, "layout"));
//        scrollView = (PGEditPtrHorizontalScrollView) findViewById(
//                PGEditTools.getId(context, "scroll_view"));
//        mScroller = new Scroller(getContext(), new DecelerateInterpolator());
//    }
//
//    public PGEditHorizontalLayout(Context context, AttributeSet attrs) {
//        super(context, attrs);
//
//        LayoutInflater.from(context).inflate(PGEditTools.getLayout(context, "pg_sdk_edit_horizontal_layout"), this, true);
//        layout = (LinearLayout)findViewById(
//                PGEditTools.getId(context, "layout"));
//        scrollView = (PGEditPtrHorizontalScrollView) findViewById(
//                PGEditTools.getId(context, "scroll_view"));
//        mScroller = new Scroller(getContext(), new DecelerateInterpolator());
//    }
//
//    public void addChildView(View view, OnClickListener onClickListener, boolean enableAnimation){
//        ViewGroup.LayoutParams params = view.getLayoutParams();
//        if (null == params) {
//            params = new ViewGroup.LayoutParams(0, 0);
//        }
//
//        params.width = (int) (getMeasuredWidth() / count);
//        params.height = getMeasuredHeight();
//        view.setLayoutParams(params);
//        mViewClickMap.put(view, onClickListener);
//
//        if (enableAnimation) {
//            view.setOnClickListener(mOnItemClickListener);
//        } else {
//            view.setOnClickListener(onClickListener);
//        }
//        layout.addView(view);
//    }
//
//    public void addBackView(View view, int width){
//        ViewGroup.LayoutParams params = view.getLayoutParams();
//        if (null == params) {
//            params = new ViewGroup.LayoutParams(0, 0);
//        }
//
//        params.width = width;
//        params.height = getMeasuredHeight();
//        view.setLayoutParams(params);
//        layout.addView(view);
//    }
//
//    public void removeAllChildViews(){
//        mViewClickMap.clear();
//        layout.removeAllViews();
//
//    }
//
//    @Override
//    public void scrollBy(int x, int y) {
//        mScroller.startScroll(scrollView.getRefreshableView().getScrollX(), 0,
//                x - scrollView.getRefreshableView().getScrollX(), 0, 400);
//        invalidate();
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//        if (mScroller.computeScrollOffset()) {
//            int curX = mScroller.getCurrX();
//            scrollView.getRefreshableView().scrollTo(curX, 0);
//            invalidate();
//        }
//    }
//
//    private OnClickListener mOnItemClickListener = new OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Rect rect = new Rect();
//            v.getGlobalVisibleRect(rect);
//            int width = rect.width();
//            if (width < v.getMeasuredWidth()) {
//                int scrollLength = 2 * v.getMeasuredWidth() - width;
//                if (rect.right == getRight()) {
//                    scrollBy(scrollView.getRefreshableView().getScrollX() + scrollLength, 0);
//                } else {
//                    scrollBy(scrollView.getRefreshableView().getScrollX()
//                            - scrollLength - v.getMeasuredWidth(), 0);
//                }
//            } else if (rect.right == getRight()) {
//                scrollBy(scrollView.getRefreshableView().getScrollX() + v.getMeasuredWidth(), 0);
//
//            } else if (rect.left == getLeft()) {
//                scrollBy(scrollView.getRefreshableView().getScrollX()
//                        - v.getMeasuredWidth() - v.getMeasuredWidth(), 0);
//
//            } else if (getRight() - rect.right < v.getMeasuredWidth()) {
//                scrollBy(scrollView.getRefreshableView().getScrollX() + v.getMeasuredWidth() - (getRight() - rect.right), 0);
//
//            } else if (rect.left - getLeft() < v.getMeasuredWidth()) {
//                scrollBy(scrollView.getRefreshableView().getScrollX()
//                        - (v.getMeasuredWidth() - (rect.left - getLeft())) - v.getMeasuredWidth(), 0);
//            } else if (rect.left - getLeft() < v.getMeasuredWidth() * 2) {
//                scrollBy(scrollView.getRefreshableView().getScrollX()
//                        - (v.getMeasuredWidth() * 2 - (rect.left - getLeft())), 0);
//            }
//
//            if(mViewClickMap.get(v) != null){
//                mViewClickMap.get(v).onClick(v);
//            }
//        }
//    };
//
//    public OnClickListener getOnItemClickListener(){
//        return mOnItemClickListener;
//    }
//
//    public void setCount(float count) {
//        this.count = count;
//    }
//
//    public void showViewForTranslateAnimation(float right, int index, int displayWidth, Animation.AnimationListener animationListener) {
//
//        Rect rect = new Rect();
//        layout.getChildAt(index).getGlobalVisibleRect(rect);
//        float selectedRight = rect.right;
//
//        float lastRightDis = selectedRight;
//        int startPosition = 0;
//        for (int i = index; i >= 0; i--) {
//            lastRightDis -= layout.getChildAt(i).getLayoutParams().width;
//            if (lastRightDis > 0) {
//
//                continue;
//
//            } else {
//                startPosition = i;
//                break;
//            }
//        }
//
//        float nextLeftDis = selectedRight - layout.getChildAt(index).getLayoutParams().width;
//        int endPosition = index;
//        for (int i = index; i < layout.getChildCount(); i++) {
//            nextLeftDis += layout.getChildAt(i).getLayoutParams().width;
//
//            if (nextLeftDis < displayWidth) {
//
//                continue;
//
//            } else {
//                endPosition = i;
//                break;
//            }
//        }
//
//        float fromXDelta = right - layout.getChildAt(index).getLayoutParams().width - lastRightDis;
//        for (int i = startPosition; i <= endPosition; i++) {
//
//
//            TranslateAnimation translateAnimation = new TranslateAnimation(fromXDelta, 0f, 0f, 0f);
//            translateAnimation.setInterpolator(new DecelerateInterpolator());
//            translateAnimation.setDuration(350l);
//
//            AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
//            alphaAnimation.setInterpolator(new DecelerateInterpolator());
//            alphaAnimation.setDuration(350l);
//
//            AnimationSet animationSet = new AnimationSet(true);
//            animationSet.addAnimation(translateAnimation);
//            if(i == 0) { //回调只需要加入一个
//                animationSet.setAnimationListener(animationListener);
//            }
//
//            View childView = layout.getChildAt(i);
//            childView.startAnimation(animationSet);
//            fromXDelta -= childView.getLayoutParams().width;
//
//        }
//    }
//
//    public void hideViewForTranslateAnimation(float x) {
//        int left = scrollView.getRefreshableView().getScrollX();
//        int right = left + getWidth();
//
//        for (int i = 0; i < layout.getChildCount(); i++) {
//            if (i == 0) {
//                x -= layout.getChildAt(0).getLayoutParams().width;
//            }
//
//            View childView = layout.getChildAt(i);
//            if (childView.getRight() > left && childView.getLeft() < right) {
//                float toXDelta = x - (childView.getLeft() - left);
//
//                TranslateAnimation translateAnimation = new TranslateAnimation(0f, toXDelta, 0f, 0f);
//                translateAnimation.setInterpolator(new DecelerateInterpolator());
//                translateAnimation.setAnimationListener(new Animation.AnimationListener() {
//                    @Override
//                    public void onAnimationStart(Animation animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animation animation) {
//                        mHandler.sendEmptyMessage(SET_VIEW_INVISIBLE);
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animation animation) {
//
//                    }
//                });
//                translateAnimation.setDuration(350l);
//
//                AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
//                alphaAnimation.setInterpolator(new DecelerateInterpolator());
//                alphaAnimation.setDuration(350l);
//
//                AnimationSet animationSet = new AnimationSet(true);
//                animationSet.addAnimation(translateAnimation);
//                animationSet.addAnimation(alphaAnimation);
//
//                childView.startAnimation(animationSet);
//
//            }
//        }
//    }
//
//    public void hideViewForAlphaAnimation() {
//
//        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
//        alphaAnimation.setDuration(350l);
//        alphaAnimation.setInterpolator(new DecelerateInterpolator());
//        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//
//                mHandler.sendEmptyMessage(SET_VIEW_GONE);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        this.startAnimation(alphaAnimation);
//    }
//
//    public void showViewForAlphaAnimation() {
//        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
//        alphaAnimation.setDuration(350l);
//
//        this.startAnimation(alphaAnimation);
//    }
//
//    public void revertLayout() {
//        scrollView.scrollTo(0, 0);
//        layout.removeAllViews();
//        setVisibility(View.INVISIBLE);
//    }
//
//    public void resetCount(){
//        this.count = DEF_COUNT;
//    }
//
//    public ViewGroup getLayout() {
//        return layout;
//    }
//
//    private final static int SET_VIEW_GONE = 1;
//    private final static int SET_VIEW_INVISIBLE = 2;
//    private Handler mHandler = new Handler() {
//
//        @Override
//        public void dispatchMessage(Message msg) {
//            if (msg.what == SET_VIEW_GONE) {
//                setVisibility(View.INVISIBLE);
//
//            } else if (msg.what == SET_VIEW_INVISIBLE) {
//                revertLayout();
//
//            }
//        }
//    };
//
//    public void scrollToView(final View currentView, final HorizontalLayoutCallBackListener callBackListener) {
//
//        scrollView.getRefreshableView().post(new Runnable() {
//            @Override
//            public void run() {
//                int containerWidth = getMeasuredWidth();
//                int viewWidth = (int) (getMeasuredWidth() / count);
//
//                int index = 0;
//                if (null != currentView) {
//                    index = layout.indexOfChild(currentView);
//                }
//
//                int totalWidth = viewWidth * index;
//
//                int gap = totalWidth % containerWidth;
//                int predictor = totalWidth / containerWidth;
//
//                int scrollX = 0;
//                if (0 != index) {
//                    scrollX = gap + containerWidth * predictor - 2 * viewWidth;
//                }
//                scrollView.getRefreshableView().scrollTo(scrollX, scrollView.getRefreshableView().getScrollY());
//
//
//                if (callBackListener != null) {
//                    callBackListener.callBack();
//                }
//            }
//        });
//    }
//
//    public void scrollToView(View currentView) {
//        scrollToView(currentView, null);
//    }
//
//    public void scrollToViewWithAnimation(final View currentView, final float right, final int displayWidth, final HorizontalLayoutCallBackListener callBackListener) {
//
//        scrollToView(currentView, new HorizontalLayoutCallBackListener() {
//            @Override
//            public void callBack() {
//                int index = layout.indexOfChild(currentView);
//                if (index == -1) {
//                    index = 0;
//                }
//                showViewForTranslateAnimation(right, index, displayWidth, null);
//
//                if (callBackListener != null) {
//                    callBackListener.callBack();
//                }
//            }
//        });
//
//    }
//
//    public ViewGroup getContainerView() {
//        return layout;
//    }
//
//    public static interface HorizontalLayoutCallBackListener {
//
//        public void callBack();
//    }
//}
