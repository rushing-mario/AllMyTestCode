package com.okry.amt.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.okry.amt.allbase.BaseActivity;

/**
 * Created by MR on 14-3-20.
 */
public class AnimListViewActivity extends BaseActivity {

    AnimListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = new AnimListView(this);
        listView.setAdapter(new MyAdapter());
        setContentView(listView);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_MENU) {
            listView.requestLayout();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private int currentFirstVisiable = 0;

    private class MyAdapter extends BaseAdapter {

        int colors[] = {0xff0000ff, 0xff00ff00, 0xffff00ff, 0xffff0000, 0xff00ffff, 0xffffff00, 0xffffffff,
                0xff0000ff, 0xff00ff00, 0xffff00ff, 0xffff0000, 0xff00ffff, 0xffffff00, 0xffffffff, 0xff0000ff,
                0xff00ff00, 0xffff00ff, 0xffff0000, 0xff00ffff, 0xffffff00, 0xffffffff, 0xff0000ff, 0xff00ff00,
                0xffff00ff, 0xffff0000, 0xff00ffff, 0xffffff00, 0xffffffff};

        @Override
        public int getCount() {
            return colors.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                TextView view = new TextView(AnimListViewActivity.this);
                convertView = view;
            }
            convertView.setBackgroundColor(colors[position]);
            ((TextView) convertView).setText("pos:" + position);
            //高度矫正
            ((TextView) convertView).setHeight(250);
            return convertView;
        }
    }

}
