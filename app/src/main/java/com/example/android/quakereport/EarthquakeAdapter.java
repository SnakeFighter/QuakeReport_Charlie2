package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by charl on 01/04/2018.
 */

public class EarthquakeAdapter extends ArrayAdapter {
    public EarthquakeAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Check if the exisitng view is being reused, otherwise inflate the view.
        View earthquakeItemView = convertView;
        if (earthquakeItemView == null) {
            earthquakeItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        //Get the earthquake item in the list.
        Earthquake currentEarthquake = (Earthquake) getItem(position);

        //Find the textview in the item layout and set to the magnitude.
        TextView magView = earthquakeItemView.findViewById(R.id.magText);

        //Ensure the magnitude is formatted correctly
        DecimalFormat formatter = new DecimalFormat("0.0");
        String outputMag = formatter.format(currentEarthquake.getMag());

        magView.setText(outputMag);

        //Split the location text in two.
        String locationText = currentEarthquake.getLocation();

        String locationArray[] = locationText.split(" of ", 2);

        String primaryLocation;
        String locationOffset;

        //If there's a primary location and an offset, split the string up:
        if (locationArray.length > 1) {
            primaryLocation = locationArray[1];
            locationOffset = locationArray[0] + " of";
        } else {
            //Otherwise, adjust it to say "Near the...":
            locationOffset = "Near the";
            primaryLocation = locationText;
        }

        //Set both location strings in the list view
        TextView offsetView = earthquakeItemView.findViewById(R.id.locationOffsetText);
        offsetView.setText(locationOffset);

        TextView locationView = earthquakeItemView.findViewById(R.id.locationText);
        locationView.setText(primaryLocation);

        //And the date:
        TextView dateView = earthquakeItemView.findViewById(R.id.dateText);
        dateView.setText(currentEarthquake.getDateToDisplay());

        //And time:
        TextView timeView = earthquakeItemView.findViewById(R.id.timeText);
        timeView.setText(currentEarthquake.getTimeToDisplay());

        //Set the correct colour background circle for the magnitude.
        //Fetch the background from the textView, which is GradientDrawable
        GradientDrawable magnitudeCircle = (GradientDrawable) magView.getBackground();

        //Get amd set the background color based on current magnitude.
        int magColor = getMagnitudeColor(currentEarthquake.getMag());
        magnitudeCircle.setColor(magColor);

        return earthquakeItemView;
    }

    private int getMagnitudeColor(double magntitude) {
        //TODO implement colour chooser for mag circle.
        int outputColor;
        int magInt = (int) magntitude;
        Context context = getContext();

        Log.i("magnitude", "" + (int) magntitude);

        //Set the colour according to the intensity.

        switch (magInt) {
            case 0:
                outputColor = ContextCompat.getColor(context, R.color.magnitude1);
                break;
            case 1:
                outputColor = ContextCompat.getColor(context, R.color.magnitude1);
                break;
            case 2:
                outputColor = ContextCompat.getColor(context, R.color.magnitude2);
                break;
            case 3:
                outputColor = ContextCompat.getColor(context, R.color.magnitude3);
                break;
            case 4:
                outputColor = ContextCompat.getColor(context, R.color.magnitude4);
                break;
            case 5:
                outputColor = ContextCompat.getColor(context, R.color.magnitude5);
                break;
            case 6:
                outputColor = ContextCompat.getColor(context, R.color.magnitude6);
                break;
            case 7:
                outputColor = ContextCompat.getColor(context, R.color.magnitude7);
                break;
            case 8:
                outputColor = ContextCompat.getColor(context, R.color.magnitude8);
                break;
            case 9:
                outputColor = ContextCompat.getColor(context, R.color.magnitude9);
                break;
            default:
                outputColor = ContextCompat.getColor(context, R.color.magnitude10plus);
                break;
        }

        return outputColor;
    }

}
