package com.example.sae201;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class DonneesSeisme {
    private final SimpleDoubleProperty latitude;
    private final SimpleDoubleProperty longitude;
    private final SimpleDoubleProperty magnitude;
    private final SimpleStringProperty region;

    public DonneesSeisme(String region, double latitude, double longitude, double magnitude) {
        this.region = new SimpleStringProperty(region);
        this.latitude = new SimpleDoubleProperty(latitude);
        this.longitude = new SimpleDoubleProperty(longitude);
        this.magnitude = new SimpleDoubleProperty(magnitude);
    }

    public String getRegion() {
        return region.get();
    }

    public SimpleStringProperty regionProperty() {
        return region;
    }

    public double getLatitude() {
        return latitude.get();
    }

    public double getLongitude() {
        return longitude.get();
    }

    public double getMagnitude() {
        return magnitude.get();
    }

}