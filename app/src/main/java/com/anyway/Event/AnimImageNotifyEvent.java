package com.anyway.Event;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.anyway.Common.EventInfo;
import com.anyway.R;

/**
 * Created by taekyong on 2015-10-11.
 */
public class AnimImageNotifyEvent {

    Activity mActivity;
    int mAnimTypeId;
    int mResourceLayout;
    int mObjId;

    Animation mSlideIn, mSlideOut;
    RelativeLayout overlayLayout;

    AnimEventEndListener endListener = null;
    AnimationImageNotifyListener animationImageNotifyListener = null;

    // Notify Text Event Animation Termination
    public interface AnimEventEndListener {
        void onAnimImageNotifyEventTermination(int index);
    }

    public AnimImageNotifyEvent(Activity activity, int AnimTypeId, int ResourceLayout, int objId) {
        this.mActivity = activity;
        this.mAnimTypeId = AnimTypeId;
        this.mResourceLayout = ResourceLayout;
        this.mObjId = objId;

        init();
    }

    private boolean init() {

        // 인플레이션으로 겹치는 레이아웃을 깐다.
        LayoutInflater inflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 지정된 Layout overlay
        overlayLayout = (RelativeLayout)inflater.inflate(mResourceLayout, null);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);

        mActivity.getWindow().addContentView(overlayLayout, layoutParams); // 레이아웃을 겹치는 부분

        if(mActivity instanceof AnimEventEndListener) {
            endListener = (AnimEventEndListener) mActivity;
        } else {
            return false;
        }

        if (animationImageNotifyListener == null) {
            animationImageNotifyListener = new AnimationImageNotifyListener();
        }

        if (mAnimTypeId == EventInfo.SLIDE_IN) {
            mSlideIn = AnimationUtils.loadAnimation(mActivity.getApplicationContext(), R.anim.slide_in);
            mSlideIn.setAnimationListener(animationImageNotifyListener);
        } else if (mAnimTypeId == EventInfo.SLIDE_OUT) {
            mSlideOut = AnimationUtils.loadAnimation(mActivity.getApplicationContext(), R.anim.slide_out);
            mSlideOut.setAnimationListener(animationImageNotifyListener);
        } else {
            Toast.makeText(mActivity.getApplicationContext(), "Unknown TypeId : AnimMsgNotifyEvent", Toast.LENGTH_LONG).show();
        }

        return true;
    }

    public void InVisible() {
        overlayLayout.setVisibility(View.INVISIBLE);
    }

    public void start(){

        if (mAnimTypeId == EventInfo.SLIDE_IN) {
            overlayLayout.startAnimation(mSlideIn);
        } else if (mAnimTypeId == EventInfo.SLIDE_OUT) {
            overlayLayout.startAnimation(mSlideOut);
        }  else {
            Toast.makeText(mActivity.getApplicationContext(), "Unknown TypeId : AnimMsgNotifyEvent start()", Toast.LENGTH_LONG).show();
        }
    }

    class AnimationImageNotifyListener implements Animation.AnimationListener {
        @Override
        public void onAnimationEnd(Animation animation) {
            endListener.onAnimImageNotifyEventTermination(mObjId);
            overlayLayout.setVisibility(View.INVISIBLE);
            //overlayLayout.clearAnimation();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

        @Override
        public void onAnimationStart(Animation animation) {

        }
    }
}

