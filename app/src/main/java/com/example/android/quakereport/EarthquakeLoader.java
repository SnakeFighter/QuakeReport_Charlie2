package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader {

    private String strings[];
    private final String LOG_TAG = "EQLoader";

    public EarthquakeLoader(Context context, String... strings) {
        super(context);
        this.strings = strings;
        //TODO:  Implement
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG,"onStartLoading");
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        //Create a URL from the input string
        ArrayList mEarthquakes;
        mEarthquakes = Utils.fetchEarthquakeData(strings[0]);
        Log.i(LOG_TAG,"loadInBackground2");
        return mEarthquakes;
    }
}
