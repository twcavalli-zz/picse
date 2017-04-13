package com.thomas.pice.picse.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.thomas.pice.picse.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
    }

    private void setupToolbar() {
        final android.support.v7.app.ActionBar ab = getActionBarToolbar();
        ab.setDisplayHomeAsUpEnabled(false);
        ab.setTitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search_history:
                // Open History Activity
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
