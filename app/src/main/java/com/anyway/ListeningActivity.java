package com.anyway;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import com.anyway.Common.CommInfo;
import com.anyway.Common.ObjectInfo;
import com.anyway.Event.AnimImageNotifyEvent;
import com.anyway.Event.AnimTextFadeEvent;
import com.anyway.Event.AnimTranslateEvent;
import com.anyway.Event.AudioPlayEvent;
import com.anyway.Event.ImageShowEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class ListeningActivity extends AppCompatActivity implements AnimImageNotifyEvent.AnimEventEndListener,
                                                                    AnimTextFadeEvent.AnimEventEndListener,
                                                                    ImageShowEvent.EventEndListener,
                                                                    AnimTranslateEvent.AnimEventEndListener,
                                                                    AudioPlayEvent.AudioEventEndListener
{
    private AnywayEnglishApp anywayEnglishApp;
    HashMap<String, ArrayList<String>> mMapBook;

//    static final String AUDIO_URL1 = "001-0E.m4a";
//    static final String AUDIO_URL2 = "001-0K.m4a";
//    static final String AUDIO_URL3 = "001-1E.m4a";
//    static final String AUDIO_URL4 = "001-1K.m4a";
    String mAudio_Pattern_EN, mAudio_Pattern_KR, mAudio_Sentence_EN, mAudio_Sentence_KR;

    private MediaPlayer mediaPlayer;

    AnimImageNotifyEvent animImageNotifyEvent, animNextNotifyEvent;
    AnimTextFadeEvent animTextFadeEvent1, animTextFadeEvent2, animTextFadeEvent3, animTextFadeEvent4;
    AnimTranslateEvent animTranslateEvent;
    AudioPlayEvent audioPlayEvent;

    ImageButton mAudioIconButton;
    ImageShowEvent mAudioImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_listening);

        anywayEnglishApp = (AnywayEnglishApp) getApplicationContext();
        mMapBook= anywayEnglishApp.getMap();

/*        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        pager.setAdapter(adapter);*/

        ActionBar m_myActionBar=getSupportActionBar(); // to get activity actionbar
        m_myActionBar.hide();

        Window win = getWindow();
        win.setContentView(R.layout.activity_listening);

        mAudio_Pattern_EN = mMapBook.get("VOICE_FILE_PREFIX").get(0) + "E" + CommInfo.EXT;
        mAudio_Pattern_KR = mMapBook.get("VOICE_FILE_PREFIX").get(0) + "K" + CommInfo.EXT;
        mAudio_Sentence_EN = mMapBook.get("VOICE_FILE_PREFIX").get(1) + "E" + CommInfo.EXT;
        mAudio_Sentence_KR = mMapBook.get("VOICE_FILE_PREFIX").get(1) + "K" + CommInfo.EXT;

        //animImageNotifyEvent = new AnimImageNotifyEvent(this, EventInfo.SLIDE_IN, R.layout.page_start, ObjectInfo.LISTENING_START);
        //animNextNotifyEvent = new AnimImageNotifyEvent(this, EventInfo.SLIDE_OUT, R.layout.page_next, ObjectInfo.LISTENING_NEXT);
        animNextNotifyEvent.InVisible();

