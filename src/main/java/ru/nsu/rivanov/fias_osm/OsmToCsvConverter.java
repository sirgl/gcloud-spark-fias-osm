package ru.nsu.rivanov.fias_osm;

import au.com.bytecode.opencsv.CSVWriter;
import net.morbz.osmonaut.EntityFilter;
import net.morbz.osmonaut.IOsmonautReceiver;
import net.morbz.osmonaut.Osmonaut;
import net.morbz.osmonaut.osm.*;

import java.io.FileWriter;
import java.io.IOException;

import static ru.nsu.rivanov.fias_osm.OsmHouse.*;

public class OsmToCsvConverter {


    public static void main(String[] args) {
        EntityFilter entityFilter = new EntityFilter(true, true, false);


        try (CSVWriter wayWriter = new CSVWriter(new FileWriter("ways.csv"))) {

            wayWriter.writeNext(new String[]{HOUSE_NUMBER, STREET, CITY, LAT, LON});

            Osmonaut osmonaut = new Osmonaut("./src/main/resources/RU-NVS.osm.pbf", entityFilter);
            osmonaut.scan(new IOsmonautReceiver() {
                public boolean needsEntity(EntityType entityType, Tags tags) {
                    return entityType.equals(EntityType.WAY) && tags.hasKey("addr:housenumber") && tags.hasKey("addr:street");
                }

                public void foundEntity(Entity entity) {
                    Way way = (Way) entity;
                    Tags tags = way.getTags();

                    String housenumber = tags.get("addr:housenumber");
                    String street = tags.get("addr:street");
                    String city = null;
                    if (tags.hasKey("addr:city")) {
                        city = tags.get("addr:city");
                    }
                    LatLon center = way.getCenter();


                    wayWriter.writeNext(new String[]{housenumber, street, city, String.valueOf(center.getLat()), String.valueOf(center.getLon())});
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
