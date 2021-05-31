package com;

import java.util.Arrays;

public class RestaurantRating {
    public static Restaurant[] AverageRatingsList(Restaurant[] array, String neighbourhood, int rating) {
        Restaurant[] returnData = new Restaurant[0];
        for (int i = 0; i <= array.length - 1; i++) {
            float avgRating = 0;
            for (int j = 0; j <= array[i].reviews.length - 1; j++) {
                avgRating += Integer.parseInt(array[i].reviews[j].reviewRating);
            }
            avgRating = avgRating / array[i].reviews.length;
            if (array[i].neighbourhood.equals(neighbourhood) && avgRating >= rating) {
                returnData = Arrays.copyOf(returnData, returnData.length + 1);
                returnData[returnData.length - 1] = array[i];
            }
        }
        return returnData;
    }
}
