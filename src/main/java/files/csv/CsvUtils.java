package files.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import items.Item;
import logcreator.LogCreator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import static logcreator.LogCreator.logger;

public abstract class CsvUtils {
    private static final File items = new File("src/main/resources/items2.csv");

    //take Item collection and write it into CSV using opencsv
    public static void writeItemsToCSV (HashMap<Integer, Item> map) {
        logger.info("Writing Items into CSV file.");
        try (Writer writer = new FileWriter(items)) {
            //create instance of writer
            var beanToCsv = new StatefulBeanToCsvBuilder<Item>(writer).build();
            beanToCsv.write(map.values()
                    .stream()
                    .peek(i -> logger.info("Writing - {}", i.toString())));
        } catch (IOException e) {
            logger.error("Error accessing csv file '{}' - ", items.getPath(), e);
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            logger.error("Error writing csv - ", e);
        }
        logger.info("All items were written to the CSV file.");
    }

    //read CSV file and put absent items to the Item collection
    public static void readItemsCSV (HashMap<Integer, Item> map) {
        logger.info("Reading CSV file.");
        try (var fr = new FileReader(items, StandardCharsets.UTF_8)) {
            map.putAll(new CsvToBeanBuilder<Item>(fr)
                    .withType(Item.class)
                    .build()
                    .parse()
                    .stream()
                    .filter(i -> !map.containsKey(i.getId()))
                    .peek(i -> LogCreator.logger.info("{} added from csv", i))
                    .collect(Collectors.toMap(Item::getId, Function.identity())));
        } catch (IOException e) {
            logger.error("Error accessing csv file '{}' - ", items.getPath(), e);
        }
        logger.info("CSV file has been read.");
    }
}
