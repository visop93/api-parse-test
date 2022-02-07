package org.example;

import files.csv.CsvUtils;
import files.h2sql.H2SqlUtils;
import files.txt.TxtUtils;
import items.ItemsDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static logcreator.LogCreator.logger;

public class App {

    public static final String DB_DIR = "C:/Users/selfi/Desktop/JAVA/java-parser/src/main/resources";
    public static final String DB_FILE = "itemBase";
    public static final String DB_URL = "jdbc:h2:" + DB_DIR + "/" + DB_FILE;
    public static final String DB_Driver = "org.h2.Driver";

    public static void main(String[] args) {
        logger.info("Program started working.");

        parseApiByIds();

        checkSqlDB();

        CsvUtils.readItemsCSV(ItemsDB.ITEMS_MAP);

        CsvUtils.writeItemsToCSV(ItemsDB.ITEMS_MAP);

        logger.info("Program finished working.");
    }

    private static void parseApiByIds() {
        TxtUtils.readIDs();
    }

    private static void checkSqlDB() {
        try {
            logger.info("Connecting to the SQL db '{}'.", DB_FILE);
            Class.forName(DB_Driver);
            H2SqlUtils db = new H2SqlUtils("Items");
            db.createTable();
            db.fillTable(ItemsDB.ITEMS_MAP);
            db.readTable(ItemsDB.ITEMS_MAP);
        } catch (ClassNotFoundException e) {
            logger.error("JDBC driver '{}' no found.", DB_Driver, e);
        } catch (SQLException e) {
            logger.error("SQL error.", e);
        }
    }

    //return connection to the DB
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
