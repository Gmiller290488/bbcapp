package com.example.gmill.bbcapptest;

/**
 * Created by gmill on 12/02/2018.
 */
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

class AppLoader extends AsyncTaskLoader<List<App>> {
    private static final String LOG_TAG = HttpRequest.class.getSimpleName();

    final private String mUrl;

    AppLoader(Context context, String url) {
        super(context);

        mUrl = url;
        Log.e(LOG_TAG, mUrl);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<App> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        return HttpRequest.fetchAppData(mUrl);
    }
}
