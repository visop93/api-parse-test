package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {
    public static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("App started.");

        logger.info("Parsing gw2api by IDs.");
        //parse gw2api to collect lates data
        CSVWorker.parseApiById();

        logger.info("Read items from csv.");
        //add to the Map previously checked IDs
        Items.ITEMS_MAP.putAll(CSVWorker.readItemsCSV());

        logger.info("Write items to the csv.");
        //rewrite CSV with new data
        CSVWorker.writeItemsCSV(Items.ITEMS_MAP);


        Items.ITEMS_MAP.values().forEach(i -> System.out.println(i.toString()));
        logger.info("App closed.");
    }
}
