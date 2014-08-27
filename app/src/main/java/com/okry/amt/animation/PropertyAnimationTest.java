package com.okry.amt.animation;

import android.animation.*;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import com.okry.amt.R;

import java.util.ArrayList;

/**
 * Created by MR on 14-2-19.
 */
public class PropertyAnimationTest extends Activity {

    View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        mView = findViewById(R.id.view_to_animate);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PropertyAnimationTest.this, "view clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){

            ObjectAnimator move1 = moveX(mView, 0, mView.getRootView().getWidth() - mView.getWidth());
            ObjectAnimator move2 = moveY(mView, mView.getY(), mView.getY() + 200);
            ObjectAnimator move3 = moveX(mView, mView.getRootView().getWidth() - mView.getWidth(),
                    mView.getRootView().getWidth() - mView.getWidth() - 200);
            ObjectAnimator fadeOut = fade(mView, 1, 0);
            fadeOut.setRepeatCount(1);
            fadeOut.setRepeatMode(ValueAnimator.REVERSE);

            AnimatorSet moveAnim = new AnimatorSet();
            moveAnim.play(move1).before(move2);
            moveAnim.play(move2).with(move3);

            moveAnim.setDuration(2000);

            AnimatorSet animAll = new AnimatorSet();
            animAll.play(fadeOut).after(moveAnim);

            animAll.start();

            animAll.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    ArrayList<String> list = new ArrayList<String>(null);
                }
            });
            return true;
        }
        return super.onTouchEvent(event);
    }

    private ObjectAnimator moveX(View v, float start, float end){
        return ObjectAnimator.ofFloat(v,"x",start,end );
    }

    private ObjectAnimator moveY(View v, float start, float end){
        return ObjectAnimator.ofFloat(v,"y",start,end );
    }

    private ObjectAnimator fade(View v, float start, float end){
        return ObjectAnimator.ofFloat(v, "alpha", start, end);
    }


}
