package com.anyway.Event;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by taekyong on 2015-11-15.
 */
public class AnimationEvent {

    Activity mActivity;
    int mObjId;

    AnimationEventEndListener endListener = null;

    // Notify Animation Termination
    public interface AnimationEventEndListener {
        void onAnimationEventTermination(int index);
    }

    public AnimationEvent(Activity mActivity, int mObjId ) {
        this.mActivity = mActivity;
        this.mObjId = mObjId;

        init();
    }

    public boolean init() {

        if(mActivity instanceof  AnimationEventEndListener) {
            endListener = (AnimationEventEndListener) mActivity;
        } else {
            return false;
        }

        // 이미지 가져오기
//        Resources res = mActivity.getResources();
//        BitmapDrawable frame01 = (BitmapDrawable)res.getDrawable(R.drawable.android_1);
//        BitmapDrawable frame02 = (BitmapDrawable)res.getDrawable(R.drawable.android_2);
//        BitmapDrawable frame03 = (BitmapDrawable)res.getDrawable(R.drawable.android_3);
//        BitmapDrawable frame04 = (BitmapDrawable)res.getDrawable(R.drawable.android_4);
//        BitmapDrawable frame05 = (BitmapDrawable)res.getDrawable(R.drawable.android_5);
//        BitmapDrawable frame06 = (BitmapDrawable)res.getDrawable(R.drawable.android_6);
//        BitmapDrawable frame07 = (BitmapDrawable)res.getDrawable(R.drawable.android_7);
//
//        // 프레임으로 추가하기
//        int duration = 500;
//        animDrawable = new AnimationDrawable();
//        animDrawable.setOneShot(true);
//        animDrawable.addFrame(frame01, duration);
//        animDrawable.addFrame(frame02, duration);
//        animDrawable.addFrame(frame03, duration);
//        animDrawable.addFrame(frame04, duration);
//        animDrawable.addFrame(frame05, duration);
//        animDrawable.addFrame(frame06, duration);
//        animDrawable.addFrame(frame07, duration);

/*
        AnimationDrawable animDrawable = (AnimationDrawable) mActivity.getResources().getDrawable(R.drawable.anim_android);

        if (frameAnimation == null) {
            frameAnimation = new AnimationDrawableEvent(animDrawable);

            frameAnimation.setOnAnimationStateListener(new AnimationDrawableEvent.OnAnimationStateListener() {
                @Override
                public void onAnimationEnd() {
                    Toast.makeText(mActivity.getApplicationContext(), "Animation Stopped", Toast.LENGTH_SHORT).show();
                    mAnimation.setVisibility(View.INVISIBLE);
                    endListener.onAnimationEventTermination(mObjId);
                }
            });
        }
*/

        return true;
    }

    public void start(final ImageView imageView, AnimationDrawableEvent frameAnimation) {

        frameAnimation.setOnAnimationStateListener(new AnimationDrawableEvent.OnAnimationStateListener() {
            @Override
            public void onAnimationEnd() {
                //Toast.makeText(mActivity.getApplicationContext(), "Animation Stopped", Toast.LENGTH_SHORT).show();
                imageView.setVisibility(View.INVISIBLE);
                endListener.onAnimationEventTermination(mObjId);
            }
        });

        // Aninmation Show & Start
        //mAnimation.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
        frameAnimation.setVisible(true, false);
        frameAnimation.start();
    }
}
