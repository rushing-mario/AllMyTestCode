package com.okry.amt.thread.synchicken;

import android.util.Log;

/**
 * 母鸡类
 */
public class Hen {

    String name;

    public Hen(String name) {
        this.name = name;
    }

    /**
     * 下蛋
     *
     * @return
     */
    public Egg lay() {
        Egg egg = new Egg();
        // 休眠，模仿下蛋需要的时间
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(FarmStory.TAG, name + " lays egg!");
        return egg;
    }

    /**
     * 鸡蛋类，需要保证其他类无法生产Egg
     */
    public class Egg {

        private Egg() {
            //私有构造，只有lay方法能产生Egg
        }

        @Override
        public String toString() {
            return "Egg:" + Integer.toHexString(hashCode());
        }

    }

}
