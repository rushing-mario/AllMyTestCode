package com.okry.amt.bitmap;

/**
 * Created by MR on 2014/4/15.
 */

import android.graphics.*;
import java.io.ByteArrayInputStream;

import com.okry.amt.log.L;

/**
 * Created by liubo on 7/31/13.
 */
public final class BitmapUtils {
    private static final String TAG = BitmapUtils.class.getSimpleName();
    private static final float PRECISION = 0.01f;
    public static final int QUALITY_MAX = 100;

    private BitmapUtils() {

    }

    /**
     * 高效快速的大图缩小
     *
     * @param jpegPath            图片路径
     * @param maxLength           最大长度，比如800,(1600x800 -> 800x400、800x1600 -> 400x800)
     * @return Bitmap
     */
    public static Bitmap scalePicture(String jpegPath, int maxLength) {
        if (maxLength < 10 && maxLength > 5000) {
            throw new IllegalArgumentException("length must between [10,5000],but value is:" + maxLength);
        }

        Bitmap bitmap;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 1;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(jpegPath, options);
            if (options.mCancel || options.outWidth == -1 || options.outHeight == -1) {
                return null;
            }
            options.inSampleSize = getSampleSize(options, maxLength, true);
            options.inJustDecodeBounds = false;
            options.inDither = false;
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            bitmap = BitmapFactory.decodeFile(jpegPath, options);
        } catch (OutOfMemoryError oom) {
            L.e(TAG, oom);
            return null;
        }