//        animTranslateEvent = new AnimTranslateEvent(this , EventInfo.TRANSLATE_UP);
//        animTextFadeEvent1 = new AnimTextFadeEvent(this, EventInfo.FADE_IN, R.id.patternText1, ObjectInfo.LISTENING_PATTERN1_TEXT);
//        animTextFadeEvent2 = new AnimTextFadeEvent(this, EventInfo.FADE_IN, R.id.patternText2, ObjectInfo.LISTENING_PATTERN2_TEXT);
//        animTextFadeEvent3 = new AnimTextFadeEvent(this, EventInfo.FADE_IN, R.id.sentenceText1, ObjectInfo.LISTENING_SENTANCE1_TEXT);
//        animTextFadeEvent4 = new AnimTextFadeEvent(this, EventInfo.FADE_IN, R.id.sentenceText2, ObjectInfo.LISTENING_SENTANCE2_TEXT);

        mAudioIconButton = (ImageButton) findViewById(R.id.audioIcon);
        //mAudioImage = new ImageShowEvent(this, EventInfo.FADE_IN, R.id.audioIcon, ObjectInfo.LISTENING_AUDIO_ICON, mAudioIconButton);

        try {
            //audioPlayEvent = new AudioPlayEvent(this);

        } catch (Exception e) {
            e.printStackTrace();
        }

        animImageNotifyEvent.start();
    }

    @Override
    public void onAnimImageNotifyEventTermination(int index) {
        Toast.makeText(getApplicationContext(), "onAnimMsgNotifyEventTermination " + index , Toast.LENGTH_LONG).show();

        if (index == ObjectInfo.LISTENING_START) {

            // button Layout Show
            animTranslateEvent.start();
        }
    }

    @Override
    public void onAnimTranslateEventTermination(int index) {
        Toast.makeText(getApplicationContext(), "onAnimTranslateEventTermination " + index , Toast.LENGTH_LONG).show();

        //animTextFadeEvent1.start(mMapBook.get("PATTERN_EN").get(0));
        audioPlayEvent.start(mAudio_Pattern_EN, ObjectInfo.LISTENING_PATTERN1_AUDIO);
    }

    @Override
    public void onAnimTextFadeEventTermination(int index) {
        Toast.makeText(getApplicationContext(), "onAnimTextFadeEventTermination " + index , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAudioEventTermination(int index) {
        Toast.makeText(getApplicationContext(), "onAudioEventTermination " + index, Toast.LENGTH_LONG).show();

        if(index == ObjectInfo.LISTENING_PATTERN1_AUDIO) {
            //animTextFadeEvent2.start(mMapBook.get("PATTERN_KEY_TRANSLATE").get(0));
            audioPlayEvent.start(mAudio_Pattern_KR, ObjectInfo.LISTENING_PATTERN2_AUDIO);
        }

        if(index == ObjectInfo.LISTENING_PATTERN2_AUDIO) {
            //animTextFadeEvent3.start(mMapBook.get("SENTENCE_EN").get(0));
            audioPlayEvent.start(mAudio_Sentence_EN, ObjectInfo.LISTENING_PATTERN3_AUDIO);
        }

        if(index == ObjectInfo.LISTENING_PATTERN3_AUDIO) {
            //animTextFadeEvent4.start(mMapBook.get("SENTENCE_KR").get(0));
            audioPlayEvent.start(mAudio_Sentence_KR, ObjectInfo.LISTENING_PATTERN4_AUDIO);
        }

        if (index == ObjectInfo.LISTENING_PATTERN4_AUDIO) {
            //mAudioImage.start();
        }
    }

    @Override
    public void onImageShowEventTermination(int index) {
        Toast.makeText(getApplicationContext(), "onImageShowEventTermination " + index, Toast.LENGTH_LONG).show();

        // Show Next Image
        animNextNotifyEvent.start();
    }

    public void onShowDescClicked(View view) {

    }

    public void onCloseClicked(View view) {

    }

    public void onBookMarkingClicked(View view) {

    }

    public void onReloadClicked(View view) {

        animTextFadeEvent2.InVisible();
        animTextFadeEvent3.InVisible();
        animTextFadeEvent4.InVisible();
        mAudioImage.InVisible();

        animTranslateEvent.start();
    }

    public void reReload(View view) {

        Toast.makeText(getApplicationContext(), "reReload Clicked", Toast.LENGTH_LONG).show();
    }

    public void onAudioButtonClicked(View view) {
        Toast.makeText(getApplicationContext(), "Audio Button Clicked", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        audioPlayEvent.killMediaPlayer();
        audioPlayEvent = null;
    }

//    public class ViewPagerAdapter extends PagerAdapter {
//
//        //private String[] names = { "John", "Mike", "Sean" };
//        //private int[] resIds = {R.drawable.dream01, R.drawable.dream02, R.drawable.dream03};
//        //private String[] callNumbers = {"010-7777-1234", "010-7788-1234", "010-7799-1234"};
//
//        private Context mContext;
//
//        public ViewPagerAdapter( Context context ) {
//            mContext = context;
//        }
//
//        /**
//         * 페이지 갯수
//         */
//        public int getCount() {
//            return 3;
//        }
//
//        public Object instantiateItem(ViewGroup container, int position) {
//            // create a instance of the page_learning and set data
//            ListeningPage page_learning = new ListeningPage(mContext);
//            //page_learning.setNameText(names[position]);
//            //page_learning.setImage(resIds[position]);
//            //page_learning.setCallNumber(callNumbers[position]);
//
//            page_learning.updateIndexes();
//            // 컨테이너에 추가
//            container.addView(page_learning, position);
//
//
//            return page_learning;
//        }
//
//        public void destroyItem(ViewGroup container, int position, Object view) {
//            container.removeView((View)view);
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return view.equals(object);
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listening, menu);
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
