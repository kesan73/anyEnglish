package com.anyway;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by taekyong on 2015-11-22.
 */
public class AnywayEnglishApp extends Application{

    private int PatternId;
    private int ChapterId;

    HashMap<String, ArrayList<String>> map;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public int getChapterId() {
        return ChapterId;
    }

    public void setChapterId(int chapterId) {
        ChapterId = chapterId;
    }

    public int getPatternId() {
        return PatternId;
    }

    public void setPatternId(int patternId) {
        PatternId = patternId;
    }

    public HashMap<String, ArrayList<String>> getMap() {
        return map;
    }

    public void setMap(HashMap<String, ArrayList<String>> map) {
        this.map = map;
    }
}
