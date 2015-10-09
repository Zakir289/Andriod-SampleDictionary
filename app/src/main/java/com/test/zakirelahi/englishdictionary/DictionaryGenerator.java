package com.test.zakirelahi.englishdictionary;

import android.content.Context;
import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by zakirelahi on 09/10/15.
 */
public class DictionaryGenerator {
    private Map<String, String> word_map;
    private final Context context;

    public DictionaryGenerator(Context ctx) {

        this.context = ctx;

        try {
            this.word_map = readXml();
        }
        catch (XmlPullParserException e) {

        }
        catch (Exception e) {

        }
    }


    public Word getRandomWord() {
        Random rand = new Random();
        List<String> keys = new ArrayList<String>(word_map.keySet());
        String randomKey = keys.get(rand.nextInt(keys.size()));
        String value = word_map.get(randomKey);
        return (new Word(randomKey, value));
    }

    public Map<String, String> readXml() throws XmlPullParserException, IOException {
        XmlResourceParser xrp = context.getResources().getXml(R.xml.sample_words);

        xrp.next();
        int eventType = xrp.getEventType();

        String word = null, meaning = null;

        Map<String, String> word_pair_map = new HashMap<String, String>();

        //parsing
        while (eventType != XmlPullParser.END_DOCUMENT) {

            if (eventType == XmlPullParser.START_TAG
                    && xrp.getName().equalsIgnoreCase("pair")) {

                eventType = xrp.next();
                if (eventType == XmlPullParser.START_TAG
                        && xrp.getName().equalsIgnoreCase("meaning")) {
                    eventType = xrp.next();
                    if (eventType == XmlPullParser.TEXT) {
                        meaning = xrp.getText();
                    }
                }

                xrp.next();
                eventType = xrp.next();
                if (eventType == XmlPullParser.START_TAG
                        && xrp.getName().equalsIgnoreCase("word")) {
                    eventType = xrp.next();
                    if (eventType == XmlPullParser.TEXT) {
                        word = xrp.getText();
                    }
                }
            }

            else if (eventType == XmlPullParser.END_TAG && xrp.getName().equalsIgnoreCase("pair")) {
                if (word != null && meaning != null && word.length() != 0 && meaning.length() != 0) {
                    if (word_pair_map.get(word) == null) {
                        word_pair_map.put(word, meaning);
                    }
                }
            }
            eventType = xrp.next();
        }
        return word_pair_map;

    }
}
