package com.anyway;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.anyway.Event.AnimImageNotifyEvent;
import com.anyway.Event.AnimTextFadeEvent;
import com.anyway.Event.AnimTranslateEvent;
import com.anyway.Event.AudioPlayEvent;

public class ReviewingActivity extends AppCompatActivity implements AnimImageNotifyEvent.AnimEventEndListener,
                                                                    AnimTextFadeEvent.AnimEventEndListener,
                                                                    AnimTranslateEvent.AnimEventEndListener,
                                                                    AudioPlayEvent.AudioEventEndListener
{

    static final String AUDIO_URL1 = "001-0E.m4a";
    static final String AUDIO_URL2 = "001-0K.m4a";
    static final String AUDIO_URL3 = "001-1E.m4a";
    static final String AUDIO_URL4 = "001-1K.m4a";

    private MediaPlayer mediaPlayer;

    AnimImageNotifyEvent animImageNotifyEvent;
    AnimTextFadeEvent animTextFadeEvent1, animTextFadeEvent2, animTextFadeEvent3, animTextFadeEvent4;
    AnimTranslateEvent animTranslateEvent;
    AudioPlayEvent audioPlayEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_reviewing);

/*        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.reviewingLayout);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        ImageView imgView = new ImageView(this);
        imgView.setImageResource(R.drawable.warming_bg);
        imgView.setAdjustViewBounds(true);

        mainLayout.addView(imgView, params);*/

        ActionBar m_myActionBar=getSupportActionBar(); // to get activity actionbar
        m_myActionBar.hide();

        Window win = getWindow();
        win.setContentView(R.layout.activity_reviewing);

        //animImageNotifyEvent = new AnimImageNotifyEvent(this, EventInfo.SLIDE_IN, R.layout.page_start, ObjectInfo.LISTENING_START);
        animImageNotifyEvent.start();

/*        animTranslateEvent = new AnimTranslateEvent(this , EventInfo.TRANSLATE_UP);
        animTextFadeEvent1 = new AnimTextFadeEvent(this, EventInfo.FADE_IN, R.id.patternText1, ObjectInfo.LISTENING_PATTERN1_TEXT);
        animTextFadeEvent2 = new AnimTextFadeEvent(this, EventInfo.FADE_IN, R.id.patternText2, ObjectInfo.LISTENING_PATTERN2_TEXT);
        animTextFadeEvent3 = new AnimTextFadeEvent(this, EventInfo.FADE_IN, R.id.sentenceText1, ObjectInfo.LISTENING_SENTANCE1_TEXT);
        animTextFadeEvent4 = new AnimTextFadeEvent(this, EventInfo.FADE_IN, R.id.sentenceText2, ObjectInfo.LISTENING_SENTANCE2_TEXT);*/

        try {
            //audioPlayEvent = new AudioPlayEvent(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAnimImageNotifyEventTermination(int index) {
        Toast.makeText(getApplicationContext(), "onAnimMsgNotifyEventTermination " + index, Toast.LENGTH_LONG).show();

        // button Layout Show
        animTranslateEvent.start();
    }

    @Override
    public void onAnimTranslateEventTermination(int index) {
//        Toast.makeText(getApplicationContext(), "onAnimTranslateEventTermination " + index , Toast.LENGTH_LONG).show();

//        animTextFadeEvent1.start("I am just about to");
//        audioPlayEvent.start(AUDIO_URL1, ObjectInfo.LISTENING_PATTERN1_AUDIO);
    }

    @Override
    public void onAnimTextFadeEventTermination(int index) {
//        Toast.makeText(getApplicationContext(), "onAnimTextFadeEventTermination " + index , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAudioEventTermination(int index) {
//        Toast.makeText(getApplicationContext(), "onAudioEventTermination " + index, Toast.LENGTH_LONG).show();

//        if(index == ObjectInfo.LISTENING_PATTERN1_AUDIO) {
//            animTextFadeEvent2.start("지금 막 ~ 하려던 참이다.");
//            audioPlayEvent.start(AUDIO_URL2, ObjectInfo.LISTENING_PATTERN2_AUDIO);
//        }
//
//        if(index == ObjectInfo.LISTENING_PATTERN2_AUDIO) {
//            animTextFadeEvent3.start("I am just about to eat my brunch.");
//            audioPlayEvent.start(AUDIO_URL3, ObjectInfo.LISTENING_PATTERN3_AUDIO);
//        }
//
//        if(index == ObjectInfo.LISTENING_PATTERN3_AUDIO) {
//            animTextFadeEvent4.start("막 아침 겸 점심 먹으려던 참이었어.");
//            audioPlayEvent.start(AUDIO_URL4, ObjectInfo.LISTENING_PATTERN4_AUDIO);
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        audioPlayEvent.killMediaPlayer();
        audioPlayEvent = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reviewing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
