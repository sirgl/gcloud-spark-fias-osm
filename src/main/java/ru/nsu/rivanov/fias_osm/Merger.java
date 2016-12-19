package ru.nsu.rivanov.fias_osm;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static ru.nsu.rivanov.fias_osm.OsmHouse.HOUSE_NUMBER;
import static ru.nsu.rivanov.fias_osm.OsmHouse.LAT;
import static ru.nsu.rivanov.fias_osm.OsmHouse.LON;

/**
 * Created by roman on 18.12.16.
 */
public class Merger {

    public static final String AOGUID = "aoguid";
    public static final String FIAS_HOUSENUM = "housenum";
    public static final String CITY_ROW_ID = "CityId";
    public static final String CITY_NAME = "CityName";
    public static final String FORMALNAME = "FORMALNAME";
    public static final String STREET_NAME = "StreetName";
    public static final String STREET_ID = "StreetId";
    public static final String HOUSE_ID = "HouseId";

    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
                .master("local")
                .appName("Merger")
                .getOrCreate();

        Dataset<Row> ways = spark.read().option("header", "true").csv("/home/roman/IdeaProjects/gcloud-spark-fias-osm/src/main/resources/ways.csv");
        Dataset<Row> addrobj = spark.read().option("header", "true").csv("/home/roman/IdeaProjects/gcloud-spark-fias-osm/src/main/resources/ADDROBJ.csv");
        Dataset<Row> house = spark.read().option("header", "true").csv("/home/roman/IdeaProjects/gcloud-spark-fias-osm/src/main/resources/HOUSE54.csv");

//        ways.show();
//        addrobj.show();

//        addrobj.join(house, AOGUID)
//                .join(ways,
//                        ways.col(HOUSE_NUMBER).equalTo(house.col(FIAS_HOUSENUM))
//                        .and(ways.col(STREET).equalTo(house.col("street?")))
//                )
//                .show();

//        addrobj.filter(addrobj.col("AREACODE").gt(54)).show();
//        Dataset<Row> addrobj54 = addrobj
//                .filter(addrobj.col("AREACODE").equalTo("054"))
//                .persist();


        Dataset<Row> cityAddrobj = addrobj
                .select(FORMALNAME, AOGUID)
                .withColumnRenamed(AOGUID, CITY_ROW_ID)
                .withColumnRenamed(FORMALNAME, CITY_NAME);

        Dataset<Row> addrobjWithStreets = addrobj
                .join(house, AOGUID)
                .join(ways, ways.col(HOUSE_NUMBER).equalTo(house.col(FIAS_HOUSENUM)))
                .join(cityAddrobj, cityAddrobj.col(CITY_ROW_ID).equalTo(addrobj.col("ParentGUID")))
                .withColumnRenamed(AOGUID, STREET_ID)
                .withColumnRenamed(FORMALNAME, STREET_NAME)
                .withColumnRenamed("HOUSEID", HOUSE_ID)
                .select(CITY_ROW_ID, CITY_NAME, STREET_ID, STREET_NAME, HOUSE_NUMBER, HOUSE_ID, LAT, LON);

        addrobjWithStreets.write().csv("/home/roman/IdeaProjects/gcloud-spark-fias-osm/result");
//        addrobjWithStreets.show();

//        addrobj.select("AREACODE").distinct().orderBy("AREACODE").show(1000);
    }
}
