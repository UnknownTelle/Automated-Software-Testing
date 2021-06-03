package com.IntegrationTest;

import com.ReadJSON;
import com.Restaurant;
import com.RestaurantLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantLocationTest {
    Restaurant[] restaurants;

    @BeforeEach
    public void startUp() throws Exception {
        restaurants = ReadJSON.convertJson(ReadJSON.getAPI("restaurant-data.json"));
    }

    @Test
    public void testRestaurantLocationAllTrue() {
        Restaurant[] restaurantsLocationInManhattan = new Restaurant[5];
        restaurantsLocationInManhattan[0] = restaurants[2];
        restaurantsLocationInManhattan[1] = restaurants[6];
        restaurantsLocationInManhattan[2] = restaurants[3];
        restaurantsLocationInManhattan[3] = restaurants[7];
        restaurantsLocationInManhattan[4] = restaurants[0];
        restaurants = RestaurantLocation.getDistance(restaurants, 40.752831, -73.985748, "Manhattan");
        assertArrayEquals(restaurantsLocationInManhattan, restaurants);
    }

    @Test
    public void testRestaurantLocationFalseNeighbourhood() {
        Restaurant[] manhattanHotelLatLngButFalseNeighbourhood = new Restaurant[0];
        restaurants = RestaurantLocation.getDistance(restaurants, 40.752831, -73.985748, "False");
        assertArrayEquals(manhattanHotelLatLngButFalseNeighbourhood, restaurants);
    }
}
