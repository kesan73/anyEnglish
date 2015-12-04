package com.anyway.Event;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.anyway.Common.EventInfo;
import com.anyway.R;

/**
 * Created by taekyong on 2015-10-11.
 */
public class AnimTranslateEvent {

    Activity mActivity;

    int mAnimTypeId;
    int mObjId;
    LinearLayout mLinearLayout;

    Animation mTranslateUp, mTranslateDown;

    AnimEventEndListener endListener = null;
    AnimationTranslateListener animationTranslateListener = null;

    // Notify Text Event Animation Termination
    public interface AnimEventEndListener {
        void onAnimTranslateEventTermination(int index);
    }

    public AnimTranslateEvent(Activity activity, int AnimTypeId, int ObjId, LinearLayout layout) {

        this.mActivity = activity;
        this.mAnimTypeId = AnimTypeId;
        this.mObjId = ObjId;
        this.mLinearLayout = layout;

        init();
    }

    private boolean init() {

        if(mActivity instanceof AnimEventEndListener) {
            endListener = (AnimEventEndListener) mActivity;
        } else {
            return false;
        }

        if (animationTranslateListener == null) {
            animationTranslateListener = new AnimationTranslateListener();
        }

        if (mAnimTypeId == EventInfo.TRANSLATE_UP) {
            mTranslateUp = AnimationUtils.loadAnimation(mActivity.getApplicationContext(), R.anim.translate_up);
            mTranslateUp.setAnimationListener(animationTranslateListener);
        } else if (mAnimTypeId == EventInfo.TRANSLATE_DOWN) {
            mTranslateDown = AnimationUtils.loadAnimation(mActivity.getApplicationContext(), R.anim.fade_out);
            mTranslateDown.setAnimationListener(animationTranslateListener);
        } else {
            Toast.makeText(mActivity.getApplicationContext(), "Unknown TypeId : AnimTranlateEvent", Toast.LENGTH_LONG).show();
        }

        return true;
    }

    public void start() {

        if (mAnimTypeId == EventInfo.TRANSLATE_UP) {
            mLinearLayout.setVisibility(View.GONE);
            mLinearLayout.setVisibility(View.VISIBLE);
            mLinearLayout.startAnimation(mTranslateUp);

        } else if (mAnimTypeId == EventInfo.TRANSLATE_DOWN) {
            mLinearLayout.setVisibility(View.VISIBLE);
            mLinearLayout.setVisibility(View.GONE);
            mLinearLayout.startAnimation(mTranslateDown);
        } else {
        }
    }

    class AnimationTranslateListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            endListener.onAnimTranslateEventTermination(mObjId);
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }

}
