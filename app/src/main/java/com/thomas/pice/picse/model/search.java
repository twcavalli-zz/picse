package com.thomas.pice.picse.model;

import java.util.Date;

import io.realm.RealmObject;

public class search extends RealmObject {
    private String word;
    private Date when;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
