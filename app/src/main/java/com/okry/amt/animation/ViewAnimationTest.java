package com.okry.amt.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;
import com.okry.amt.R;

/**
 * Created by MR on 14-2-24.
 */
public class ViewAnimationTest extends Activity {

    View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        mView = findViewById(R.id.view_to_animate);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewAnimationTest.this, "view clicked!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            TranslateAnimation anim1 = new TranslateAnimation(0, 200, 0 ,0);
            anim1.setDuration(2000);
            anim1.setFillAfter(true);

            final TranslateAnimation anim2 = new TranslateAnimation(200, 100, 0, 150);
            anim2.setDuration(1400);
            anim2.setFillAfter(true);


            anim1.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mView.startAnimation(anim2);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            mView.startAnimation(anim1);
            return true;
        }else{
            return super.onTouchEvent(event);
        }
    }
}
