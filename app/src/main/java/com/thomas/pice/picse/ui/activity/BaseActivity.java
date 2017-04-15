package com.thomas.pice.picse.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.thomas.pice.picse.R;

/**
 * Created by Thomas on 13/04/2017.
 *
 * Handle toolbar in the activities.
 * App activities must extends BaseActivity
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Toolbar actionBarToolbar;
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    protected ActionBar getActionBarToolbar() {
        if (actionBarToolbar == null) {
            actionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (actionBarToolbar != null) {
                setSupportActionBar(actionBarToolbar);
            }
        }
        return getSupportActionBar();
    }
}
