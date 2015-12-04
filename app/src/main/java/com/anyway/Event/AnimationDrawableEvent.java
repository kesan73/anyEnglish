package com.anyway.Event;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;

/**
 * Created by taekyong on 2015-11-15.
 */
public class AnimationDrawableEvent extends AnimationDrawable {
    private OnAnimationStateListener mListener;
    private Handler mHandler;

    public AnimationDrawableEvent(AnimationDrawable drawable) {
        mHandler = new Handler();
        for (int index = 0; index < drawable.getNumberOfFrames(); index++) {
            this.addFrame(drawable.getFrame(index), drawable.getDuration(index));
        }
    }

    public void setOnAnimationStateListener(OnAnimationStateListener listener){
        mListener = listener;
    }

    @Override
    public void start() {
        super.start();
        if(mListener != null) {
            mHandler.postDelayed(new Runnable() {
                public void run() {
                    mListener.onAnimationEnd();
                }
            }, getTotalDuration());
        }
    }

    public int getTotalDuration() {
        int duration = 0;
        for (int i = 0; i < this.getNumberOfFrames(); i++) {
            duration += this.getDuration(i);
        }

        return duration;
    }

    public interface OnAnimationStateListener {
        public void onAnimationEnd();
    }

}