package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    public static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Just a log message.");
        logger.debug("Message for debug level.");

//        CSVWorker.parseApiById();
//        CSVWorker.writeItemsCSV(Items.ITEMS_MAP);
        Items.ITEMS_MAP = CSVWorker.readItemsCSV();

        Items.ITEMS_MAP.values().forEach(i -> System.out.println(i.toString()));
    }
}
