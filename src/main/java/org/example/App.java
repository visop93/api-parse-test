package org.example;

import files.txt.TXTReader;
import items.ItemsDB;

import static logcreator.LogCreator.logger;

public class App {
    public static void main(String[] args) {
        logger.info("App started.");

        TXTReader.readIDs();

        ItemsDB.ITEMS_MAP.values().forEach(System.out::println);



//        logger.info("Parsing Api by IDs.");
//        CSVWorker.parseApiById();

//        logger.info("Reading csv to add items that are from the base");
//        CSVWorker.readItemsCSV(Items.ITEMS_MAP);

//        logger.info("Rewriting csv with new information");
//        CSVWorker.writeItemsCSV(Items.ITEMS_MAP);

        logger.info("Program stopped");
    }
}
