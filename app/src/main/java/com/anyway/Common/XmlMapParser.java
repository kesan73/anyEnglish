package com.anyway.Common;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by taekyong on 2015-11-23.
 */
public class XmlMapParser {

    private XmlPullParser parser;

    public void XmlMapVisitor(Context context, int xmlid) {
        parser = context.getResources().getXml(xmlid);
    }

    public HashMap<String, ArrayList<String>> convert() {
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

        final String KEY = "key", STRING = "string";

        try {
            parser.next();
            int eventType = parser.getEventType();
            String lastTag = null;
            String lastKey = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {

                if (eventType == XmlPullParser.START_TAG) {
                    lastTag = parser.getName();
                }
                else if (eventType == XmlPullParser.TEXT) {

                    // some text
                    if (KEY.equalsIgnoreCase(lastTag)) {

                        // start tracking a new key
                        lastKey = parser.getText();
                    }
                    else if (STRING.equalsIgnoreCase(lastTag)) {

                        // a new string for the last encountered key
                        if (!map.containsKey(lastKey)) {
                            map.put(lastKey, new ArrayList<String>());
                        }

                        map.get(lastKey).add(parser.getText());
                    }
                }

                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}
