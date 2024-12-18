package com.example.currencyconversion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Currency {



    public double getRate(String fromCurrency, String toCurrency) throws IOException {
        String API_KEY = "75497f77e3a73845093ee164";  // Your API key
        var API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + fromCurrency;
        URL url = new URL(API_URL);
        String exRates = getExchangeRate(url);

        BufferedReader br = new BufferedReader(new StringReader(exRates));
        String line;


        while((line = br.readLine()) != null){
            if (line.contains(toCurrency)) {

                break;
            }

        }


        double rate = extractNumberString(line);
        br.close();
        return rate;

    }


    public String getDate() throws IOException {
        String API_KEY = "75497f77e3a73845093ee164";  // Your API key
        String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";
        URL url = new URL(API_URL);
        String response = getExchangeRate(url);

        BufferedReader br = new BufferedReader(new StringReader(response));
        String line;

        while((line = br.readLine()) != null){
            if (line.contains("time_last_update_utc")) {

                break;
            }
        }
        line = line.replaceAll("\"", "");
        int colonIndex = line.indexOf(':');

        // Extract the substring after the colon and trim quotes and spaces
        String date = line.substring(colonIndex + 1).trim();
        date =  date.substring(0, date.length() - 1);
        return date;
    }



    private static String getExchangeRate(URL url){
        StringBuilder content = new StringBuilder();
        try {

            // open connection to API
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Set http request method to get data from API
            conn.setRequestMethod("GET");

            // read data from API Line by line
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));


            // Store all data in string builder
            String inputLine;

            while((inputLine = in.readLine()) != null){
                content.append(inputLine + "\n"); // append response to string builder
            }
            in.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return String.valueOf(content);
    }

    private static double extractNumberString(String input) {
        // Find the position of the colon
        int colonIndex = input.indexOf(':');

        // Extract the substring after the colon and trim quotes and spaces
        String numberPart = input.substring(colonIndex + 1).trim();

        // Remove any surrounding quotes if present
        if (numberPart.startsWith("\"") && numberPart.endsWith("\"")) {
            numberPart = numberPart.substring(1, numberPart.length() - 1);
        }

        // remove any other non integer value
        numberPart = numberPart.replaceAll("[^0-9.]", "");

        return Double.parseDouble(numberPart);
    }


}







