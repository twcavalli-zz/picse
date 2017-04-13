package com.thomas.pice.picse.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.thomas.pice.picse.R;

public class HistoryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setupToolbar();
    }

    private void setupToolbar() {
        final android.support.v7.app.ActionBar ab = getActionBarToolbar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

}
