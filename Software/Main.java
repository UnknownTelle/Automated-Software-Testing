package com;

import org.json.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Date;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String ReadJSON = getAPI("restaurant-data.json");
        restaurant[] dataArray = convertJson(ReadJSON);
    }

    // Gets the API and reads the data
    private static String getAPI(String URLEnd) throws IOException {
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
    private static restaurant[] convertJson(String input) {
        JSONArray ja = new JSONArray("[" + input + "]");
        restaurant[] dataArray = new restaurant[ja.getJSONObject(0).getJSONArray("restaurants").length()];

        for (int i = 0; i <= ja.getJSONObject(0).getJSONArray("restaurants").length() - 1; i++) {

            // converts the DOHMH Score to an int
            int DOHMH = 0;
            if (ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).get("DOHMH_inspection_score").toString() != "") {
                DOHMH = Integer.parseInt(ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).get("DOHMH_inspection_score").toString());
            }
            // Reads in each day and puts into an array to get the times.
            String[] getHours = new String[7];
            getHours[0] = ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONObject("operating_hours").get("Monday").toString();
            getHours[1] = ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONObject("operating_hours").get("Tuesday").toString();
            getHours[2] = ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONObject("operating_hours").get("Wednesday").toString();
            getHours[3] = ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONObject("operating_hours").get("Thursday").toString();
            getHours[4] = ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONObject("operating_hours").get("Friday").toString();
            getHours[5] = ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONObject("operating_hours").get("Saturday").toString();
            getHours[6] = ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONObject("operating_hours").get("Sunday").toString();

            // loops through the restaurants on the API
            dataArray[i] = new restaurant(Integer.parseInt(
                    ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).get("id").toString()),
                    ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).get("name").toString(),
                    DOHMH,
                    ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).get("neighborhood").toString(),
                    ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).get("photograph").toString(),
                    ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).get("address").toString(),
                    Float.parseFloat(ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONObject("latlng").get("lat").toString()),
                    Float.parseFloat(ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONObject("latlng").get("lng").toString()),
                    ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).get("cuisine_type").toString(),
                    getHours);

            // loops through the reviews on the API
            dataArray[i].getReviews = new reviews[(ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(0).getJSONArray("reviews").length())];
            for (int j = 0; j <= ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(0).getJSONArray("reviews").length() - 1; j++) {
                dataArray[i].getReviews[j] = new reviews(
                        ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONArray("reviews").getJSONObject(j).get("name").toString(),
                        ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONArray("reviews").getJSONObject(j).get("date").toString(),
                        ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONArray("reviews").getJSONObject(j).get("rating").toString(),
                        ja.getJSONObject(0).getJSONArray("restaurants").getJSONObject(i).getJSONArray("reviews").getJSONObject(j).get("comments").toString());
            }
        }
        return dataArray;
    }
    // Splits the days opening times for the method getTime. Uses two open and close time for when the restaurant has two open and closing times.
    public static boolean getOpenTimes(String time, String hoursOpen){
        String openTime = "";
        String closeTime = "";
        String openTime2 = "";
        String closeTime2 = "";
        // Splits the times into groups starting with openTime (being the first open time) all the way to closeTime2 (being the second closing time.
        String regex = "((?<openTime>(1[0-2]|0?[1-9]):([0-5][0-9]) ?([AaPp][Mm])) - (?<closeTime>(1[0-2]|0?[1-9]):([0-5][0-9]) ?([AaPp][Mm])){1}(, (?<openTime2>(1[0-2]|0?[1-9]):([0-5][0-9]) ?([AaPp][Mm])) - (?<closeTime2>(1[0-2]|0?[1-9]):([0-5][0-9]) ?([AaPp][Mm])){1})?)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(hoursOpen);
        if (matcher.matches()){
            openTime = matcher.group("openTime");
            closeTime = matcher.group("closeTime");
            if (matcher.matches()){
                openTime2 = matcher.group("openTime2");
                closeTime2 = matcher.group("closeTime2");
            }
        }
        if (!openTime.equals("") && !closeTime.equals("")){
            Date open = new Time(0);
            Date close = new Time(0);
            Date newTime = new Time(0);
            try{
                open = new SimpleDateFormat("h:mm a").parse(openTime);
                close = new SimpleDateFormat("h:mm a").parse(closeTime);
                newTime = new SimpleDateFormat("h:mm a").parse(time);
            } catch (Exception e){ }

            if (open.compareTo(newTime) <= 0 && close.compareTo(newTime) == 1){
                return true;
            } else {
                if (openTime2 !="" && closeTime2 !=""){
                    Date open2 = new Time(0);
                    Date close2 = new Time(0);
                    try{
                        open2 = new SimpleDateFormat("h:mm a").parse(openTime2);
                        close2 = new SimpleDateFormat("h:mm a").parse(closeTime2);
                    } catch (Exception e){ }
                    if (open2.compareTo(newTime) <= 0 && close2.compareTo(newTime) == 1){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // Filters out the restaurants that match the cuisine and neighbourhood the user states
    private static restaurant[] getCuisine(restaurant[] array, String cuisine, String neighbourhood) {
        restaurant[] returnData = new restaurant[0];
        for (int i = 0; i <= array.length - 1; i++) {
            if (array[i].getNeighbourhood.equals(neighbourhood) && array[i].getCuisine.equals(cuisine)) {
                returnData = Arrays.copyOf(returnData, returnData.length + 1);
                returnData[returnData.length - 1] = array[i];
            }
        }
        return returnData;
    }

    // Filters out the restaurants that are open on a specific day and hour
    private static restaurant[] getTime(restaurant[] array, String time, int day){
        restaurant[] returnData = new restaurant[0];
        for (int i = 0; i <= array.length - 1; i++){
            if (getOpenTimes(time, array[i].getHours[day])){
                returnData = Arrays.copyOf(returnData, returnData.length + 1);
                returnData[returnData.length - 1] = array[i];
            }
        }
        return returnData;
    }

    // Filters out the restaurants that are under a specific ratting within a specific neighbourhood
    private static restaurant[] getAvgRating(restaurant[] array, String neighbourhood, int rating) {
        restaurant[] returnData = new restaurant[0];
        for (int i = 0; i <= array.length - 1; i++) {
            float avgRating = 0;
            for (int j = 0; j <= array[i].getReviews.length - 1; j++) {
                avgRating += Integer.parseInt(array[i].getReviews[j].getRating);
            }
            avgRating = avgRating / array[i].getReviews.length;
            if (array[i].getNeighbourhood.equals(neighbourhood) && avgRating >= rating) {
                returnData = Arrays.copyOf(returnData, returnData.length + 1);
                returnData[returnData.length - 1] = array[i];
            }
        }
        return returnData;
    }

    // Collects all restaurants from a specific neighbourhood and places them in descending order depending on the DOHMN inspection score
    private static restaurant[] getDOHMNList(restaurant[] array, String neighbourhood) {
        restaurant[] returnData = new restaurant[0];
        int bestDOHMHScore = 0;
        for (int i = 0; i <= array.length - 1; i++) {
            if (array[i].getDOHMHInspectionScore > bestDOHMHScore) {
                bestDOHMHScore = array[i].getDOHMHInspectionScore;
            }
        }
        while (bestDOHMHScore >= 0) {
            for (int i = 0; i <= array.length - 1; i++) {
                if (array[i].getDOHMHInspectionScore == bestDOHMHScore && array[i].getNeighbourhood.equals(neighbourhood)) {
                    returnData = Arrays.copyOf(returnData, returnData.length + 1);
                    returnData[returnData.length - 1] = array[i];
                }
            }
            bestDOHMHScore -= 1;
        }
        return returnData;
    }

    // Return list of restaurants in order of distance from a certain hotel within a specific neighbourhood
    private static restaurant[] getLocation(restaurant[] array, double lat, double lng, String neighbourhood) {
        restaurant[] returnData = new restaurant[0];
        for (int i = 0; i <= array.length - 1; i++) {
            if (array[i].getNeighbourhood.equals(neighbourhood) && (lat < array[i].getLat + 0.06 && lat > array[i].getLat - 0.06) && (lng < array[i].getLng + 0.06 && lng > array[i].getLng - 0.06)){
                returnData = Arrays.copyOf(returnData, returnData.length + 1);
                returnData[returnData.length - 1] = array[i];
            }
        }
        return returnData;
    }
}
