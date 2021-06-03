package com;
import java.util.Arrays;
import java.util.*;

public class RestaurantLocation {
    public static Restaurant[] getDistance(Restaurant[] array, double hLat, double hLng, String neighbourhood) {
        Restaurant[] returnData = new Restaurant[0];
        double rLat;
        double rLng;
        double distance;
        for (int i = 0; i < array.length - 1; i++){
            rLat = array[i].LatLng[0];
            rLng = array[i].LatLng[1];
            distance = Math.sqrt((hLat - rLat) * (hLat - rLat) + (hLng - rLng) * (hLng - rLng));
            array[i].distance = distance;
            if (array[i].neighbourhood.equals(neighbourhood)){
                returnData = Arrays.copyOf(returnData, returnData.length + 1);
                returnData[returnData.length - 1] = array[i];
            }
        }
        Arrays.sort(returnData, Comparator.comparing(Restaurant::distance));
        return returnData;
    }

}
