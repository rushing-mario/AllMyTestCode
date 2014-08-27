package com.okry.amt.bitmap.jni;

/**
 * Created by MR on 2014/4/18.
 */
public class JniProcess {

    static {
        System.loadLibrary("test");
    }

    public native int nadd(int a, int b);

    public static native void certificate(String source, String image, String trimp, int height,
                                          int width, float scale, int[] color, String[] img);
}

