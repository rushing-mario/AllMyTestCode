package com.okry.amt.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.okry.amt.log.L;

import java.io.ByteArrayOutputStream;

/**
 * Created by MR on 2014/4/18.
 */
public class PreMakeTest {

    private static final String TAG = PreMakeTest.class.getSimpleName();

    public static byte[] imageToByte(String path) {
        BitmapFactory.Options ops = new BitmapFactory.Options();
        ops.inJustDecodeBounds = false;
        ops.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(path, ops);
        byte[] data = bitmapToByte(bitmap);
        return data;
    }

    /**
     * 把bitmap转化成字节数组
     *
     * @return 字节数组数据
     */
    public static byte[] bitmapToByte(Bitmap b) {
        if (b == null) {
            return null;
        }
        ByteArrayOutputStream o = new ByteArrayOutputStream();
        // 压缩成制定的.PNG格式
        b.compress(Bitmap.CompressFormat.PNG, 100, o);
        byte[] data = o.toByteArray();
        L.d(TAG + " length[%d]" , data.length);
        return data;
    }

    public static Bitmap byteToBitmap(byte[] data) {
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }

}
