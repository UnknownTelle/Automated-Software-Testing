package com;

import java.util.Arrays;

public class RestaurantCuisine {
    public static Restaurant[] getRestaurantWithCuisineFromNeighbourhood(Restaurant[] array, String cuisine, String neighbourhood) {
        Restaurant[] returnData = new Restaurant[0];
        for (int i = 0; i <= array.length - 1; i++) {
            if (array[i].neighbourhood.equals(neighbourhood) && array[i].cuisine.equals(cuisine)) {
                returnData = Arrays.copyOf(returnData, returnData.length + 1);
                returnData[returnData.length - 1] = array[i];
            }
        }
        return returnData;
    }
}
