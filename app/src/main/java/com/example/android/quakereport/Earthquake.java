package com.example.android.quakereport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by charl on 31/03/2018.
 */

public class Earthquake {
    private double mMag;
    private String mLocation; //eg 85KM W of San Fransisco
    private Date mDate;
    private long mTimeInMilliseconds;
    String mDateToDisplay;
    String mTimeToDisplay;
    private String mUrl;

    /**
     * Constructs a new earthquake object.
     *
     * @param mag  magnitude
     * @param location  description of location
     * @param timeInMilliseconds  Unix time ms of event.
     */
    public Earthquake(double mag, String location, long timeInMilliseconds, String url) {
        this.mMag = mag;
        this.mLocation = location;
        this.mTimeInMilliseconds = timeInMilliseconds;
        this.mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }

    public double getMag() {
        return mMag;
    }

    public long getmTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public void setMag(double mMag) {
        this.mMag = mMag;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        this.mLocation = location;
    }

    public Date getDate() {
        return  mDate;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    public String getDateToDisplay() {

        //Extract the date
        Date dateObject = new Date (mTimeInMilliseconds);
        this.mDate = dateObject;

        //Format the date...
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy");
        mDateToDisplay = dateFormatter.format(dateObject);

        return mDateToDisplay;
    }

    public String getTimeToDisplay() {

        //Extract the date
        Date dateObject = new Date (mTimeInMilliseconds);
        this.mDate = dateObject;
        //...and the time
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm");
        mTimeToDisplay = dateFormatter.format(mDate);
        return mTimeToDisplay;
    }
}
