package files.txt;

import JSONUtils.JsonUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static logcreator.LogCreator.logger;

public abstract class TXTReader {
    public static void readIDs () {
        var fileName = "src/main/resources/ids_short.txt";
//        var fileName = "src/main/resources/ids_full.txt";

        logger.info("Reading file '{}'", fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                JsonUtils.parseId(inputLine);
            }
        } catch (IOException e) {
            logger.error("Can not read file '{}'", fileName);
//            e.printStackTrace();
        }
    }
}
