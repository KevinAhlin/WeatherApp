package com.zevzo.weatherapp;

import javax.swing.*;

public class AppLauncher {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // display our weather app gui
                new WeatherAppGui().setVisible(true);

                // pass the name of a city for debugging
                //System.out.println(WeatherApp.getLocationData("Tokyo"));

                // test 'getCurrentTime' method for debugging
                //System.out.println(WeatherApp.getCurrentTime());
            }
        });
    }
}
