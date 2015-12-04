package com.anyway;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.anyway.Common.CommInfo;
import com.anyway.Page.ConfigurationPage;
import com.anyway.Page.LearningPage;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = "MainActivity";
    private AnywayEnglishApp anywayEnglishApp;

    int MAX_PAGE = 2;                         //View Pager의 총 페이지 갯수를 나타내는 변수 선언
    Fragment cur_fragment = new Fragment();   //현재 Viewpager가 가리키는 Fragment를 받을 변수 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        anywayEnglishApp = (AnywayEnglishApp)getApplicationContext();

        ViewPager viewPager=(ViewPager)findViewById(R.id.viewpager);        //Viewpager 선언 및 초기화
        viewPager.setAdapter(new adapter(getSupportFragmentManager()));     //선언한 viewpager에 adapter를 연결
    }

    private class adapter extends FragmentPagerAdapter {                    //adapter클래스

        public adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if(position<0 || MAX_PAGE<=position)        //가리키는 페이지가 0 이하거나 MAX_PAGE보다 많을 시 null로 리턴
                return null;

            switch (position){ //포지션에 맞는 Fragment찾아서 cur_fragment변수에 대입
                case 0:
                    cur_fragment = new LearningPage();
                    break;

                case 1:
                    cur_fragment = new ConfigurationPage();
                    break;
            }

            return cur_fragment;
        }

        @Override
        public int getCount() {
            return MAX_PAGE;
        }
    }

    public void onPattern1(View view){
        Intent intent = new Intent(getApplicationContext(), PatternActivity.class);
        anywayEnglishApp.setPatternId(1);

        startActivityForResult(intent, CommInfo.REQ_MAIN_PATTERN1_ACTIVITY);
    }

    public void onPattern2(View view){
        Intent intent = new Intent(getApplicationContext(), PatternActivity.class);
        anywayEnglishApp.setPatternId(2);
        startActivityForResult(intent, CommInfo.REQ_MAIN_PATTERN2_ACTIVITY);
    }

    public void onPattern3(View view){
        Intent intent = new Intent(getApplicationContext(), PatternActivity.class);
        anywayEnglishApp.setPatternId(3);
        startActivityForResult(intent, CommInfo.REQ_MAIN_PATTERN3_ACTIVITY);
    }

    public void onPattern4(View view){
        Intent intent = new Intent(getApplicationContext(), PatternActivity.class);
        anywayEnglishApp.setPatternId(4);
        startActivityForResult(intent, CommInfo.REQ_MAIN_PATTERN4_ACTIVITY);
    }

    public void onPattern5(View view){
        Intent intent = new Intent(getApplicationContext(), PatternActivity.class);
        anywayEnglishApp.setPatternId(5);
        startActivityForResult(intent, CommInfo.REQ_MAIN_PATTERN5_ACTIVITY);
    }

    public void onEverydayEnglish(View view){
//        Intent intent = new Intent(getApplicationContext(), PatternActivity.class);
//        startActivityForResult(intent, CommInfo.REQ_MAIN_EVERYDAY_ACTIVITY);
    }

    public void onExam(View view){
//        Intent intent = new Intent(getApplicationContext(), PatternActivity.class);
//        startActivityForResult(intent, CommInfo.REQ_MAIN_PATTERN1_ACTIVITY);
    }

    public void onNotification(View view){
//        Intent intent = new Intent(getApplicationContext(), PatternActivity.class);
//        startActivityForResult(intent, CommInfo.REQ_MAIN_PATTERN2_ACTIVITY);
    }

    public void onBookmark(View view){
//        Intent intent = new Intent(getApplicationContext(), PatternActivity.class);
//        startActivityForResult(intent, CommInfo.REQ_MAIN_PATTERN3_ACTIVITY);
    }

    public void onSettings(View view){
//        Intent intent = new Intent(getApplicationContext(), PatternActivity.class);
//        startActivityForResult(intent, CommInfo.REQ_MAIN_PATTERN4_ACTIVITY);
    }

    public void onWakeupEnglish(View view){
//        Intent intent = new Intent(getApplicationContext(), PatternActivity.class);
//        startActivityForResult(intent, CommInfo.REQ_MAIN_PATTERN5_ACTIVITY);
    }

    public void onHelp(View view){
        //Intent intent = new Intent(getApplicationContext(), PatternActivity.class);
        //startActivityForResult(intent, CommInfo.REQ_MAIN_EVERYDAY_ACTIVITY);
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
