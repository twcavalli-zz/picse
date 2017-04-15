package com.thomas.pice.picse.data.dao;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import com.thomas.pice.picse.data.model.*;

/**
 * Created by Thomas on 13/04/2017.
 */

public class searchDao {
    Realm realm;
    public searchDao(Realm realm) {
        this.realm = realm;
    }
    // Return all data from search table
    public RealmResults<search> getAllSearches() {
        return realm.where(search.class).findAllSorted("when", Sort.DESCENDING);
    }

    // Insert word or update datetime when user
    public boolean insert(String word){
        search item = realm.where(search.class).equalTo("word",word, Case.INSENSITIVE).findFirst();
        try {
            realm.beginTransaction();
            if (item != null) {
                item.updateDate();
            } else {
                item = new search();
                item.setWord(word);
                realm.copyToRealm(item);
            }
            realm.commitTransaction();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
