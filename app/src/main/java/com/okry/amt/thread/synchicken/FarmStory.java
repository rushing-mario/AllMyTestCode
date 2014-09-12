package com.okry.amt.thread.synchicken;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.okry.amt.allbase.BaseActivity;

/**
 * Created by mr on 14-8-27.
 * 该包下用于实现鸡用篮子下蛋的问题
 */
public class FarmStory extends BaseActivity {

    public static final String TAG = "FarmStory";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout f = new FrameLayout(this);
        setContentView(f);

        Button b = new Button(this);
        f.addView(b, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        b.setText("Start");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Client client = new Client();
                client.start();
            }
        });

    }

    public static class Client {
        // 初始化母鸡类，假设有10只鸡
        Hen[] hens;
        // 有一个篮子
        Basket<Hen.Egg> eggBasket;

        private void start() {
            init();
            for (int i = 0; i < hens.length; i++) {
                final int index = i;
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        Hen.Egg egg = hens[index].lay();
                        synchronized (this) {
                            eggBasket.put(egg);
                        }
                    }
                };
                t.start();
            }
        }

        private void init() {
            // 初始化母鸡类，假设有10只鸡
            hens = new Hen[10];
            for (int i = 0; i < hens.length; i++) {
                hens[i] = new Hen("Hen[" + i + "]");
            }
            // 有一个篮子
            eggBasket = new Basket<Hen.Egg>();
        }
    }


}
