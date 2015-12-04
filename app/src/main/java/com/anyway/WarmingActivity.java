package com.anyway;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.anyway.Common.CommInfo;
import com.anyway.Common.EventInfo;
import com.anyway.Common.ObjectInfo;
import com.anyway.Event.AnimImageNotifyEvent;
import com.anyway.Event.AnimTextFadeEvent;
import com.anyway.Event.AnimTranslateEvent;
import com.anyway.Event.AnimationEvent;
import com.anyway.Event.AudioPlayEvent;
import com.anyway.Event.ImageShowEvent;
import com.anyway.Page.WarmingPage;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.HashMap;


public class WarmingActivity extends AppCompatActivity implements AnimTranslateEvent.AnimEventEndListener,
                                                                  AnimTextFadeEvent.AnimEventEndListener,
                                                                  AnimImageNotifyEvent.AnimEventEndListener,
                                                                  ImageShowEvent.EventEndListener,
                                                                  AudioPlayEvent.AudioEventEndListener,
                                                                  AnimationEvent.AnimationEventEndListener {

    private AnywayEnglishApp anywayEnglishApp;
    HashMap<String, ArrayList<String>> mMapBook;

    CirclePageIndicator mIndicator; // Pager Indicator
    AnyViewPager mAnyPager;         // ViewPager
    ViewPagerAdapter mAdapter;      // View Adapter

    // Event
    AnimImageNotifyEvent animImageNotifyEvent, animNextNotifyEvent;
    AnimTranslateEvent animTranslateEvent;
    AnimationEvent animationEvent;
    AnimTextFadeEvent animTextFadeEvent;
    AudioPlayEvent audioPlayEvent;

    ImageShowEvent audioImage;

    int mPageIndex = 0;
    int MAX_PAGE = 3;                         //View Pager의 총 페이지 갯수를 나타내는 변수 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        anywayEnglishApp = (AnywayEnglishApp) getApplicationContext();
        mMapBook= anywayEnglishApp.getMap();

        ActionBar m_myActionBar = getSupportActionBar(); // to get activity actionbar
        m_myActionBar.hide();

        Window win = getWindow();
        win.setContentView(R.layout.activity_warming); //첫번째에 메인을 깔고

        // 뷰페이저 객체를 참조하고 어댑터를 설정합니다.
        mAnyPager = (AnyViewPager) findViewById(R.id.warmingviewpager);
        mAdapter = new ViewPagerAdapter(this);
        mAnyPager.setAdapter(mAdapter);

        // ViewPager Indicator
        mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        mIndicator.setFillColor(R.color.default_circle_indicator_fill_color);
        mIndicator.setRadius(5);
        mIndicator.setViewPager(mAnyPager);

        // Create Start Animation Event Object
        animImageNotifyEvent = new AnimImageNotifyEvent(this, EventInfo.SLIDE_IN, R.layout.page_start, ObjectInfo.WARMING_START);
        animImageNotifyEvent.InVisible();

        // Create Next Animation Event Object
        animNextNotifyEvent = new AnimImageNotifyEvent(this, EventInfo.SLIDE_OUT, R.layout.page_next, ObjectInfo.WARMING_NEXT);
        animNextNotifyEvent.InVisible();

        // Create Translate Animation layout Event Object
        LinearLayout layout = (LinearLayout)findViewById(R.id.buttonLayout);
        animTranslateEvent = new AnimTranslateEvent(this, EventInfo.TRANSLATE_UP, ObjectInfo.WARMING_BUTTON_LAYOUT, layout);

        // Create Waiting Animation Event Object
        //ImageView drawableImage = (ImageView)mAnyPager.getfindViewById(R.id.myanimation);
        animationEvent = new AnimationEvent(this, ObjectInfo.WARMING_DESC_ANIM);

        audioImage = new ImageShowEvent(this, EventInfo.FADE_IN, ObjectInfo.WARMING_AUDIO_ICON);

        // Create Fade Animation Text Event Object
        animTextFadeEvent = new AnimTextFadeEvent(this, EventInfo.FADE_IN, R.id.descText, ObjectInfo.WARMING_DESC_TEXT);

        try {
            audioPlayEvent = new AudioPlayEvent(this);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //mAnyPager.setPagingDisabled();

        mAnyPager.addOnPageChangeListener(new AnyViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //Toast.makeText(getApplicationContext(), "addOnPageChangeListener::onPageSelected : " + position, Toast.LENGTH_SHORT).show();

                mPageIndex = position;
                //WarmingPage warmingPage = (WarmingPage)mAnyPager.getChildAt(mAnyPager.getCurrentItem());
                //WarmingPage warmingPage = (WarmingPage)mAdapter.getViewAtPosition(position);
                //animationEvent.setAnimationImage(warmingPage.getAnimationImage(animationEvent.frameAnimation));
                //warmingPage.setAnimationVisible();

                if (animTranslateEvent != null) {
                    animTranslateEvent.start();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * 뷰페이저를 위한 어댑터 정의
     */
    public class ViewPagerAdapter extends PagerAdapter {

        private Context mContext;
        private SparseArray instantiatedViewsArray;

        public ViewPagerAdapter( Context context ) {
            mContext = context;
            this.instantiatedViewsArray = new SparseArray();
        }

        public int getCount() {
            return MAX_PAGE;
        }

        public Object instantiateItem(ViewGroup container, int position) {

            // create a instance of the page and set data
            WarmingPage warmingPage = new WarmingPage(mContext);

            // Key Pattern Translate String
            warmingPage.setPatternTranslateText(mMapBook.get("PATTERN_KEY_TRANSLATE").get(position * 2));
            //warmingPage.setDescText(mMapBook.get("KR_DESCRIPTION").get(position));
            //warmingPage.setAudioFile(mMapBook.get("VOICE_FILE_PREFIX").get(position) + "E" + CommInfo.EXT);
            warmingPage.setPatternEngText(mMapBook.get("PATTERN_EN").get(position));

            //warmingPage.mAnimation.setVisibility(View.VISIBLE);
            //animationEvent.frameAnimation.setVisible(true, false);
            //animationEvent.frameAnimation.start();
            // Create Translate Animation layout Event Object
            //animTranslateEvent = new AnimTranslateEvent(this, EventInfo.TRANSLATE_UP);

            // Create Fade Animation Text Event Object
            //animTextFadeEvent = new AnimTextFadeEvent(this, EventInfo.FADE_IN, R.id.descText, ObjectInfo.WARMING_DESC_TEXT);

            //page.setImage(resIds[position]);
            //page.setCallNumber(callNumbers[position]);

            // 컨테이너에 추가
            container.addView(warmingPage, position);

            warmingPage = (WarmingPage)mAnyPager.getChildAt(position);
            instantiatedViewsArray.put(position, warmingPage);

            int curPosition = mAnyPager.getCurrentItem();

            if (position == 0 && curPosition == 0) { // 최초 첫페이지 자동 진행.

                if (animImageNotifyEvent != null) {
                    animImageNotifyEvent.start();
                }
            }

            return warmingPage;
        }

        public void destroyItem(ViewGroup container, int position, Object view) {
            container.removeView((View)view);
            instantiatedViewsArray.remove(position);
        }

        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        public View getViewAtPosition(int position) {
            return (View)instantiatedViewsArray.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            int childCount = mAnyPager.getChildCount();

            for (int i = 0; i < childCount; i++) {
                View child = mAnyPager.getChildAt(i);

                if (isViewFromObject(child, object)) {
                    return i;
                }
            }

            return POSITION_NONE;
        }
    }

    @Override
    public void onAnimImageNotifyEventTermination(int index) {
        //Toast.makeText(getApplicationContext(), "onAnimMsgNotifyEventTermination " + index, Toast.LENGTH_LONG).show();

        if (index == ObjectInfo.WARMING_START) {
            // button Layout Show
            animTranslateEvent.start();
        }
    }

    @Override
    public void onAnimTranslateEventTermination(int index) {
        //Toast.makeText(getApplicationContext(), "onAnimTranslateEventTermination " + index, Toast.LENGTH_LONG).show();

        // Play Animation
        WarmingPage warmingPage = (WarmingPage)mAdapter.getViewAtPosition(mPageIndex);
        warmingPage.getAnimationImage().setVisibility(View.VISIBLE);

        animationEvent.start(warmingPage.getFrameAnimation(), warmingPage.getAnimationImage());
    }

    @Override
    public void onAnimTextFadeEventTermination(int index) {
        //Toast.makeText(getApplicationContext(), "onAnimTextFadeEventTermination " + index , Toast.LENGTH_LONG).show();

        // Play Audio
        //001-0E.m4a
        audioPlayEvent.start(mMapBook.get("VOICE_FILE_PREFIX").get(mPageIndex) + "E" + CommInfo.EXT, ObjectInfo.WARMING_PATTERN_TEXT);
    }

    @Override
    public void onAudioEventTermination(int index) {
        //Toast.makeText(getApplicationContext(), "onAudioEventTermination " + index, Toast.LENGTH_LONG).show();

        // Audio Image Show
        WarmingPage warmingPage = (WarmingPage)mAdapter.getViewAtPosition(mPageIndex);
        audioImage.start(warmingPage.setAudioImageButton());
    }

    @Override
    public void onAnimationEventTermination(int index) {
        Toast.makeText(getApplicationContext(), "onAnimationEventTermination " + index, Toast.LENGTH_LONG).show();

        // Show Text
        WarmingPage warmingPage = (WarmingPage)mAdapter.getViewAtPosition(mPageIndex);
        animTextFadeEvent.start(warmingPage.getDescText(), mMapBook.get("KR_DESCRIPTION").get(mPageIndex));
        warmingPage.mPatternKeyEng.setVisibility(View.VISIBLE);
        warmingPage.mPageImage.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onImageShowEventTermination(int index) {
        //Toast.makeText(getApplicationContext(), "onImageShowEventTermination " + index, Toast.LENGTH_LONG).show();

        // Show Next Image
        animNextNotifyEvent.start();
    }

/*
    private class Adapter extends FragmentPagerAdapter {                    //adapter클래스

        private Fragment mCurrentFragment;
        Bundle bundle;

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            if (mCurrentFragment != object) {
                mCurrentFragment = (Fragment) object;
            }

            super.setPrimaryItem(container, position, object);
        }

        public Fragment getCurrentFragment() {
            return mCurrentFragment;
        }

        @Override
        public Fragment getItem(int position) {

            if(position<0 || MAX_PAGE<=position)        //가리키는 페이지가 0 이하거나 MAX_PAGE보다 많을 시 null로 리턴
                return null;

            switch (position){ //포지션에 맞는 Fragment찾아서 cur_fragment변수에 대입
                case 0:
                    cur_fragment = new WarmingPage();

                    bundle = new Bundle();
                    bundle.putInt("index", position);
                    cur_fragment.setArguments(bundle);

                    Toast.makeText(getApplicationContext(), "Activity : " + position, Toast.LENGTH_SHORT).show();
                    break;

                case 1:
                    cur_fragment = new WarmingPage();

                    bundle = new Bundle();
                    bundle.putInt("index", position);
                    cur_fragment.setArguments(bundle);
                    Toast.makeText(getApplicationContext(), "Activity : " + position, Toast.LENGTH_SHORT).show();

                    break;

                case 2:
                    cur_fragment = new WarmingPage();

                    bundle = new Bundle();
                    bundle.putInt("index", position);
                    cur_fragment.setArguments(bundle);
                    Toast.makeText(getApplicationContext(), "Activity : " + position, Toast.LENGTH_SHORT).show();

                    break;
            }

            return cur_fragment;
        }

        @Override
        public int getCount() {
            return MAX_PAGE;
        }
    }
*/
    @Override
    protected void onDestroy() {
        super.onDestroy();

        audioPlayEvent.killMediaPlayer();
        audioPlayEvent = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listening, menu);
        return true;
    }

    public void onShowDescClicked(View view) {

        //Fragment fragment = mAdapter.getCurrentFragment();
        //WarmingPage warmingPage = (WarmingPage) fragment;
    }

    public void onCloseClicked(View view) {

    }

    public void onBookMarkingClicked(View view) {

    }

    public void onReloadClicked(View view) {

        //WarmingPage ttm = (WarmingPage) getSupportFragmentManager().findFragmentById(R.id.warmingviewpager);
        //ttm.onAnimImageNotifyEventTermination(1);

        //ImageView pageImage = (ImageView)(R.id.pageImage);
        //pageImage.setVisibility(View.VISIBLE);

//        cur_fragment.animTextFadeEvent.InVisible();
//        mAudioImage.InVisible();
//        animTranslateEvent.start();
    }

/*
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            int x = (int)event.getX();
            int y = (int)event.getY();

            Bitmap bitmapScreen = Bitmap.createBitmap(mDeviceScreenWidth, mDeviceScreenHeight, Bitmap.Config.ARGB_8888);

            if(x < 0 || y < 0)
                return false;

            int ARGB = bitmapScreen.getPixel(x, y);

            if(Color.alpha(ARGB) == 0) {

                if (startLayout != null && startLayout.getVisibility() == View.VISIBLE) {
                    startLayout.setVisibility(View.GONE);
                }
            }

            return true;
        }

        return false;
    }*/

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
