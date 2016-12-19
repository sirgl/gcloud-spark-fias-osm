package ru.nsu.rivanov.fias_osm;

/**
 * Created by roman on 18.12.16.
 */
public class OsmHouse {
    public static final String HOUSE_NUMBER = "HouseNumber";
    public static final String STREET = "Street";
    public static final String CITY = "City";
    public static final String LAT = "Lat";
    public static final String LON = "Lon";

    private String houseNumber;
    private String street;
    private String city;
    private double lat;
    private double lon;

    public OsmHouse() {
    }

    public static String getHouseNumber() {
        return HOUSE_NUMBER;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public static String getSTREET() {
        return STREET;
    }

    public static String getCITY() {
        return CITY;
    }

    public static String getLAT() {
        return LAT;
    }

    public static String getLON() {
        return LON;
    }

    public OsmHouse(String houseNumber, String street, String city, double lat, double lon) {

        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.lat = lat;
        this.lon = lon;
    }
}
