package com;

public class restaurant {
    int getId;
    String getName;
    int getDOHMHInspectionScore;
    String getNeighbourhood;
    String getPhotograph;
    String getAddress;
    float getLat;
    float getLng;
    String getCuisine;

    String[] getHours;
    reviews[] getReviews;

    public restaurant(int getId, String getName, int getDOHMHInspectionScore, String getNeighbourhood,
                      String getPhotograph, String getAddress,
                      float getLat, float getLng, String getCuisine, String[] getHours) {

        this.getId = getId;
        this.getName = getName;
        this.getDOHMHInspectionScore = getDOHMHInspectionScore;
        this.getNeighbourhood = getNeighbourhood;
        this.getPhotograph = getPhotograph;
        this.getAddress = getAddress;
        this.getLat = getLat;
        this.getLng = getLng;
        this.getCuisine = getCuisine;
        this.getHours = getHours;
    }
}
