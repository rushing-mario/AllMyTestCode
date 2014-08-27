package com.okry.amt.ui.animhoriscroll;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.okry.amt.allbase.BaseActivity;

import org.w3c.dom.Text;

/**
 * Created by mr on 14-8-25.
 */
public class ActivitySample extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout container = new FrameLayout(this);
        setContentView(container);
        LinearHoriScrollView mHoriContainer = new LinearHoriScrollView(this);
        mHoriContainer.setItemCountOnScreen(5.5f);
        MyAdapter adapter = new MyAdapter();
        mHoriContainer.setAdapter(adapter);
        container.addView(mHoriContainer, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private static class MyAdapter extends BaseHoriScrollItemAdapter{

        @Override
        public View initView(final LinearHoriScrollView parent, final Context context, final int position) {
            Button btn = new Button(context);
            btn.setText("pos:" + position);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context, "btn:" + position + " clicked!", Toast.LENGTH_SHORT).show();
//                    parent.smoothScrollItemToCenter(position);
                    parent.setSelected(!parent.isSelected());
                    if(parent.isSelected()) {
                        parent.startCollapseAnim(v.getLeft());
                    } else {
                        parent.startExpandAnim(v.getLeft());
                    }
//                      Toast.makeText(context, "firstVisible:" + parent.getFirstVisibleChildIndex() + ", lastVisible:" + parent.getLastVisibleChildIndex(), Toast.LENGTH_SHORT).show();
                }
            });
            return btn;
        }

        @Override
        public int getCount() {
            return 20;
        }

    }

}
