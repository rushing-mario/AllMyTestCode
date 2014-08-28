package com.okry.amt.ui.animhoriscroll;

import android.content.Context;
import android.view.View;

import java.util.List;

/**
 * Created by marui on 13-11-26.
 */
public abstract class BaseHoriScrollItemAdapter<T> {

    protected List<T> mList;
    private HoriDataSetObserver mDataSetObserver;

    public abstract View initView(LinearHoriScrollView parent, Context context, int position);

    public int getCount() {
        return mList==null ? 0:mList.size();
    }

    public T getItem(int position) {
        if(mList != null && position >= 0 && position <= mList.size() - 1){
            return mList.get(position);
        }else{
            return null;
        }
    }

    public void setData(List<T> list){
        mList = list;
    }

    public void remove(int position){
        if(mList != null){
            mList.remove(position);
            if(mDataSetObserver != null){
                mDataSetObserver.onRemove(position);
            }
        }
    }

    public void remove(T item) {
        if (mList != null) {
            int index = indexOf(item);
            mList.remove(index);
            if (mDataSetObserver != null) {
                mDataSetObserver.onRemove(index);
            }
        }
    }

    private int indexOf(T item) {
        for (int i = 0; i < mList.size(); i++) {
            if (item.equals(mList.get(i))) return i;
        }
        return -1;
    }

    /**
     * Register an observer that is called when changes happen to the data used by this adapter.
     *
     * @param observer the object that gets notified when the data set changes.
     */
    public void registerDataSetObserver(HoriDataSetObserver observer){
        mDataSetObserver = observer;
    }

    /**
     * Unregister an observer that has previously been registered with this
     * adapter via {@link #registerDataSetObserver}.
     *
     * @param observer the object to unregister.
     */
    public void unregisterDataSetObserver(HoriDataSetObserver observer){
        mDataSetObserver = null;
    }

    public void notifyDataSetChange() {
        if (mDataSetObserver != null) {
            mDataSetObserver.onInvalidated();
        }
    }

    public interface HoriDataSetObserver {
        public void onAdd(int position);

        public void onRemove(int position);

        public void onInvalidated();
    }

}
