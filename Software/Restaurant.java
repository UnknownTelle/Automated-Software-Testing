package com;

public class Restaurant {
    public double distance;
    int id;
    String name;
    int DOHMHInspectionScore;
    String neighbourhood;
    String photograph;
    String address;
    String cuisine;

    String[] openHours;
    Double[] LatLng;
    public Reviews[] reviews;

    public Restaurant(int id, String name, int DOHMHInspectionScore, String neighbourhood,
                      String photograph, String address, String cuisine, String[] Hours, Double[] LatLng, double distance) {

        this.id = id;
        this.name = name;
        this.DOHMHInspectionScore = DOHMHInspectionScore;
        this.neighbourhood = neighbourhood;
        this.photograph = photograph;
        this.address = address;
        this.cuisine = cuisine;
        this.openHours = Hours;
        this.LatLng = LatLng;
        this.distance = distance;
    }

    public double distance() {
        return distance;
    }
}
