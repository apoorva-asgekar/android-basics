package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by apoorva on 2/7/17.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        final Earthquake currentEarthquake = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID magnitude_text_view
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude_text_view);
        // Get the magnitude from the current Earthquake object and
        // set this text on the magnitude TextView
        DecimalFormat formatter = new DecimalFormat("0.0");
        String magnitude = formatter.format(currentEarthquake.getMagnitude());

        magnitudeTextView.setText(magnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        String location = currentEarthquake.getPlace();
        // Find the TextView in the list_item.xml layout with the ID offset_text_view
        TextView offsetTextView = (TextView) listItemView.findViewById(R.id.offset_text_view);
        // Get the place from the current Earthquake object and
        // set this text on the place TextView
        String offsetLocation;
        if (location.indexOf(" of ") == -1) {
            offsetLocation = "Near the";
        } else {
            offsetLocation = (location.substring(0, (location.indexOf(" of ") + 4))).trim();
        }
            offsetTextView.setText(offsetLocation);

        // Find the TextView in the list_item.xml layout with the ID place_text_view
        TextView placeTextView = (TextView) listItemView.findViewById(R.id.place_text_view);
        // Get the place from the current Earthquake object and
        // set this text on the place TextView
        String primaryLocation;
        if (location.indexOf(" of ") == -1) {
            primaryLocation = location;
        } else {
            primaryLocation = location.substring(location.indexOf(" of ") + 4);
        }
        placeTextView.setText(primaryLocation);

        Date dateObject = new Date(currentEarthquake.getTimeinMilliseconds());

        // Find the TextView in the list_item.xml layout with the ID date_text_view
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_text_view);
        // Get the milliseconds from the current Earthquake object, convert it into the correct
        // format and set this text on the date TextView
        String formattedDate = formatDate(dateObject);
        dateTextView.setText(formattedDate);

        // Find the TextView in the list_item.xml layout with the ID time_text_view
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time_text_view);
        // Get the milliseconds from the current Earthquake object, convert it into the correct
        // format and set this text on the date TextView
        String formattedTime = formatTime(dateObject);
        timeTextView.setText(formattedTime);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the color to be set in the drawable circle from the magnitude.
     */
    private int getMagnitudeColor (Double magnitude) {
        int magnitudeColor;
        int floorMagnitude = (int) Math.floor(magnitude);
        switch (floorMagnitude) {
            case 0:
            case 1:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude1);
                break;
            case 2:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude2);
                break;
            case 3:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude3);
                break;
            case 4:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude4);
                break;
            case 5:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude5);
                break;
            case 6:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude6);
                break;
            case 7:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude7);
                break;
            case 8:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude8);
                break;
            case 9:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude9);
                break;
            default:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
        }
        return magnitudeColor;
    }
}
