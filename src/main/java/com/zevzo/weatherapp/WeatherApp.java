package com.zevzo.weatherapp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

// retrieve weather data from API - this backend logic will fetch the latest weather data
// from the external API and return it. The GUI will display this data to the user
public class WeatherApp {
    // fetch weather data for the given location
    public static JSONObject getWeatherData(String locationName) {
        // get location coordinates using the geolocation API (long and lat)
        JSONArray locationData = getLocationData(locationName);
    }

    // creates another API call where it will take in an entered
    // location, and return the latitude and longitude data
    private static JSONArray getLocationData(String locationName) {
        // replace any whitespace in location name with "+" to adhere to the APIs request format
        locationName = locationName.replaceAll(" ", "+");

        // build API url with location parameter
        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name=" +
                locationName + "&count=10&language=en&format=json";

        try {
            // call api and get a response
            HttpURLConnection connection = fetchApiResponse(urlString);

        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    private static HttpURLConnection fetchApiResponse(String urlString) {
        try {
            // attempt to create connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // set request method to "get"
            conn.setRequestMethod("GET");

            // connect to our API
            conn.connect();
            return conn;
        } catch (IOException error) {
            error.printStackTrace();
        }

        // could not make connection
        return null;
    }


}










