package com.example.gmill.bbcapptest;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gmill on 12/02/2018.
 */

public class AppActivity  extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<App>> {
    private static final String LOG_TAG = HttpRequest.class.getSimpleName();

    /**
     * the request url
     */
    private static final String REQUEST_URL = "https://itunes.apple.com/search?media=software&entity=software&country=gb&term=";

    private static final int APP_LOADER_ID = 1;

    private AppAdapter mAdapter;

    /**
     * the text view for empty resultset or no internet
     */
    private TextView mNoResultsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        ListView appListView = (ListView) findViewById(R.id.list);

        mNoResultsTextView = (TextView) findViewById(R.id.empty_view);
        appListView.setEmptyView(mNoResultsTextView);

        mAdapter = new AppAdapter(this, new ArrayList<App>());

        appListView.setAdapter(mAdapter);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(APP_LOADER_ID, null, this);
        } else {

            View loadingIndicator = findViewById(R.id.progress_bar);
            loadingIndicator.setVisibility(View.GONE);

            mNoResultsTextView.setText("No internet connection found");
        }


        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected article.
        appListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                // Find the current article that was clicked on
                App currentApp = mAdapter.getItem(position);
                Intent intent = new Intent(AppActivity.this, AppDetailActivity.class);
                intent.putExtra("app", currentApp);
                startActivity(intent);
            }


        });
    }


    @Override
    public Loader<List<App>> onCreateLoader(int i, Bundle bundle) {
        Intent intent = getIntent();
        String searchTerm = intent.getStringExtra(MainActivity.SEARCH_TERM);
        String url = REQUEST_URL + searchTerm;
        Log.e(LOG_TAG, searchTerm);


        return new AppLoader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<List<App>> loader, List<App> apps) {
        View loadingIndicator = findViewById(R.id.progress_bar);
        loadingIndicator.setVisibility(View.GONE);

        mNoResultsTextView.setText("No apps found");

        mAdapter.clear();

        if (apps != null && !apps.isEmpty()) {
            mAdapter.addAll(apps);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<App>> loader) {
        mAdapter.clear();
    }
}