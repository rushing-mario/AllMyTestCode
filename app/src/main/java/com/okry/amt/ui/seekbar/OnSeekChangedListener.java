package com.okry.amt.ui.seekbar;

/**
 * Created by apple on 14-7-7.
 */
public interface OnSeekChangedListener {
    /**
     * @param rate from[0..1]
     */
    void onSeekValueChanged(float rate);
}
