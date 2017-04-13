package com.thomas.pice.picse.dao;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

import com.thomas.pice.picse.model.*;

/**
 * Created by Thomas on 13/04/2017.
 */

public class searchDao {
    Realm realm;
    public searchDao(Realm realm) {
        this.realm = realm;
    }
    public RealmResults<search> getAllSearches() {
        return realm.where(search.class).findAllSorted("when", Sort.DESCENDING);
    }

    public boolean insert(search search){
        realm.beginTransaction();
        if (realm.copyToRealm(search) != null) {
            realm.commitTransaction();
            return true;
        } else {
            return false;
        }
    }
}
