package com.IntegrationTest;
import com.ReadJSON;
import com.Restaurant;
import com.RestaurantLocation;
import com.RestaurantRating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantRatingTest {
    Restaurant[] restaurants;

    @BeforeEach
    public void startUp() throws Exception {
        restaurants = ReadJSON.convertJson(ReadJSON.getAPI("restaurant-data.json"));
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
