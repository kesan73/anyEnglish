package com.anyway.Event;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

/**
 * Created by taekyong on 2015-10-11.
 */
public class AudioPlayEvent {

    Activity mActivity;
    int mAnimTypeId;
    int mObjId;

    private MediaPlayer mediaPlayer;
    AudioEventEndListener endListener = null;

    // Notify Text Event Animation Termination
    public interface AudioEventEndListener {
        void onAudioEventTermination(int index);
    }

    public AudioPlayEvent(Activity mActivity) {
        this.mActivity = mActivity;

        init();
    }

    private boolean init() {

        if(mActivity instanceof  AudioEventEndListener) {
            endListener = (AudioEventEndListener) mActivity;
        } else {
            return false;
        }

        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        mediaPlayer = new MediaPlayer();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.stop();
                mediaPlayer.reset();
                endListener.onAudioEventTermination(mObjId);
            }
        });

        return true;
    }

    public void start(String url, int ObjId) {

        this.mObjId = ObjId;

        try {
            AssetFileDescriptor afd = mActivity.getAssets().openFd(url);

            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void killMediaPlayer() {

        if (mediaPlayer != null) {

            try {
                mediaPlayer.stop();
                mediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
