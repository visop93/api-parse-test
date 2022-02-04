package JSONUtils;

import items.Item;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import static logcreator.LogCreator.logger;

public abstract class JsonUtils {
    //Access gw2api using String as an ID and create Item with it.
    public static void parseLine(String strId) {
        logger.info("Parsing line '{}'", strId);
        //id is a number
        if (!validateStringID(strId)){
            return;
        }

        //collect info from gw2api
        JSONObject jObj = collectInformationFromApi(strId);
//        logger.debug("{}", jObj.toString());
        if (jObj == null) {
            return;
        }

        //instantiate new item
        try {
            new Item((String) jObj.get("name"),
                    (int) jObj.get("id"),
                    (int) jObj.get("buy_price"),
                    (int) jObj.get("sell_price"));
        } catch (JSONException e) {
            logger.error("JSONException from '{}' ID.", strId, e);
        }
    }

    //collect needed information from api
    private static JSONObject collectInformationFromApi(String strId) {
        String commerce = "https://api.guildwars2.com/v2/commerce/prices/";
        String item = "https://api.guildwars2.com/v2/items/";

        JSONObject result = new JSONObject();

        try {
            JSONObject jObj = new JSONObject(parseUrl(createUrl(commerce + strId)));
//            logger.debug("{}", jObj.toString());

            if(validateJSONObjectFromApi(jObj)){
                logger.error("ID {} is not part of commerce gw2 api", strId);
                return null;
            }

            result.put("id", jObj.get("id"));
            result.put("buy_price", jObj.getJSONObject("buys").get("unit_price"));
            result.put("sell_price", jObj.getJSONObject("sells").get("unit_price"));

            //read items api
            jObj = new JSONObject(parseUrl(createUrl(item + strId)));
//            logger.debug("{}", jObj.toString());

            if(validateJSONObjectFromApi(jObj)){
                logger.error("ID {} is not part of items gw2 api", strId);
                return null;
            }

            result.put("name", jObj.get("name"));

        } catch (MalformedURLException e) {
            logger.error("Couldn't create URL out of '{}' ID.", strId, e);
            return null;
        } catch (IOException e) {
            logger.error("Error while parsing '{}' ID  url.", strId, e);
            return null;
        }

        return result;
    }

    //check if line is a number
    private static boolean validateStringID (String strId) {
        try {
            Integer.parseInt(strId);
            return true;
        } catch (NumberFormatException e) {
            logger.error("Txt file line {} is not a number.", strId, e);
            return false;
        }
    }

    //create URL.
    private static URL createUrl(String link) throws MalformedURLException {
        return new URL(link);
    }

    //parse URL into string.
    private static String parseUrl(URL url) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        //connect to URL using try-with-resources
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {

            String inputLine;
            //read line by line
            while ((inputLine = br.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
        }
        return stringBuilder.toString();
    }

    //check if part of api.
    private static boolean validateJSONObjectFromApi(JSONObject obj) {
        return obj.keySet().contains("text");
    }

    //example used to catch exception in at test.
    public static void ExceptionThrowExample() throws NumberFormatException {
        Integer.parseInt("nope");
    }
}
