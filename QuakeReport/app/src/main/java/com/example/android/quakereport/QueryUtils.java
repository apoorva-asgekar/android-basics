package com.example.android.quakereport;

/**
 * Created by apoorva on 2/9/17.
 */
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static android.R.id.input;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    private static String LOG_TAG = QueryUtils.class.getName();
    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /** Makes a HTTP Request to the USGS Server and returns the JSON response as a String.
     * @param requestUrl
     */
    private static String makeHttpRequest (String requestUrl) throws IOException {
        URL url;
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        String jsonResponse = null;
        try {
            url = new URL(requestUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Malformed URL Exception while trying to create a URL object.");
            return jsonResponse;
        }
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(10000);
            connection.connect();
            if (connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else{
                Log.e(LOG_TAG,
                        "USGS API returned a non OK response." + connection.getResponseCode());
            }


        } catch (IOException e) {
            Log.e(LOG_TAG, "IO Exception encountered in makeHttpRequest");
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) {
        StringBuilder output = new StringBuilder();
        if (inputStream!= null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            try {
                String line = bufferedReader.readLine();
                while (line != null) {
                    output.append(line);
                    line = bufferedReader.readLine();
                }
            } catch (IOException e) {
                Log.e(LOG_TAG, "IOException in readFromStream");
            }
        }
        return output.toString();
    }

    public static List<Earthquake>  getEarthquakeDetails(String requestUrl) {
        List<Earthquake> currentEarthquakeList = null;
        try {
            currentEarthquakeList = extractEarthquakes(makeHttpRequest(requestUrl));
        } catch (IOException e) {
            Log.e(LOG_TAG, "IOException in getEarthquakeDetails");
        }
        return currentEarthquakeList;
    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    private static List<Earthquake> extractEarthquakes(String jsonResponse) {

        // Create an empty ArrayList that we can start adding earthquakes to
        List<Earthquake> earthquakes = new ArrayList<>();

        if (jsonResponse == null || jsonResponse.isEmpty()) {
            return null;
        }

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // build up a list of Earthquake objects with the corresponding data.
            JSONObject reader = new JSONObject(jsonResponse);
            JSONArray features = reader.getJSONArray("features");

            for (int cnt = 0; cnt < features.length(); cnt++) {
                JSONObject oneFeature = features.getJSONObject(cnt);
                JSONObject properties = oneFeature.getJSONObject("properties");

                Double magnitude = properties.optDouble("mag");
                String place = properties.optString("place");
                Long date = properties.optLong("time");
                String url = properties.optString("url");
                earthquakes.add(new Earthquake(magnitude, place, date, url));
            }
        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

}