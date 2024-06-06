package com.zevzo.weatherapp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

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

            // check response status
            if (connection.getResponseCode() != 200) {
                System.out.println("Error: Could not connect to API");
                return null;
            } else {
                // store the API results
                StringBuilder resultJson = new StringBuilder();
                Scanner scanner = new Scanner(connection.getInputStream());

                // read and store the resulting json data into out string builder
                while (scanner.hasNext()) {
                    resultJson.append(scanner.nextLine());
                }

                // close scanner
                scanner.close();

                // close url connection
                connection.disconnect();

                // parse the JSON string into a JSON object
                JSONParser parser = new JSONParser();
                JSONObject resultsJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));

                // get the list of location data the API generated from the location name
            }

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










