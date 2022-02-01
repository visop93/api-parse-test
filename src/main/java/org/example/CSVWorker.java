package org.example;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

//class only for reading resources so no instances needed
public abstract class CSVWorker {
    private static final String items = "src/main/resources/items.csv";

    //read CSV and parse each id
    public static void parseApiById() {
//        var fileName = "src/main/resources/basic_crafting_materials.csv";
        var fileName = "src/main/resources/basic_crafting_materials_all.csv";
        try (var fr = new FileReader(fileName, StandardCharsets.UTF_8);
             var reader = new CSVReader(fr)) {

            //String container for line
            String[] nextLine = reader.readNext();

            //read whole CSV
            while ((nextLine = reader.readNext()) != null) {
                App.logger.info("Parsing ids -  {}", Arrays.toString(nextLine));
                //parseInt once
                int basicInt = Integer.parseInt(nextLine[0]);
                int refinedInt = Integer.parseInt(nextLine[1]);

                //parse api with id and create basic item
                JSONObject basic = JsonUtils.parseId(basicInt);
                new Items((int)basic.get("id"),
                        (int)basic.get("buy_price"),
                        (int)basic.get("sell_price"),
                        (String)basic.get("name"),
                        0,
                        refinedInt);

                //parse api with id and create refined item
                JSONObject refined = JsonUtils.parseId(refinedInt);
                new Items((int)refined.get("id"),
                        (int)refined.get("buy_price"),
                        (int)refined.get("sell_price"),
                        (String)refined.get("name"),
                        basicInt,
                        0);

            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    //write current item list to the .csv
    public static void writeItemsCSV (HashMap<Integer, Items> map) {
        try (Writer writer = new FileWriter(items)) {
            //create instance of writer
            var beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            beanToCsv.write(map.values()
                    .stream()
                    .peek(i -> App.logger.info("Writing - {}", i.toString())));
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            App.logger.error("Error writing csv - ", e);
        }
    }

    //read from .csv and return a HashMap
    //TODO: filter/map stream to peek only items that will be added to the map
    public static void readItemsCSV (HashMap<Integer, Items> map) {

        try (var fr = new FileReader(items, StandardCharsets.UTF_8)) {
            new CsvToBeanBuilder<Items>(fr)
                    .withType(Items.class)
                    .build()
                    .parse()
                    .stream()
                    .peek(i -> App.logger.info("{} has been read from csv", i.toString()))
                    .collect(Collectors.toMap(Items::getId, Function.identity()))
                    .forEach(map::putIfAbsent);
        } catch (IOException e) {
            App.logger.error("Error with access to csv - ", e);
        }
    }
}
