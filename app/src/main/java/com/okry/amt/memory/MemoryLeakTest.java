package com.okry.amt.memory;

import android.os.Bundle;
import com.okry.amt.allbase.BaseActivity;

/**
 * Created by MR on 2014/5/22.
 */
public class MemoryLeakTest extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testStaticMethodWithInnerClass();
    }

    private static void testStaticMethodWithInnerClass() {
        final byte[] data2 = new byte[1024 * 1024 * 2];
        byte[] data3 = new byte[1024 * 1024 * 3];
        byte[] data4 = new byte[1024 * 1024 * 4];
        final byte[] data5 = new byte[1024 * 1024 * 5];

        innerClassInterface inner = new innerClassInterface() {
            public void innerMethod() {
                byte[] dataInner = data5;
            }
        };

        inner.innerMethod();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public interface innerClassInterface {
        public void innerMethod();
    }

}
