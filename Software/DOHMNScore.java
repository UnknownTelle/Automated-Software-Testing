package com;

import java.util.Arrays;

public class DOHMNScore {
    public static Restaurant[] OrdedDOHMNScore(Restaurant[] array, String neighbourhood) {
        Restaurant[] returnData = new Restaurant[0];
        int bestDOHMHScore = 0;
        for (int i = 0; i <= array.length - 1; i++) {
            if (array[i].DOHMHInspectionScore > bestDOHMHScore) {
                bestDOHMHScore = array[i].DOHMHInspectionScore;
            }
        }
        while (bestDOHMHScore >= 0) {
            for (int i = 0; i <= array.length - 1; i++) {
                if (array[i].DOHMHInspectionScore == bestDOHMHScore && array[i].neighbourhood.equals(neighbourhood)) {
                    returnData = Arrays.copyOf(returnData, returnData.length + 1);
                    returnData[returnData.length - 1] = array[i];
                }
            }
            bestDOHMHScore -= 1;
        }
        return returnData;
    }
}
