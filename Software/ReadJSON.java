package com;

import org.json.JSONArray;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ReadJSON {
    public static String getAPI(String URLEnd) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL("http://intelligent-social-robots-ws.com/" + URLEnd).openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            String response = "";
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                response += scanner.nextLine();
                response += "\n";
            }
            scanner.close();
            return response;
        }
        return null;
    }

    // loops through the file to get and puts each piece of data into its relevant parts
    public static Restaurant[] convertJson(String input) {
        JSONArray ja = new JSONArray("[" + input + "]");
        Restaurant[] dataArray = new Restaurant[ja.getJSONObject(0).getJSONArray("restaurants").length()];

        for (int i = 0; i <= ja.getJSONObject(0).getJSONArray("restaurants").length() - 1; i++) {

            // converts the DOHMH Score to an int
            int DOHMH = 0;
            if (ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).get("DOHMH_inspection_score").toString() != "") {
                DOHMH = Integer.parseInt(ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).get("DOHMH_inspection_score").toString());
            }
            // Reads in each day and puts into an array to get the times.
            String[] Hours = new String[7];
            Hours[0] = ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONObject("operating_hours").get("Monday").toString();
            Hours[1] = ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONObject("operating_hours").get("Tuesday").toString();
            Hours[2] = ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONObject("operating_hours").get("Wednesday").toString();
            Hours[3] = ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONObject("operating_hours").get("Thursday").toString();
            Hours[4] = ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONObject("operating_hours").get("Friday").toString();
            Hours[5] = ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONObject("operating_hours").get("Saturday").toString();
            Hours[6] = ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONObject("operating_hours").get("Sunday").toString();

            Double[] LatLng = new Double[2];
            LatLng[0] = Double.parseDouble(ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONObject("latlng").get("lat").toString());
            LatLng[1] = Double.parseDouble(ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONObject("latlng").get("lng").toString());

            double distance = 0;

            // loops through the restaurants on the API
            dataArray[i] = new Restaurant(Integer.parseInt(
                    ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).get("id").toString()),
                    ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).get("name").toString(),
                    DOHMH,
                    ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).get("neighborhood").toString(),
                    ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).get("photograph").toString(),
                    ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).get("address").toString(),
                    ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).get("cuisine_type").toString(),
                    Hours,
                    LatLng,
                    distance);

            // loops through the reviews on the API
            dataArray[i].reviews = new Reviews[(ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(0).getJSONArray("reviews").length())];
            for (int j = 0; j <= ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(0).getJSONArray("reviews").length() - 1; j++) {
                dataArray[i].reviews[j] = new Reviews(
                        ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONArray("reviews").getJSONObject(j).get("name").toString(),
                        ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONArray("reviews").getJSONObject(j).get("date").toString(),
                        ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONArray("reviews").getJSONObject(j).get("rating").toString(),
                        ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONArray("reviews").getJSONObject(j).get("comments").toString());
            }
        }
        return dataArray;
    }
}
