package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//class only for reading resources so no instances needed
public abstract class CSVWorker {
    //read CSV and parse each id
    public static void readMaterialsCSV () {
        var fileName = "src/main/resources/basic_crafting_materials.csv";
        try (var fr = new FileReader(fileName, StandardCharsets.UTF_8);
             var reader = new CSVReader(fr)) {

            //String container for line
            String[] nextLine;

            //first line is header so we skip it
            nextLine = reader.readNext();

            //read whole CSV
            while ((nextLine = reader.readNext()) != null) {
                //parseInt once
                int basicInt = Integer.parseInt(nextLine[0]);
                int refinedInt = Integer.parseInt(nextLine[1]);

                //parse api with id and create basic item
                JSONObject basic = JsonUtils.parseId(basicInt);
                new ItemBasic((int)basic.get("id"),
                        (int)basic.get("buy_price"),
                        (int)basic.get("sell_price"),
                        (String)basic.get("name"),
                        refinedInt);

                //parse api with id and create refined item
                JSONObject refined = JsonUtils.parseId(refinedInt);
                new ItemRefined((int)refined.get("id"),
                        (int)refined.get("buy_price"),
                        (int)refined.get("sell_price"),
                        (String)refined.get("name"),
                        basicInt);

            }
        } catch (IOException e) {
            System.out.println(e.toString());
        } catch (CsvValidationException e) {
            System.out.println(e.toString());
        }
    }
}
