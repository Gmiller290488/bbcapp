package com.example.gmill.bbcapptest;

/**
 * Created by gmill on 12/02/2018.
 */

import android.text.TextUtils;
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
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

final class HttpRequest {

    private static final String LOG_TAG = HttpRequest.class.getSimpleName();

    private HttpRequest() {
        // intentionally blank - shouldn't be called!
    }

    static List<App> fetchAppData(String requestUrl) {
        URL url = createUrl(requestUrl);
        //URL url = createUrl("https://itunes.apple.com/search?media=software&entity=software&country=gb&term=bbc");
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        return extractFeatureFromJson(jsonResponse);
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
            Log.e(LOG_TAG, "URL should be \"https://itunes.apple.com/search?media=software&entity=software&country=gb&term=bbc\"");
            Log.e(LOG_TAG, "Url is" + url);

        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection connection = null;
        InputStream inputStream = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000 /* milliseconds */);
            connection.setConnectTimeout(15000 /* milliseconds */);
            connection.setRequestMethod("GET");
            connection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + connection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the app JSON results.", e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        Log.e(LOG_TAG, "Connection ok");
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();
        if (inputStream != null) {

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
            Log.e(LOG_TAG, output.toString());
        }

        return output.toString();
    }

    private static List<App> extractFeatureFromJson(String appJSON) {
        if (TextUtils.isEmpty(appJSON)) {
            Log.e(LOG_TAG, "json is empty");
            return null;
        }

        List<App> apps = new ArrayList<>();
        try {

            JSONObject baseJsonResponse = new JSONObject(appJSON);

            JSONArray appArray = baseJsonResponse.getJSONArray("results");
            Log.e(LOG_TAG, appArray.toString());

            for (int i = 0; i < appArray.length(); i++) {
                String scrnshotUrls = "";

                JSONObject currentApp = appArray.getJSONObject(i);


                String title = currentApp.getString("trackName");
                String description = currentApp.getString("description");
                String rating = currentApp.getString("averageUserRating");
                String price = currentApp.getString("formattedPrice");
                String name = currentApp.getString("sellerName");


                String smallImageUrl = currentApp.getString("artworkUrl60");
                String largeImageUrl = currentApp.getString("artworkUrl512");

                App app = new App(title, description, rating, price, smallImageUrl, largeImageUrl, name);

                apps.add(app);
            }

        } catch (JSONException e) {
            Log.e("HttpRequest", "Problem parsing the app JSON results", e);
        }

        return apps;
    }

}