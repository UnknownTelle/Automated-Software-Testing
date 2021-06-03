package com.IntegrationTest;
import com.DOHMNScore;
import com.ReadJSON;
import com.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DOHMNScoreTest {
    Restaurant[] restaurants;
    @BeforeEach
    public void startUp() throws Exception {
        restaurants = ReadJSON.convertJson(ReadJSON.getAPI("restaurant-data.json"));
    }
    @Test
    public void testDOHMNScoreTrueNeighbourhood(){
        Restaurant[] restaurantsWithAllDOHMNScoreInManhattan = new Restaurant[5];
        restaurantsWithAllDOHMNScoreInManhattan[0] = restaurants[3];
        restaurantsWithAllDOHMNScoreInManhattan[1] = restaurants[0];
        restaurantsWithAllDOHMNScoreInManhattan[2] = restaurants[7];
        restaurantsWithAllDOHMNScoreInManhattan[3] = restaurants[6];
        restaurantsWithAllDOHMNScoreInManhattan[4] = restaurants[2];
        restaurants = DOHMNScore.OrderDOMEScore(restaurants, "Manhattan");
        assertArrayEquals(restaurantsWithAllDOHMNScoreInManhattan, restaurants);
    }
    @Test
    public void testDOHMNScoreFalseNeighbourhood(){
        Restaurant[] restaurantsWithFalseNeighbourhood = new Restaurant[0];
        restaurants = DOHMNScore.OrderDOMEScore(restaurants, "False");
        assertArrayEquals(restaurantsWithFalseNeighbourhood, restaurants);
    }
}
