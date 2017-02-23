package com.example.android.quakereport;

/**
 * Created by apoorva on 2/7/17.
 */

/**
 * Class which holds all the information about an earthquake.
 */
public class Earthquake {
    private double mMagnitude;
    private String mPlace;
    private long mTimeinMilliseconds;
    private String mUrl;

    public Earthquake(double magnitude, String place, long timeInMilliseconds, String url){
        mMagnitude = magnitude;
        mPlace = place;
        mTimeinMilliseconds = timeInMilliseconds;
        mUrl = url;
    };

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getPlace() {
        return mPlace;
    }

    public long getTimeinMilliseconds() {
        return mTimeinMilliseconds;
    }

    public String getUrl() {
        return mUrl;
    }
}
