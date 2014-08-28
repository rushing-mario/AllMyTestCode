package com.okry.amt.ui.animhoriscroll;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.okry.amt.R;
import com.okry.amt.allbase.BaseActivity;

import java.util.ArrayList;
import java.util.List;

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
//        mHoriContainer.setItemCountOnScreen(5.5f);
        MyAdapter adapter = new MyAdapter();
        adapter.setData(getTestData());
        mHoriContainer.setAdapter(adapter);
        container.addView(mHoriContainer, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private List<String> getTestData() {
        String[] fruit = getResources().getStringArray(R.array.fruit);
        List<String> list = new ArrayList();
        for (String s : fruit) list.add(s);
        return list;
    }

    private static class MyAdapter extends BaseHoriScrollItemAdapter<String> {

        @Override
        public View initView(final LinearHoriScrollView parent, final Context context, int position) {

            final View root = View.inflate(context, R.layout.view_hori_item, null);
            Button btn = (Button) root.findViewById(R.id.item_btn);
            btn.setText("" + position);
            final String item = getItem(position);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context, "btn:" + position + " clicked!", Toast.LENGTH_SHORT).show();
//                    parent.smoothScrollItemToCenter(position);
//                    parent.setSelected(!parent.isSelected());
//                    if(parent.isSelected()) {
//                        parent.startCollapseAnim(root.getLeft());
//                    } else {
//                        parent.startExpandAnim(root.getLeft());
//                    }
//                      Toast.makeText(context, "firstVisible:" + parent.getFirstVisibleChildIndex() + ", lastVisible:" + parent.getLastVisibleChildIndex(), Toast.LENGTH_SHORT).show();
                    remove(item);
                }
            });
            TextView tv = (TextView) root.findViewById(R.id.item_text);
            tv.setText(getItem(position));
            return root;
        }

    }

}
