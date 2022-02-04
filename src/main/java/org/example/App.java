package org.example;

import files.csv.CsvUtils;
import files.txt.TxtUtils;
import items.ItemsDB;

import static logcreator.LogCreator.logger;

public class App {
    public static void main(String[] args) {
        logger.info("Program started working.");

        TxtUtils.readIDs();

        CsvUtils.readItemsCSV(ItemsDB.ITEMS_MAP);

        CsvUtils.writeItemsToCSV(ItemsDB.ITEMS_MAP);

        logger.info("Program finished working.");
    }
}
