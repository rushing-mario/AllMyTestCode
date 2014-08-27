package com.okry.amt.annotation;

import android.os.Bundle;
import com.okry.amt.allbase.BaseActivity;
import com.okry.amt.log.L;

/**
 * Created by apple on 14-6-25.
 */
public class AnnotationTest extends BaseActivity{

    private static final String TAG = AnnotationTest.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Annotations.ashow("test msg")
    private void method1() {
        L.d(TAG, "method1");
    }

    @Annotations.atest
    private void method2() {
        L.d(TAG, "method2");
    }

    @SuppressWarnings("dffd")
    private void method3() {
    }

    @Annotations.UseCase(id = 0, description = "method4")
    private void method4() {

    }

}
