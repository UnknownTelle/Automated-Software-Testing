package com;

public class Restaurant {
    int id;
    String name;
    int DOHMHInspectionScore;
    String neighbourhood;
    String photograph;
    String address;
    float lat;
    float lng;
    String cuisine;

    String[] openHours;
    Reviews[] reviews;

    public Restaurant(int id, String name, int DOHMHInspectionScore, String neighbourhood,
                      String photograph, String address,
                      float lat, float lng, String cuisine, String[] getHours) {

        this.id = id;
        this.name = name;
        this.DOHMHInspectionScore = DOHMHInspectionScore;
        this.neighbourhood = neighbourhood;
        this.photograph = photograph;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.cuisine = cuisine;
        this.openHours = getHours;
    }
}
