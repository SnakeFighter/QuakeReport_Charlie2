/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    EarthquakeAdapter earthquakeAdapter;
    ListView listView;

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    //Query URL to send to the USGS.
    private static final String USGS_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=1&limit=20";

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        Log.i(LOG_TAG,"Get new loader / OnCreateLoader");
        return new EarthquakeLoader(this, USGS_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {

        //Clear the adapter of previous data
        //earthquakeAdapter.clear();

        Log.i(LOG_TAG,"onLoadFinished");

        earthquakeAdapter = new EarthquakeAdapter(getBaseContext(), earthquakes);
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        listView =  findViewById(R.id.list);
        listView.setAdapter(earthquakeAdapter);
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        Log.i(LOG_TAG,"onLoaderReset");
        listView.setAdapter(new EarthquakeAdapter(this,null));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        //Init loader here!
        Log.i(LOG_TAG,"Init loader");
        getLoaderManager().initLoader(0,null,this);

        //NetworkTask mNetworkTask = new NetworkTask();
        //mNetworkTask.execute(USGS_URL);
    }

    private class NetworkTask extends AsyncTask<String, Void, List<Earthquake>> {

        @Override
        protected List<Earthquake> doInBackground(String... strings) {
            //Create a URL from the input string
            ArrayList mEarthquakes;
            mEarthquakes = Utils.fetchEarthquakeData(strings[0]);
            return mEarthquakes;
        }

        @Override
        protected void onPostExecute(List<Earthquake> earthquakes) {
            EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(getBaseContext(), earthquakes);
            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            final ListView listView = findViewById(R.id.list);
            listView.setAdapter(earthquakeAdapter);

            //Set a listener to take the user to the earthquake URL if they click the list item.
            listView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Get the earthquake item that's been clicked.
                    Earthquake clickEarthquake = (Earthquake) listView.getItemAtPosition(position);
                    //Make an intent to open a browser:
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(clickEarthquake.getUrl()));
                    startActivity(intent);
                }
            });

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }
}
