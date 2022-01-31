package org.example;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

//class only for reading resources so no instances needed
public abstract class CSVWorker {
    private static final String items = "src/main/resources/items.csv";

    //read CSV and parse each id
    public static void parseApiById() {
        var fileName = "src/main/resources/basic_crafting_materials.csv";
//        var fileName = "src/main/resources/basic_crafting_materials_all.csv";
        try (var fr = new FileReader(fileName, StandardCharsets.UTF_8);
             var reader = new CSVReader(fr)) {

            //String container for line
            String[] nextLine = reader.readNext();

            //read whole CSV
            while ((nextLine = reader.readNext()) != null) {
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
            beanToCsv.write(map.values().stream());
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
    }

    //read from .csv and return a HashMap
    public static HashMap<Integer, Items> readItemsCSV () {

        HashMap<Integer, Items> result;

        try (var fr = new FileReader(items, StandardCharsets.UTF_8)) {
            result = (HashMap<Integer, Items>) new CsvToBeanBuilder<Items>(fr)
                    .withType(Items.class)
                    .build()
                    .parse()
                    .stream()
                    .collect(Collectors.toMap(Items::getId, Function.identity()));
        } catch (IOException e) {
            result = new HashMap<>();
            e.printStackTrace();
        }

        return result;
    }
}
