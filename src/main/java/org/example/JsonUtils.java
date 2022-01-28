package org.example;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

public class JsonUtils {
    /**
     * Метод для получения данных по указанной ссылке
     *
     * @param url - ссылка в виде объекта URL (Uniform Resource Locator)
     * @return содержимое страницы на указанной ссылке в @param url
     */
    public static String parseUrl(URL url) {
        if (url == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        // открываем соедиение к указанному URL
        // помощью конструкции try-with-resources
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {

            String inputLine;
            // построчно считываем результат в объект StringBuilder
            while ((inputLine = in.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    // парсим некоторые данные о погоде
    public static void parseCurrentWeatherJson(String resultJson) {
        // конвертируем строку с Json в JSONObject для дальнейшего его парсинга
        JSONObject weatherJsonObject = new JSONObject(resultJson);

        // получаем название города, для которого смотрим погоду
        System.out.println("Название: " + weatherJsonObject.get("name"));

        // получаем массив элементов для поля weather
        JSONArray weatherArray = (JSONArray) weatherJsonObject.get("weather");
        // достаем из массива первый элемент
        JSONObject weatherData = (JSONObject) weatherArray.get(0);

        // печатаем текущую погоду в консоль
        System.out.println("Погода на данный момент: " + weatherData.get("main"));
        // и описание к ней
        System.out.println("Более детальное описание погоды: " + weatherData.get("description"));
    }

    public static void parseId (int id) {
        //info to create URLs
        String idStr = String.valueOf(id);
        String commerce = "https://api.guildwars2.com/v2/commerce/prices/";
        String item = "https://api.guildwars2.com/v2/items/";

        //buffer object to contain information
        JSONObject obj = new JSONObject();

        //parsing information from apis
        parseCommerce(obj, createUrl(commerce + idStr));
        parseItem(obj, createUrl(item + idStr));

        //adding new item using acquired data
        new Items((int)obj.get("id"),
                (int)obj.get("buy_price"),
                (int)obj.get("sell_price"),
                (String)obj.get("name"));
    }

    public static void parseCommerce(JSONObject JSONObj, URL url) {
        //create JSONObject from string we receive parsing URL
        JSONObject commerceJSON = new JSONObject(parseUrl(url));

        //get id buy_price sell_price
        JSONObj.put("id", commerceJSON.get("id"));
        JSONObj.put("buy_price", commerceJSON.getJSONObject("buys").get("unit_price"));
        JSONObj.put("sell_price", commerceJSON.getJSONObject("sells").get("unit_price"));
    }

    public static void parseItem(JSONObject JSONObj, URL url) {
        //create JSONObject from string we receive parsing URL
        JSONObject commerceJSON = new JSONObject(parseUrl(url));

        //get id buy_price sell_price
        JSONObj.put("name", commerceJSON.get("name"));
    }


//    public static void

    public static void printJSONObjectKeys (JSONObject obj) {
        Iterator<String> keys = obj.keys();
        String key;
        System.out.print("\nJSONObject\n " + obj.toString() + "\n");
        while (keys.hasNext()) {
            key = (String)keys.next();
            System.out.print(" " + key);
        }
        System.out.print("\n");
    }

    // формируем новый JSON объект из нужных нам погодных данных
    public static String buildWeatherJson() {
        // для простоты примера просто хардкодим нужные данные в методе
        JSONObject jsonObject = new JSONObject();
        // задаем идентификатор
        jsonObject.put("weather_id", 0);

        // создаем поле с именем
        jsonObject.put("name", "Лондон");

        // используем функцию аккумулирования для добавления
        // элемента к существующему значению. В результате мы получим список значений
        jsonObject.accumulate("name", "Англия");
        // добавляет элемент в уже существующий список
        jsonObject.append("name", "(Великобритания)");

        // увеличиваем значение на единицу
        jsonObject.increment("weather_id");

        jsonObject.put("main", "Солнечно");
        jsonObject.put("description", "Мороз трескучий, На небе ни единой тучи");

        // позволяет представить JSON в удобном для HTML виде
        System.out.println(JSONObject.quote(jsonObject.toString()));

        return jsonObject.toString();
    }

    // конвертируем указанный JSONArray в отформатированную строку,
    // готовую для записи в файл формата CSV
    public static String convertJsonToCsv(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);

        return CDL.toString(jsonObject.getJSONArray("weather"));
    }

    // создаем объект URL из указанной в параметре строки
    public static URL createUrl(String link) {
        try {
            return new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
