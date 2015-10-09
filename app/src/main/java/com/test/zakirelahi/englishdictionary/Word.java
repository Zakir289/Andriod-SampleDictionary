package com.test.zakirelahi.englishdictionary;

/**
 * Created by zakirelahi on 10/10/15.
 */
public class Word {
    public Word(String _word, String _meaning) {
        this._word = _word;
        this._meaning = _meaning;
    }

    public String get_meaning() {
        return _meaning;
    }

    public void set_meaning(String _meaning) {
        this._meaning = _meaning;
    }

    public String get_word() {
        return _word;
    }

    public void set_word(String _word) {
        this._word = _word;
    }

    private String _word, _meaning;
}
