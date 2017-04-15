package com.thomas.pice.picse.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.thomas.pice.picse.R;
import com.thomas.pice.picse.data.dao.searchDao;
import com.thomas.pice.picse.remote.manager.apiDataManager;
import com.thomas.pice.picse.remote.object.media;
import com.thomas.pice.picse.ui.adapter.card_adapter;
import java.util.ArrayList;
import io.realm.Realm;
import rx.Subscriber;

/**
 * Created by Thomas on 13/04/2017.
 *
 * Activity to do the searches.
 */
public class MainActivity extends BaseActivity {
    private FloatingActionButton fab_search;
    private EditText input_search;
    private TextInputLayout input_layout_search;
    private Realm realm;
    private apiDataManager dataManager;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ProgressBar pgb_loading;
    ArrayList<media> searchData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input_search = (EditText) findViewById(R.id.input_search);
        fab_search = (FloatingActionButton) findViewById(R.id.fab_search);
        input_layout_search = (TextInputLayout) findViewById(R.id.input_layout_search);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_results);
        pgb_loading = (ProgressBar) findViewById(R.id.pgb_loading);

        setupToolbar();

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // In case of screen rotation
        if (savedInstanceState != null ){
            // Load data from saved instance
            searchData = savedInstanceState.getParcelableArrayList("search_data");
            mAdapter = new card_adapter(MainActivity.this, searchData);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            searchData = new ArrayList();
        }


        dataManager = new apiDataManager(MainActivity.this);
        fab_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnline()) {
                    if (input_search.getText().length() <= 0) {
                        input_layout_search.setError(getResources().getString(R.string.insert_a_text));
                    } else {
                        search();
                    }
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.no_connection), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void search() {
        //Insert the search word on database
        pgb_loading.setVisibility(View.VISIBLE);
        input_layout_search.setError(null);

        realm = Realm.getDefaultInstance();
        searchDao dao = new searchDao(realm);
        dao.insert(input_search.getText().toString());
        realm.close();

        InputMethodManager inputManager = (InputMethodManager) MainActivity.this.getSystemService(MainActivity.this.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

        // Clean list data
        if (mRecyclerView.getAdapter() != null) {
            searchData.clear();
            mAdapter.notifyDataSetChanged();
        }

        dataManager.startSearch(input_search.getText().toString())
                .subscribe(new Subscriber<media>() {
                    @Override
                    public void onCompleted() {
                        // When finish load all data, put it into the recycler
                        pgb_loading.setVisibility(View.INVISIBLE);
                        mAdapter = new card_adapter(MainActivity.this, searchData);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.e(">> ERROR", e.getMessage());
                        pgb_loading.setVisibility(View.INVISIBLE);
                    }
                    @Override
                    public void onNext(media value) {
                        // If valid data, add it to data list
                        if (value != null) {
                            searchData.add(value);
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!isOnline()) {
            Toast.makeText(MainActivity.this, getString(R.string.no_connection), Toast.LENGTH_LONG).show();
        }
    }

    private void setupToolbar() {
        final android.support.v7.app.ActionBar ab = getActionBarToolbar();
        ab.setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Add options menu on toolbar
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //Enable to open the activity to show the search history
            case R.id.action_search_history:
                //This is because it is possible to search for used terms again
                startActivityForResult(new Intent(MainActivity.this, HistoryActivity.class),1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //When an item from the list is selected, the activity search fot it again
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                input_search.setText(data.getStringExtra("search_word"));
                search();
            }
        }
    }


    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(MainActivity.this.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    // Save and restore data for screen rotation
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("input_search", input_search.getText().toString());
        savedInstanceState.putParcelableArrayList("search_data", searchData);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        input_search.setText(savedInstanceState.getString("input_search"));
    }
}
