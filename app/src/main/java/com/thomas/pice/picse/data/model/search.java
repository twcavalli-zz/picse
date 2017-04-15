package com.thomas.pice.picse.data.model;

import java.util.Date;
import io.realm.RealmObject;

/**
 * Created by Thomas on 13/04/2017.
 */

public class search extends RealmObject {
    private String word;
    private Date when;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
        updateDate();
    }

    public void updateDate() {
        this.when = new Date();
    }
}
