package com.anyway.Event;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

import com.anyway.Common.EventInfo;
import com.anyway.R;

/**
 * Created by taekyong on 2015-11-15.
 */
public class ImageShowEvent {

    Activity mActivity;
    int mAnimTypeId;
    int mObjId;

    ImageButton mAudioIconButton;
    Animation mFadeIn;

    EventEndListener endListener = null;
    ImageShowEventListener animationListener = null;

    public interface EventEndListener {
        void onImageShowEventTermination(int index);
    }

    public ImageShowEvent(Activity mActivity, int AnimTypeId, int objId ) {

        this.mActivity = mActivity;
        this.mAnimTypeId = AnimTypeId;
        this.mObjId = objId;

        init();
    }

    public boolean init() {

        if(mActivity instanceof EventEndListener) {
            endListener = (EventEndListener) mActivity;
        } else {
            return false;
        }

        if (animationListener == null) {
            animationListener = new ImageShowEventListener();
        }

        //audioIconButton = (ImageButton)mActivity.findViewById(mResId);
        //mAudioIconButton.setVisibility(View.INVISIBLE);

        if (mAnimTypeId == EventInfo.FADE_IN) {
            mFadeIn = AnimationUtils.loadAnimation(mActivity.getApplicationContext(), R.anim.fade_in);
            mFadeIn.setAnimationListener(animationListener);
        } else {
            Toast.makeText(mActivity.getApplicationContext(), "Unknown TypeId : AnimTextEvent", Toast.LENGTH_LONG).show();
        }

        return true;
    }

    public void start(ImageButton imageButton) {

        if (imageButton != null) {
            imageButton.setVisibility(View.VISIBLE);
            imageButton.startAnimation(mFadeIn);
        }
    }

    public void InVisible() {

        if (mAudioIconButton != null) {
            mAudioIconButton.setVisibility(View.INVISIBLE);
        }
    }

    class ImageShowEventListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {
            endListener.onImageShowEventTermination(mObjId);
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }
}
