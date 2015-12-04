package com.anyway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.anyway.Common.CommInfo;

public class PatternActivity extends AppCompatActivity {

    public final static String TAG = "PatternActivity";

    int PatternId;
    //String PatternChapterId;
    private AnywayEnglishApp anywayEnglishApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern);

//        Intent intent = getIntent();
//        PatternId = intent.getStringExtra("PatternId");

        anywayEnglishApp = (AnywayEnglishApp) getApplicationContext();
        //PatternId = anywayEnglishApp.getPatternId();

        //Toast.makeText(getApplicationContext(), "book" + PatternId, Toast.LENGTH_LONG).show();
    }

    public void onChapter1(View view) {
        anywayEnglishApp.setChapterId(1);

        Intent intent = new Intent(getApplicationContext(), StepActivity.class);
        //intent.putExtra("PatternChapterId", PatternId + "_" + "step1");
        startActivityForResult(intent, CommInfo.REQ_CHAPTER1_STEP_ACTIVITY);
    }

    public void onChapter2(View view) {
        anywayEnglishApp.setChapterId(2);

        Intent intent = new Intent(getApplicationContext(), StepActivity.class);
        //intent.putExtra("PatternChapterId", PatternId + "_" + "step2");
        startActivityForResult(intent, CommInfo.REQ_CHAPTER2_STEP_ACTIVITY);
    }

    public void onChapter3(View view) {
        anywayEnglishApp.setChapterId(3);

        Intent intent = new Intent(getApplicationContext(), StepActivity.class);
        //intent.putExtra("PatternChapterId", PatternId + "_" + "step3");
        startActivityForResult(intent, CommInfo.REQ_CHAPTER3_STEP_ACTIVITY);
    }

    public void onChapter4(View view) {
        anywayEnglishApp.setChapterId(4);

        Intent intent = new Intent(getApplicationContext(), StepActivity.class);
        //intent.putExtra("PatternChapterId", PatternId + "_" + "step4");
        startActivityForResult(intent, CommInfo.REQ_CHAPTER4_STEP_ACTIVITY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
