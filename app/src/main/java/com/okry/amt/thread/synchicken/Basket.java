package com.okry.amt.thread.synchicken;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 篮子类，一个容器类，保证可以装任何东西，采用泛型
 */
public class Basket<T> {

    List<T> innerVolume;

    public Basket() {
        innerVolume = new ArrayList<T>();
    }

    public T get(int i) {
        return innerVolume.get(i);
    }

    public synchronized void put(T item) {
        innerVolume.add(item);
        Log.i(FarmStory.TAG, "put " + item + " in basket:" + this);
    }

    @Override
    public String toString() {
        return "Basket(" + innerVolume.size() + "):" + hashCode();
    }
}
