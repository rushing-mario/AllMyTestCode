package com.okry.amt.temp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.okry.amt.R;
import com.okry.amt.allbase.BaseActivity;

import java.util.Arrays;
import java.util.List;

public class MyActivity extends BaseActivity {

    String[] imageFilePaths = {"/storage/emulated/0/test/img0.png", "/storage/emulated/0/test/img1.png", "/storage/emulated/0/test/img2.png",
            "/storage/emulated/0/test/img3.png", "/storage/emulated/0/test/img4.png", "/storage/emulated/0/test/img5.png",
            "/storage/emulated/0/test/img6.png", "/storage/emulated/0/test/img7.png", "/storage/emulated/0/test/img8.png", "/storage/emulated/0/test/img9.png"};
    private ListView mListView;

    private static Bitmap getBitmap(String filePath) {
        Bitmap b = BitmapFactory.decodeFile(filePath);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_container);
        mListView = (ListView) findViewById(R.id.list);
        MyAdapter adapter = new MyAdapter();
        adapter.setData(Arrays.asList(imageFilePaths));
        mListView.setAdapter(adapter);
    }

    // 注意MyAdapter是static类，不能使用Activity的方法
    private static class MyAdapter extends BaseAdapter {

        private List<String> mList;

        public void setData(List<String> list) {
            mList = list;
        }

        @Override
        public int getCount() {
            return mList == null ? 0 : mList.size();
        }

        @Override
        public String getItem(int position) {
            return mList == null ? null : mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // convertView的重用是考察点
            if (convertView == null) {
                ImageView imageView = new ImageView(parent.getContext());
                convertView = imageView;
            }
            final ImageView imageView = (ImageView) convertView;

            // 直接在主线程加载会卡顿
            //            Bitmap b = getBitmap(getItem(position));
            //            imageView.setImageBitmap(b);
            // 这里的异步实现写得比较烂，只是完成了功能，方法仅供参考。。。。。。
            new Thread() {
                @Override
                public void run() {
                    final Bitmap b = getBitmap(getItem(position));
                    imageView.post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(b);
                        }
                    });
                }
            }.start();
            return convertView;
        }

    }

}
