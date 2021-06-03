package com.IntegrationTest;

import com.ReadJSON;
import com.Restaurant;
import com.RestaurantCuisine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantCuisineTest {
    Restaurant[] restaurants;
    @BeforeEach
    public void startUp() throws Exception {
        restaurants = ReadJSON.convertJson(ReadJSON.getAPI("restaurant-data.json"));
    }
    @Test
    public void testRestaurantCuisineAllTrue(){  // Correct Inputs
        Restaurant[] restaurantsWithAsianCuisineInManhattan = new Restaurant[2];
        restaurantsWithAsianCuisineInManhattan[0] = restaurants[0];
        restaurantsWithAsianCuisineInManhattan[1] = restaurants[2];
        restaurants = RestaurantCuisine.getRestaurantWithCuisineFromNeighbourhood(restaurants, "Asian", "Manhattan");
        assertArrayEquals(restaurantsWithAsianCuisineInManhattan, restaurants);
    }
    @Test
    public void testRestaurantCuisineFalseCuisine(){
        Restaurant[] restaurantsWithFalseCuisineInManhattan = new Restaurant[0];
        restaurants = RestaurantCuisine.getRestaurantWithCuisineFromNeighbourhood(restaurants, "False", "Manhattan");
        assertArrayEquals(restaurantsWithFalseCuisineInManhattan, restaurants);
    }
    @Test
    public void testRestaurantCuisineFalseNeighbourhood(){
        Restaurant[] restaurantsWithAsianCuisineWithFalseNeighbourhood = new Restaurant[0];
        restaurants = RestaurantCuisine.getRestaurantWithCuisineFromNeighbourhood(restaurants, "Asian", "False");
        assertArrayEquals(restaurantsWithAsianCuisineWithFalseNeighbourhood, restaurants);
    }
    @Test
    public void testRestaurantCuisineAllFalse(){
        Restaurant[] restaurantsWithFalseCuisineWithFalseNeighbourhood = new Restaurant[0];
        restaurants = RestaurantCuisine.getRestaurantWithCuisineFromNeighbourhood(restaurants, "False", "False");
        assertArrayEquals(restaurantsWithFalseCuisineWithFalseNeighbourhood, restaurants);
    }
}
