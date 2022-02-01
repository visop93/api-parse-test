package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    public static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("App started.");

        logger.info("Parsing Api by IDs.");
        CSVWorker.parseApiById();

        logger.info("Adding additional items to the base that was written into csv earlier");
        CSVWorker.readItemsCSV(Items.ITEMS_MAP);

        logger.info("Rewriting current information to the csv");
        CSVWorker.writeItemsCSV(Items.ITEMS_MAP);

        logger.info("Program stopped");
    }
}
