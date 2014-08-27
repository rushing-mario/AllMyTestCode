package com.okry.amt.bitmap.bitmapconvert;

import android.graphics.*;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import com.okry.amt.R;
import com.okry.amt.allbase.BaseActivity;

import java.nio.ByteBuffer;

/**
 * Created by MR on 2014/5/23.
 */
public class BitmapConvertActivity extends BaseActivity{

//    private Bitmap bitmap;
    private ImageView imageView;
    private Bitmap bitmap2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageView = new ImageView(this);
        imageView.setBackgroundColor(Color.YELLOW);
        setContentView(imageView);

//        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.compresstest);
//        imageView.setImageBitmap(bitmap);
//        Bitmap.Config c = bitmap.getConfig();

        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inPreferredConfig = Bitmap.Config.ALPHA_8;
        bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.compresstest, o);
        imageView.setImageBitmap(bitmap2);
    }

    private byte[] getPixels(Bitmap b) {
        int bytes = b.getRowBytes() * b.getHeight();
        ByteBuffer buffer = ByteBuffer.allocate(bytes);
        b.copyPixelsToBuffer(buffer);
        return buffer.array();
    }

    private Bitmap convertToAlpha8(Bitmap org) {
        byte[] pixels = getPixels(org);
        ByteBuffer buffer = ByteBuffer.allocate(pixels.length / 4);
        for(int i = 0; i < pixels.length / 4; i++) {
            byte p = pixels[i * 4 + 1];
            buffer.put(p);
        }
        buffer.rewind();
        Bitmap b = Bitmap.createBitmap(org.getWidth(), org.getHeight(), Bitmap.Config.ALPHA_8);
        b.copyPixelsFromBuffer(buffer);
        return b;
    }

    private Bitmap convertToAlpha_8(Bitmap org){
        Paint p = new Paint();
        p.setAntiAlias(true);
        Bitmap convert = Bitmap.createBitmap(org.getWidth(), org.getHeight(), Bitmap.Config.ALPHA_8);
        Canvas c = new Canvas(convert);
        c.drawBitmap(org, 0, 0, p);
        return convert;
    }

    private Bitmap convertToARGB_8888(Bitmap org){
        Paint p = new Paint();
        p.setAntiAlias(true);
        Bitmap convert = Bitmap.createBitmap(org.getWidth(), org.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(convert);
        c.drawBitmap(org, 0, 0, p);
        return convert;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            if(bitmap2.getConfig() == Bitmap.Config.ARGB_8888) {
                bitmap2 = convertToAlpha8(bitmap2);
                imageView.setImageBitmap(bitmap2);
            } else {
                bitmap2 = convertToARGB_8888(bitmap2);
                imageView.setImageBitmap(bitmap2);
            }
        }
        return super.onTouchEvent(event);
    }
}
