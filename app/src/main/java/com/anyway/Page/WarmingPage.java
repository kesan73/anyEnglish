package com.anyway.Page;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anyway.Event.AnimationDrawableEvent;
import com.anyway.R;

/**
 * Created by taekyong on 2015-11-25.
 */
public class WarmingPage extends LinearLayout {

    public ImageView mPageImage, mAnimation;
    TextView mPatternKeyTranslate, mDescText, mPatternKeyEng;
    ImageButton mAudioIconButton;
    AnimationDrawableEvent frameAnimation;

    Context mContext;

    public WarmingPage(Context context) {
        super(context);

        init(context);
    }

    public WarmingPage(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context) {

        mContext = context;

        // inflate XML layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.page_warming, this, true);

        mPatternKeyTranslate = (TextView) findViewById(R.id.patternKeyTranslate);
        mPatternKeyEng = (TextView)findViewById(R.id.keyPatternText_EN);

        AnimationDrawable animDrawable = (AnimationDrawable) mContext.getResources().getDrawable(R.drawable.anim_android);

        if (frameAnimation == null) {
            frameAnimation = new AnimationDrawableEvent(animDrawable);
        }

        mAnimation = (ImageView) findViewById(R.id.myanimation);
        mAnimation.setImageDrawable(frameAnimation);

        mDescText = (TextView)findViewById(R.id.descText);

        mPageImage = (ImageView)findViewById(R.id.pageImage);
        mAudioIconButton = (ImageButton) findViewById(R.id.audioIcon);
    }

/*
    public void setCallNumber(String number) {
        callButton.setTag(number);
    }

    public String getNameText() {
        return mPatternKeyTranslate.getText().toString();
    }*/

    public void setPatternTranslateText(String textPattern) {
        mPatternKeyTranslate.setText(textPattern);
    }

    public void setPatternEngText(String textPatternEng) {
        mPatternKeyEng.setText(textPatternEng);
    }

    public TextView getPatternEngText() {
        return mPatternKeyEng;
    }

    public AnimationDrawableEvent getFrameAnimation() {
        return frameAnimation;
    }

    public ImageView getAnimationImage() {
        return mAnimation;
    }

    public ImageButton setAudioImageButton() {
        return mAudioIconButton;
    }

    public TextView getDescText() {
        return mDescText;
    }

    public void onAudioButtonClicked(View view) {
        Toast.makeText(mContext, "Audio Button Clicked", Toast.LENGTH_LONG).show();
    }

/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        anywayEnglishApp = (AnywayEnglishApp) getActivity().getApplicationContext();
        mMapBook= anywayEnglishApp.getMap();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle extra = getArguments();
        mPageIndex = extra.getInt("index");

        Toast.makeText(getActivity(), "onCreateView :" + mPageIndex, Toast.LENGTH_LONG).show();
        View view = inflater.inflate(R.layout.page_warming, container, false);

        mAudioFile = mMapBook.get("VOICE_FILE_PREFIX").get(mPageIndex) + "E" + CommInfo.EXT;

        // Create Start Animation Event Object
        animImageNotifyEvent = new AnimImageNotifyEvent(this, EventInfo.SLIDE_IN, R.layout.page_start, ObjectInfo.WARMING_START);
        animImageNotifyEvent.InVisible();

        // Create Next Animation Event Object
        animNextNotifyEvent = new AnimImageNotifyEvent(this, EventInfo.SLIDE_OUT, R.layout.page_next, ObjectInfo.WARMING_NEXT);
        animNextNotifyEvent.InVisible();

        // Create Waiting Animation Event Object
        mDrawableImage = (ImageView)view.findViewById(R.id.myanimation);
        animationEvent = new AnimationEvent(this, R.id.myanimation, ObjectInfo.WARMING_DESC_ANIM, mDrawableImage, view);

        // Create Audio Icon Image Show Event Object
        mAudioIconButton = (ImageButton)view.findViewById(R.id.audioIcon);
        mAudioImage = new ImageShowEvent(this, EventInfo.FADE_IN, R.id.audioIcon, ObjectInfo.WARMING_AUDIO_ICON, mAudioIconButton);

        //pageImage = new ImageShowEvent(this, EventInfo.FADE_IN, R.id.pageImage, ObjectInfo.WARMING_AUDIO_ICON);
        mPageImage = (ImageView)view.findViewById(R.id.pageImage);
        mPageImage.setVisibility(View.VISIBLE);

        TextView keyPatternText1 = (TextView)view.findViewById(R.id.keyPatternText1);
        keyPatternText1.setText(mMapBook.get("PATTERN_KEY_TRANSLATE").get(mPageIndex));

        keyPatternText2 = (TextView)view.findViewById(R.id.keyPatternText2);
        keyPatternText2.setText(mMapBook.get("PATTERN_EN").get(mPageIndex));
        keyPatternText2.setVisibility(View.INVISIBLE);

        // Create Translate Animation layout Event Object
        animTranslateEvent = new AnimTranslateEvent(this, EventInfo.TRANSLATE_UP);

        // Create Fade Animation Text Event Object
        animTextFadeEvent = new AnimTextFadeEvent(this, EventInfo.FADE_IN, R.id.descText, ObjectInfo.WARMING_DESC_TEXT);

        try {
            // Create Audio Object
            audioPlayEvent = new AudioPlayEvent(this);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Animation Start Image
        //if (mPageIndex == 0)
            //animImageNotifyEvent.start();

        return view;
    }
*/
}
