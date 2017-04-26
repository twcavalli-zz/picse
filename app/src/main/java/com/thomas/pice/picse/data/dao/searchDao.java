package com.thomas.pice.picse.data.dao;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import com.thomas.pice.picse.data.model.*;

/**
 * Created by Thomas on 13/04/2017.
 */

public class SearchDao {
    Realm realm;
    public SearchDao(Realm realm) {
        this.realm = realm;
    }
    // Return all data from Search table
    public RealmResults<Search> getAllSearches() {
        return realm.where(Search.class).findAllSorted("when", Sort.DESCENDING);
    }

    // Insert word or update datetime when User
    public boolean insert(String word){
        Search item = realm.where(Search.class).equalTo("word",word, Case.INSENSITIVE).findFirst();
        try {
            realm.beginTransaction();
            if (item != null) {
                item.updateDate();
            } else {
                item = new Search();
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
