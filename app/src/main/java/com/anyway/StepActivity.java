package com.anyway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.anyway.Common.CommInfo;
import com.anyway.Common.XmlMapParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class StepActivity extends AppCompatActivity {

    public final static String TAG = "StepActivity";
    String mPatternChapterId; // xml file name
    private AnywayEnglishApp anywayEnglishApp;
    XmlMapParser xmlMapParser = null;
    HashMap<String, ArrayList<String>> mapBook = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        //Intent intent = getIntent();
        //PatternChapterId = intent.getStringExtra("PatternChapterId");
        anywayEnglishApp = (AnywayEnglishApp) getApplicationContext();

        mPatternChapterId = "book" + anywayEnglishApp.getPatternId() + "_" + "step" + anywayEnglishApp.getChapterId();
        Toast.makeText(getApplicationContext(), mPatternChapterId, Toast.LENGTH_LONG).show();

        // xml parser
        // Load XML for parsing.
        if (xmlMapParser == null) {
            xmlMapParser = new XmlMapParser();
        }

        //if (anywayEnglishApp.getPatternId() == 1 && anywayEnglishApp.getChapterId() == 1) {
        xmlMapParser.XmlMapVisitor(getApplicationContext(), R.xml.book1_step1);
        //}

        mapBook = xmlMapParser.convert();
        anywayEnglishApp.setMap(mapBook);

/*        if (mapPlist != null) {

            // HashMap에 포함된 Key , Value를 Set에 담고 iterator에 값을 Set 정보를 담아 준다.
            Set<Entry<String, ArrayList<String>>> set = mapPlist.entrySet();
            Iterator<Entry<String, ArrayList<String>>> it = set.iterator();

            // HashMap에 포함된 key, value 값을 호출 한다.
            while (it.hasNext()) {

                Map.Entry<String, ArrayList<String>> e = (Map.Entry<String, ArrayList<String>>)it.next();
                System.out.println("key : " + e.getKey() + ", value : " + e.getValue());
            }
        }*/

    }

    private void getXMLfromResource() throws IOException, XmlPullParserException {

        // Create ResourceParser for XML file
//        XmlResourceParser xpp = getResources().getXml(R.xml.book1_step1);
//
//        // check state
//        int eventType = xpp.getEventType();
//
//        while (eventType != XmlPullParser.END_DOCUMENT) {
//            // instead of the following if/else if lines
//            // you should custom parse your xml
//            if(eventType == XmlPullParser.START_DOCUMENT) {
//                System.out.println("Start document");
//            } else if(eventType == XmlPullParser.START_TAG) {
//                System.out.println("Start tag :"+xpp.getName());
//            } else if(eventType == XmlPullParser.END_TAG) {
//                System.out.println("End tag :"+xpp.getName());
//            } else if(eventType == XmlPullParser.TEXT) {
//
//                if (bViewId) {
//                    viewId = xpp.getText();
//                    mViewIdList.add(xpp.getText());
//                    bViewId = false;
//                }
//
//                if(xpp.getText().equals("VOICE_FILE_PREFIX")) {
//                    bViewId = true;
//                } else {
//                    bViewId = false;
//                }
//
//                if (bPatternEN) {
//                    mPatternEN.put(viewId, xpp.getText());
//                    bPatternEN = false;
//                }
//
//                if(xpp.getText().equals("PATTERN_EN")) {
//                    bPatternEN = true;
//                } else {
//                    bPatternEN = false;
//                }
//
//                System.out.println("Text :"+xpp.getText());
//            }
//            eventType = xpp.next();
//        }
//
//        // indicate app done reading the resource.
//        xpp.close();
    }

    public void onWarmingClicked(View view) {

        Intent intent = new Intent(getApplicationContext(), WarmingActivity.class);
        startActivityForResult(intent, CommInfo.REQ_STEP_WARMING_ACTIVITY);
    }

    public void onListeningClicked(View view) {

        Intent intent = new Intent(getApplicationContext(), ListeningActivity.class);
        startActivityForResult(intent, CommInfo.REQ_STEP_LISTENING_ACTIVITY);
    }

    public void onReviewingClicked(View view) {

        Intent intent = new Intent(getApplicationContext(), ReviewingActivity.class);
        startActivityForResult(intent, CommInfo.REQ_REVIEWING_STEP_ACTIVITY);
    }

    public void onAnsweringClicked(View view) {

        Intent intent = new Intent(getApplicationContext(), AnsweringActivity.class);
        startActivityForResult(intent, CommInfo.REQ_ANSWERING_STEP_ACTIVITY);
    }

    public void onSelectingClicked(View view) {

        Intent intent = new Intent(getApplicationContext(), SelectingActivity.class);
        startActivityForResult(intent, CommInfo.REQ_SELECTING_STEP_ACTIVITY);
    }

    public void onPlayingClicked(View view) {

        Intent intent = new Intent(getApplicationContext(), PlayingActivity.class);
        startActivityForResult(intent, CommInfo.REQ_PLAYING_STEP_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case CommInfo.REQ_WARMING_STEP_ACTIVITY :
                Toast.makeText(getApplicationContext(), "REQ_WARMING_STEP_ACTIVITY", Toast.LENGTH_SHORT).show();
                break;
            case CommInfo.REQ_LISTENING_STEP_ACTIVITY :
                Toast.makeText(getApplicationContext(), "REQ_LISTENING_STEP_ACTIVITY", Toast.LENGTH_SHORT).show();
                break;
            case CommInfo.REQ_REVIEWING_STEP_ACTIVITY :
                Toast.makeText(getApplicationContext(), "REQ_REVIEWING_STEP_ACTIVITY", Toast.LENGTH_SHORT).show();
                break;
            case CommInfo.REQ_ANSWERING_STEP_ACTIVITY:
                Toast.makeText(getApplicationContext(), "REQ_ANSWERING_STEP_ACTIVITY", Toast.LENGTH_SHORT).show();
                break;
            case CommInfo.REQ_SELECTING_STEP_ACTIVITY :
                Toast.makeText(getApplicationContext(), "REQ_SELECTING_STEP_ACTIVITY", Toast.LENGTH_SHORT).show();
                break;
            case CommInfo.REQ_PLAYING_STEP_ACTIVITY :
                Toast.makeText(getApplicationContext(), "REQ_PLAYING_STEP_ACTIVITY", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getApplicationContext(), "Unknown", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mapBook != null) {
            mapBook.clear();
            mapBook = null;
        }
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
