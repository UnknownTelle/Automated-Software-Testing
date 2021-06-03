package com.UnitTest;

import com.Mock.ReadJSONMock;
import com.Restaurant;
import com.RestaurantRating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RestaurantRatingTest {
    Restaurant[] restaurants;
    @BeforeEach
    public void startUp() throws Exception {
        restaurants = ReadJSONMock.convertJson(ReadJSONMock.getAPI());
    }
    @Test
    public void testRestaurantRatingAllTrue() {
        Restaurant[] restaurantsWithAvgScore4InManhattan = new Restaurant[3];
        restaurantsWithAvgScore4InManhattan[0] = restaurants[3];
        restaurantsWithAvgScore4InManhattan[1] = restaurants[6];
        restaurantsWithAvgScore4InManhattan[2] = restaurants[7];
        restaurants = RestaurantRating.AverageRatingsList(restaurants, "Manhattan", 4);
        assertArrayEquals(restaurantsWithAvgScore4InManhattan, restaurants);
    }
    @Test
    public void testRestaurantRatingFalseNeighbourhood(){
        Restaurant[] restaurantsWithFalseNeighbourhood = new Restaurant[0];
        restaurants = RestaurantRating.AverageRatingsList(restaurants, "False", 4);
        assertArrayEquals(restaurantsWithFalseNeighbourhood, restaurants);
    }
}
