package com.okry.amt.animation;

import android.os.Bundle;
import android.view.View;

import com.okry.amt.R;
import com.okry.amt.allbase.BaseActivity;
import com.okry.amt.log.L;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mr on 15/7/10.
 */
public class ViewTranslate extends BaseActivity {


    @InjectView(R.id.view1)
    View view1;
    @InjectView(R.id.view2)
    View view2;
    @InjectView(R.id.view3)
    View view3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_translate);
        ButterKnife.inject(this);


//        L.d("view1, x:%f, y:%f, translateX:%f, translateY:%f, top:%d", view1.getX(), view1.getY(), view1.getTranslationX(), view1.getTranslationY(), view1.getTop());
//        L.d("view2, x:%f, y:%f, translateX:%f, translateY:%f, top:%d", view2.getX(), view2.getY(), view2.getTranslationX(), view2.getTranslationY(), view2.getTop());
//        L.d("view3, x:%f, y:%f, translateX:%f, translateY:%f, top:%d", view3.getX(), view3.getY(), view3.getTranslationX(), view3.getTranslationY(), view3.getTop());

//        view1.setTop(100);
//        view2.setTop(100);
//        view3.setTop(100);

        L.d("view1, x:%f, y:%f, translateX:%f, translateY:%f, top:%d", view1.getX(), view1.getY(), view1.getTranslationX(), view1.getTranslationY(), view1.getTop());
        L.d("view2, x:%f, y:%f, translateX:%f, translateY:%f, top:%d", view2.getX(), view2.getY(), view2.getTranslationX(), view2.getTranslationY(), view2.getTop());
        L.d("view3, x:%f, y:%f, translateX:%f, translateY:%f, top:%d", view3.getX(), view3.getY(), view3.getTranslationX(), view3.getTranslationY(), view3.getTop());

        view1.setPivotY(0);
        view2.setPivotY(100);
        view3.setPivotY(200);

        L.d("view1, x:%f, y:%f, translateX:%f, translateY:%f, top:%d", view1.getX(), view1.getY(), view1.getTranslationX(), view1.getTranslationY(), view1.getTop());
        L.d("view2, x:%f, y:%f, translateX:%f, translateY:%f, top:%d", view2.getX(), view2.getY(), view2.getTranslationX(), view2.getTranslationY(), view2.getTop());
        L.d("view3, x:%f, y:%f, translateX:%f, translateY:%f, top:%d", view3.getX(), view3.getY(), view3.getTranslationX(), view3.getTranslationY(), view3.getTop());

//        view1.setScaleY(.5f);
//        view2.setScaleY(.5f);
//        view3.setScaleY(.5f);


        view1.setY(100);
        view2.setY(0);
        view3.setY(100);

        L.d("view1, x:%f, y:%f, translateX:%f, translateY:%f, top:%d", view1.getX(), view1.getY(), view1.getTranslationX(), view1.getTranslationY(), view1.getTop());
        L.d("view2, x:%f, y:%f, translateX:%f, translateY:%f, top:%d", view2.getX(), view2.getY(), view2.getTranslationX(), view2.getTranslationY(), view2.getTop());
        L.d("view3, x:%f, y:%f, translateX:%f, translateY:%f, top:%d", view3.getX(), view3.getY(), view3.getTranslationX(), view3.getTranslationY(), view3.getTop());
    }


}
