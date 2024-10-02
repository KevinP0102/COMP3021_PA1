package hk.ust.comp3021;

import java.lang.Math;

public class Location {
    private Double latitude;
    private Double altitude;

    public Location(Double latitude, Double altitude) {
        this.latitude = latitude;
        this.altitude = altitude;
    }

    public Double distanceTo(Location location) {
        return Math.sqrt(Math.pow(this.latitude - location.latitude, 2) + Math.pow(this.altitude - location.altitude, 2));
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    @Override
    public String toString() {
        return "Location(" +
                "latitude=" + latitude +
                ", altitude=" + altitude +
                ')';
    }
}
