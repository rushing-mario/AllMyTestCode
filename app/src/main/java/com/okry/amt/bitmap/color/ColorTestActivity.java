package com.okry.amt.bitmap.color;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.okry.amt.R;
import com.okry.amt.allbase.BaseActivity;

import java.util.Arrays;

/**
 * Created by mr on 14-9-12.
 */
public class ColorTestActivity extends BaseActivity {

    public static int changeHsb(int color, float h, float s, float b) {
        int[] rgb = new int[3];
        rgb[0] = Color.red(color);
        rgb[1] = Color.green(color);
        rgb[2] = Color.blue(color);
        float[] hsb = rgb2hsb(rgb[0], rgb[1], rgb[2]);
        if (h >= 0 && h <= 360) hsb[0] = h;
        if (s >= 0 && s <= 1) hsb[1] = s;
        if (b >= 0 && b <= 1) hsb[2] = b;
        rgb = hsb2rgb(hsb[0], hsb[1], hsb[2]);
        return Color.rgb(rgb[0], rgb[1], rgb[2]);
    }

    public static int randomHsbColor(int color) {
        int[] rgb = new int[3];
        rgb[0] = Color.red(color);
        rgb[1] = Color.green(color);
        rgb[2] = Color.blue(color);
        float[] hsb = rgb2hsb(rgb[0], rgb[1], rgb[2]);

        float r = randomAB(0.8f, 1.2f);
        Log.e("ColorTest", "randomRange:" + r);

        hsb[0] = range(hsb[0] * r, 0, 359.99f);
        hsb[1] = range(hsb[1] * r, 0, 1);
        hsb[2] = range(hsb[2] * r, 0, 1);

        rgb = hsb2rgb(hsb[0], hsb[1], hsb[2]);
        return Color.rgb(rgb[0], rgb[1], rgb[2]);
    }

    private static float range(float value, float min, float max) {
        if (value < min) value = min;
        else if (value > max) value = max;
        return value;
    }

    private static float randomAB(float a, float b) {
        return (float) (a + Math.random() * (b - a));
    }

    public static float[] rgb2hsb(int rgbR, int rgbG, int rgbB) {
        int[] rgb = new int[]{rgbR, rgbG, rgbB};
        Arrays.sort(rgb);
        int max = rgb[2];
        int min = rgb[0];

        float hsbB = max / 255.0f;
        float hsbS = max == 0 ? 0 : (max - min) / (float) max;

        float hsbH = 0;
        if (max == rgbR && rgbG >= rgbB) {
            hsbH = (rgbG - rgbB) * 60f / (max - min) + 0;
        } else if (max == rgbR && rgbG < rgbB) {
            hsbH = (rgbG - rgbB) * 60f / (max - min) + 360;
        } else if (max == rgbG) {
            hsbH = (rgbB - rgbR) * 60f / (max - min) + 120;
        } else if (max == rgbB) {
            hsbH = (rgbR - rgbG) * 60f / (max - min) + 240;
        }

        return new float[]{hsbH, hsbS, hsbB};
    }

    public static int[] hsb2rgb(float h, float s, float v) {
        assert Float.compare(h, 0.0f) >= 0 && Float.compare(h, 360.0f) <= 0;
        assert Float.compare(s, 0.0f) >= 0 && Float.compare(s, 1.0f) <= 0;
        assert Float.compare(v, 0.0f) >= 0 && Float.compare(v, 1.0f) <= 0;

        float r = 0, g = 0, b = 0;
        int i = (int) ((h / 60) % 6);
        float f = (h / 60) - i;
        float p = v * (1 - s);
        float q = v * (1 - f * s);
        float t = v * (1 - (1 - f) * s);
        switch (i) {
            case 0:
                r = v;
                g = t;
                b = p;
                break;
            case 1:
                r = q;
                g = v;
                b = p;
                break;
            case 2:
                r = p;
                g = v;
                b = t;
                break;
            case 3:
                r = p;
                g = q;
                b = v;
                break;
            case 4:
                r = t;
                g = p;
                b = v;
                break;
            case 5:
                r = v;
                g = p;
                b = q;
                break;
            default:
                break;
        }
        return new int[]{(int) (r * 255.0), (int) (g * 255.0),
                (int) (b * 255.0)};
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        Button btn = (Button) findViewById(R.id.color_change);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputR = (EditText) findViewById(R.id.color_r);
                EditText inputG = (EditText) findViewById(R.id.color_g);
                EditText inputB = (EditText) findViewById(R.id.color_b);

                String rs = inputR.getText().toString();
                String gs = inputG.getText().toString();
                String bs = inputB.getText().toString();

                int r = Integer.valueOf(rs);
                int g = Integer.valueOf(gs);
                int b = Integer.valueOf(bs);

                int color = Color.rgb(r, g, b);

                View ori = findViewById(R.id.color_ori);
                ori.setBackgroundColor(color);

                LinearLayout hcontainer = (LinearLayout) findViewById(R.id.h_container);
                int hcount = hcontainer.getChildCount();
                for (int i = 0; i < hcount; i++) {
                    View child = hcontainer.getChildAt(i);
                    child.setBackgroundColor(changeHsb(color, 360f / hcount * i, -1, -1));
                }

                LinearLayout scontainer = (LinearLayout) findViewById(R.id.s_container);
                int scount = scontainer.getChildCount();
                for (int i = 0; i < scount; i++) {
                    View child = scontainer.getChildAt(i);
                    child.setBackgroundColor(changeHsb(color, -1, 1f / scount * i, -1));
                }

                LinearLayout bcontainer = (LinearLayout) findViewById(R.id.b_container);
                int bcount = bcontainer.getChildCount();
                for (int i = 0; i < bcount; i++) {
                    View child = bcontainer.getChildAt(i);
                    child.setBackgroundColor(changeHsb(color, -1, -1, 1f / bcount * i));
                }

                LinearLayout hsbcontainer = (LinearLayout) findViewById(R.id.hsb_container);
                int hsbcount = hsbcontainer.getChildCount();
                for (int i = 0; i < hsbcount; i++) {
                    View child = hsbcontainer.getChildAt(i);
                    child.setBackgroundColor(randomHsbColor(color));
                }
            }
        });
    }
}
