package com.anyway.Event;

import android.support.v4.app.Fragment;

/**
 * Created by taekyong on 2015-10-11.
 */
public class AnimImageShowEvent {

    Fragment mActivity;
    int mAnimTypeId;

//    Handler handler = new Handler();
//    LinearLayout descLayout;
//    LinearLayout.LayoutParams params;
//
//    // cache bitmap
//    private Bitmap cacheBitmap;
//
//    // cache canvas
//    private Canvas cacheCanvas;
//
//    //DisplayRunnable runnable = null;
//    BackgroundTask task;
    AnimEventEndListener endListener = null;

    public interface AnimEventEndListener {
        void onAnimImageShowEventTermination(int index);
    }

    public AnimImageShowEvent (Fragment activity) {
        this.mActivity = activity;

        init();
    }

    private boolean init() {

        if(mActivity instanceof  AnimEventEndListener) {
            endListener = (AnimEventEndListener) mActivity;
        } else {
            return false;
        }

//        if(runnable == null) {
//            runnable = new DisplayRunnable();
//        }
//
//        descLayout = (LinearLayout) mActivity.findViewById(R.id.descLayout);
//        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        return true;
    }

    public void start() {

/*       Thread thread = new Thread(new Runnable() {
            public void run() {

                Looper.prepare();

                try {
                    for (int i = 0; i < 3 ; i++) {
                        Thread.sleep(1000);

                        //handler.post(runnable);
                    }

                    endListener.onAnimImageShowEventTermination(3);

                } catch (Exception ex) {
                    Log.e("AnimImageShowEvent", "Exception in processing message.", ex);
                    Toast.makeText(mActivity.getApplicationContext(), "AnimImageShowEvent exception", Toast.LENGTH_LONG).show();
                }

                Looper.loop();
            }
        });

        thread.start();*/
    }

/*    public class DisplayRunnable implements Runnable {

        public void run() {

            ImageView imgView = new ImageView(mActivity);
            imgView.setImageResource(R.drawable.white);
            imgView.setAdjustViewBounds(true);

            descLayout.addView(imgView, params);
        }
    }
*/

}
