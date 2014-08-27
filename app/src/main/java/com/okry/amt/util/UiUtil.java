package com.okry.amt.util;

import com.okry.amt.app.AMTApplication;

/**
 * Created by apple on 14-7-7.
 */
public class UiUtil {


    public static int dpToPixel(float dp) {

        return Math.round(AMTApplication.getInstatnce().getResources().getDisplayMetrics().density * dp);
    }

}
