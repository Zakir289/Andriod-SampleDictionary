package com.test.zakirelahi.englishdictionary;
/**
 * Created by zakirelahi on 10/10/15.
 */
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private DictionaryGenerator dictionaryGenerator;
    private TextView textview_word;
    private TextView textview_meaning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        textview_word = (TextView) findViewById(R.id.textview_word);
        textview_meaning = (TextView) findViewById(R.id.textview_meaning);
        dictionaryGenerator = new DictionaryGenerator(this);
    }


    public void googleSearch(View v) {
        String search_query = textview_word.getText().toString();
        search_query = search_query.replace(' ', '+');
        Uri uri = Uri.parse("http://www.google.com/#q=define:" + search_query);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void nextRandom(View v) {
        Word word = nextRandom();
        textview_word.setText(word.get_word());
        textview_meaning.setText(word.get_meaning());   //Empty, if user waits then show else move on
    }

    private Word nextRandom() {
        return dictionaryGenerator.getRandomWord();
    }

}
