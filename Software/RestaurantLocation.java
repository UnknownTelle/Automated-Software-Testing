package com;


import java.util.Arrays;

public class RestaurantLocation {
    public static Restaurant[] getLocation(Restaurant[] array, double lat, double lng, String neighbourhood) {
        Restaurant[] returnData = new Restaurant[0];
        for (int i = 0; i <= array.length - 1; i++) {
            if (array[i].neighbourhood.equals(neighbourhood) && (lat < array[i].lat + 0.06 && lat > array[i].lat - 0.06) && (lng < array[i].lng + 0.06 && lng > array[i].lng - 0.06)) {
                returnData = Arrays.copyOf(returnData, returnData.length + 1);
                returnData[returnData.length - 1] = array[i];
            }
        }
        return returnData;
    }

}
