package com.anyway.Event;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.anyway.Common.EventInfo;
import com.anyway.R;

/**
 * Created by taekyong on 2015-10-11.
 */
public class AnimTextFadeEvent {

    Activity mActivity;
    int mAnimTypeId;
    int mResId;
    int mObjId;

    Animation mFadeIn, mFadeOut, mFadeInOut;

    AnimEventEndListener endListener = null;
    AnimationListener animationListener = null;

    // Notify Text Event Animation Termination
    public interface AnimEventEndListener {
        void onAnimTextFadeEventTermination(int index);
    }

    public AnimTextFadeEvent(Activity activity, int AnimTypeId, int resId, int ObjId) {
        this.mActivity = activity;
        this.mAnimTypeId = AnimTypeId;
        this.mResId = resId;
        this.mObjId = ObjId;

        init();
    }

    private boolean init() {

        if(mActivity instanceof  AnimEventEndListener) {
            endListener = (AnimEventEndListener) mActivity;
        } else {
            return false;
        }

        if (animationListener == null) {
            animationListener = new AnimationListener();
        }

        if (mAnimTypeId == EventInfo.FADE_IN) {
            mFadeIn = AnimationUtils.loadAnimation(mActivity.getApplicationContext(), R.anim.fade_in);
            mFadeIn.setAnimationListener(animationListener);
        } else if (mAnimTypeId == EventInfo.FADE_OUT) {
            mFadeOut = AnimationUtils.loadAnimation(mActivity.getApplicationContext(), R.anim.fade_out);
            mFadeOut.setAnimationListener(animationListener);
        } else if (mAnimTypeId == EventInfo.FADE_INOUT) {
            // Todo
        } else {
            //Toast.makeText(mActivity.getApplicationContext(), "Unknown TypeId : AnimTextEvent", Toast.LENGTH_LONG).show();
        }

        return true;
    }

    public void start(TextView textView, String strText) {

        if (mAnimTypeId == EventInfo.FADE_IN) {
            textView.setText(strText);
            textView.setVisibility(View.VISIBLE);
            textView.startAnimation(mFadeIn);

        } else if (mAnimTypeId == EventInfo.FADE_OUT) {
            textView.setText(strText);
            textView.startAnimation(mFadeOut);
        } else if (mAnimTypeId == EventInfo.FADE_INOUT) {
        } else {
        }
    }

    public void InVisible() {

        if(mResId == R.id.descText ) {
            mActivity.findViewById(R.id.descText).setVisibility(View.INVISIBLE);
        }

        if(mResId == R.id.patternText1 ) {
            mActivity.findViewById(R.id.patternText1).setVisibility(View.INVISIBLE);
        }

        if(mResId == R.id.patternText2 ) {
            mActivity.findViewById(R.id.patternText2).setVisibility(View.INVISIBLE);
        }

        if(mResId == R.id.sentenceText1 ) {
            mActivity.findViewById(R.id.sentenceText1).setVisibility(View.INVISIBLE);
        }

        if(mResId == R.id.sentenceText2 ) {
            mActivity.findViewById(R.id.sentenceText2).setVisibility(View.INVISIBLE);
        }
    }

    class AnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            endListener.onAnimTextFadeEventTermination(mObjId);
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }

}
