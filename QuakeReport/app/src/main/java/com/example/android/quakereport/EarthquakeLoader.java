package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by apoorva on 2/21/17.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private static String LOG_TAG = "EarthquakeLoader.java";
    private String mUrl;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.v(LOG_TAG, "In onStartLoading");
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        Log.v(LOG_TAG, "In loadInBackground");
        List<Earthquake> currentEarthquakes = QueryUtils.getEarthquakeDetails(mUrl);
        return currentEarthquakes;
    }
}