        int orientation = 0;
        return scaleBitmap(bitmap, maxLength, orientation);
    }

    /**
     * 高效快速的大图缩小
     *
     * @param jpegData            图片路径
     * @param maxLength           最大长度，比如800,(1600x800 -> 800x400、800x1600 -> 400x800)
     * @return Bitmap
     */
    public static Bitmap scalePicture(byte[] jpegData, int maxLength) {
        if (jpegData == null)
            return null;

        if (maxLength < 10 && maxLength > 5000) {
            throw new IllegalArgumentException("length must between [10,5000],but value is:" + maxLength);
        }

        Bitmap bitmap;
        //必须采用两个输入流，因为如果采用一个流，图像缩小时将会导致图像一直返回null
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 1;
            options.inJustDecodeBounds = true;
            ByteArrayInputStream in01 = new ByteArrayInputStream(jpegData);
            BitmapFactory.decodeStream(in01, null, options);
            if (options.mCancel || options.outWidth == -1 || options.outHeight == -1) {
                return null;
            }
            int inSampleSize = getSampleSize(options, maxLength, true);
            options.inSampleSize = inSampleSize;
            options.inJustDecodeBounds = false;
            options.inDither = false;
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            ByteArrayInputStream in02 = new ByteArrayInputStream(jpegData);
            bitmap = BitmapFactory.decodeStream(in02, null, options);
        } catch (OutOfMemoryError oom) {
            L.e(TAG, oom);
            return null;
        }
        //ByteArrayInputStream 不需要关闭流

        return scaleBitmap(bitmap, maxLength);
    }

    /**
     * @param
     * @return Bitmap
     * @Description 得到指定大小图片的路径
     * @author hlf
     */
    public static Bitmap scaleBitmap(String path, int screenWidth, int screenHeight) {

        BitmapFactory.Options ops = new BitmapFactory.Options();
        ops.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, ops);
        int originalWidth = ops.outWidth;
        int originalHeight = ops.outHeight;
        int orgPix, dstPix;
        orgPix = originalWidth * originalHeight;
        dstPix = screenWidth * screenHeight;
        float fScale = (float) Math.sqrt(orgPix * 1.0 / dstPix);
        int scale = (int) (fScale + 0.8);


        ops.inSampleSize = scale;
        ops.inJustDecodeBounds = false;
        ops.inDither = false;
        ops.inPreferredConfig = Bitmap.Config.ARGB_8888;

        Bitmap bitmap = BitmapFactory.decodeFile(path, ops);

        return bitmap;
    }

    /**
     * 缩小图片
     *
     * @param bitmap      Bitmap
     * @param maxLength   最大长度，比如800,(1600x800 -> 800x400、800x1600 -> 400x800)
     * @return Bitmap
     */
    public static Bitmap scaleBitmap(Bitmap bitmap, int maxLength) {
        if (maxLength < 10 && maxLength > 5000) {
            throw new IllegalArgumentException("length must between [10,5000],but value is:" + maxLength);
        }
        if (bitmap == null) {
            return null;
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //定义预转换成的图片的宽度和高度
        float scale;
        if (width >= height) {
            scale = (float) maxLength / (float) width;
        } else {
            scale = (float) maxLength / (float) height;
        }

        if (Math.abs(scale - 1) < PRECISION) {
            return bitmap;
        }

        return scaleBitmap(bitmap, scale, scale, 0);
    }

    /**
     * 简单的矩阵缩放图片，适用于变化比例不超过2倍的图像
     *
     * @param bitmap
     * @param sx
     * @param sy
     * @return
     */
    public static Bitmap scaleBitmap(Bitmap bitmap, float sx, float sy) {
        if (bitmap == null) {
            return null;
        }

        Matrix matrix = new Matrix();
        matrix.postScale(sx, sy);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (newBitmap != bitmap) {
            bitmap.recycle();
        }
        return newBitmap;
    }

    /**
     * 图像旋转
     *
     * @param bitmap
     * @param orientation
     * @return
     */
    public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {
        if (bitmap == null || orientation == 0) {
            return bitmap;
        }

        Matrix matrix = new Matrix();
        matrix.postRotate(orientation);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (newBitmap != bitmap) {
            bitmap.recycle();
        }
        return newBitmap;
    }

    /**
     * 图像矫正
     *
     * @param bitmap
     * @param sx
     * @param sy
     * @param orientation
     * @return
     */
    public static Bitmap scaleBitmap(Bitmap bitmap, float sx, float sy, int orientation) {
        if (bitmap == null) {
            return null;
        }

        Matrix matrix = new Matrix();
        matrix.postScale(sx, sy);
        if (orientation != 0) {
            matrix.postRotate(orientation);
        }
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (newBitmap != bitmap) {
            bitmap.recycle();
        }
        return newBitmap;
    }

    public static Bitmap zoomAndRotate(Bitmap org, int w, int h, int orientation) {
        int orgW = org.getWidth();
        int orgH = org.getHeight();
        Matrix matrix = new Matrix(); // 创建操作图片用的Matrix对象
        float scaleWidth = ((float) w / orgW); // 计算缩放比例
        float scaleHeight = ((float) h / orgH);


        matrix.reset();
        if (orientation != 0) {
            matrix.postRotate(orientation);
        }
        matrix.postScale(scaleWidth, scaleHeight); // 设置缩放比例

        if (orgH <= 0 || orgW <= 0) {
            throw new IllegalArgumentException("Width or Heigth < 0:" + orgH + "/" + orgW + "/" + matrix.toString() + "/" + w + "/" + h + "/" + orientation);
        }

        try {
            return Bitmap.createBitmap(org, 0, 0, orgW, orgH, matrix, true); // 建立新的bitmap，其内容是对原bitmap的缩放后的图
        } catch (Exception e) {
            L.e(TAG, e);
            //出异常时，记录响应信息，方便定位bug
            throw new IllegalStateException("orgSize : " + orgW + "x" + orgH + ", size : " + w + "x" + h + ", matrix : " +
                    matrix.toShortString());
        }
    }

    private static int getSampleSize(BitmapFactory.Options options, int size, boolean useMaxSize) {
        int maxSize = options.outWidth;
        if (useMaxSize && options.outWidth < options.outHeight) {
            maxSize = options.outHeight;
        } else if (!useMaxSize && options.outWidth > options.outHeight) {
            maxSize = options.outHeight;
        }

        int sampleSize = 1;
        while (maxSize / size >= 2) {
            sampleSize <<= 1;
            maxSize >>= 1;
        }
        return sampleSize;
    }


}

