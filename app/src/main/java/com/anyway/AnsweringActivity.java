package com.anyway;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class AnsweringActivity extends AppCompatActivity {

    TextView textView;

    ImageView imageView;
    AnimationDrawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answering);

/*        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.answeringMainLayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        ImageView imgView = new ImageView(this);
        imgView.setImageResource(R.drawable.examplge_image_bg);
        imgView.setAdjustViewBounds(true);

        mainLayout.addView(imgView, params);*/

        textView = (TextView) findViewById(R.id.textView);
        textView.setText("I am just about to");

        imageView =(ImageView)findViewById(R.id.imageView);

        drawable = (AnimationDrawable) getResources().getDrawable(R.drawable.emotion_list);
        imageView.setImageDrawable(drawable);

//        Resources res = getResources();
//        Drawable frame1 = res.getDrawable(R.drawable.emotion_ah);
//        Drawable frame2 = res.getDrawable(R.drawable.emotion_grin);
//        Drawable frame3 = res.getDrawable(R.drawable.emotion_smile);
//        Drawable frame4 = res.getDrawable(R.drawable.emotion_what);
//
//        int duration = 500;
//        drawable = new AnimationDrawable();
//        drawable.addFrame(frame1, duration);
//        drawable.addFrame(frame2, duration);
//        drawable.addFrame(frame3, duration);
//        drawable.addFrame(frame4, duration);
//        drawable.setOneShot(true);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(hasFocus) {
            imageView.setBackground(drawable);

            drawable.setVisible(true, true);
            drawable.start();
        } else {
            drawable.stop();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_answering, menu);
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
