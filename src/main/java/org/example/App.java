package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    public static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("App started.");

        logger.info("Parsing Api by IDs.");
        CSVWorker.parseApiById();

        logger.info("Reading csv to add items that are from the base");
        CSVWorker.readItemsCSV(Items.ITEMS_MAP);

        logger.info("Rewriting csv with new information");
        CSVWorker.writeItemsCSV(Items.ITEMS_MAP);

        logger.info("Program stopped");
    }
}
