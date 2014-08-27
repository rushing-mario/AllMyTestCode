package com.okry.amt.bitmap.compress;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.ImageView;
import com.okry.amt.R;
import com.okry.amt.allbase.BaseActivity;

import java.io.*;

/**
 * Created by MR on 2014/5/19.
 */
public class CompressBitmapRepeatly extends BaseActivity{

    private Bitmap b;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = BitmapFactory.decodeResource(getResources(), R.drawable.compresstest);
        iv = new ImageView(this);
        setContentView(iv);
        iv.setImageBitmap(b);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            try {
                saveBitmap(getExternalCacheDir() + File.separator + "compressTest", b, 100);
            } catch (IOException e) {
                e.printStackTrace();
            }
            b = BitmapFactory.decodeFile(getExternalCacheDir() + File.separator + "compressTest");
            iv.setImageBitmap(b);
        }
        return super.onTouchEvent(event);
    }

    /**
     * 保存Bitmap
     *
     * @param
     * @return boolean
     * @author litao
     */
    public static boolean saveBitmap(String path, Bitmap bitmap, int quality) throws IOException, IllegalArgumentException {
        if (TextUtils.isEmpty(path) || bitmap == null)
            return false;

        boolean flag = false;
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out)) {
                out.flush();

                flag = true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            close(out);
        }

        return flag;
    }

    public static void close(OutputStream out) throws IOException {
        if (out != null) {
            out.close();
            out = null;
        }
    }

}
