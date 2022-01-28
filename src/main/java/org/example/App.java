package org.example;

import java.net.URL;

//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;

//import java.io.IOException;

//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.Scanner;

public class App {
    public static final String WEATHER_URL =
            "http://api.openweathermap.org/data/2.5/weather?q=London,uk" +
                    "&units=metric&appid=241de9349721df959d8800c12ca4f1f3";

    public static void main(String[] args) {
        // создаем URL из строки
        URL url = JsonUtils.createUrl(WEATHER_URL);

        // загружаем Json в виде Java строки
        String resultJson = JsonUtils.parseUrl(url);
        System.out.println("\nПолученный JSON:\n" + resultJson);

        // парсим полученный JSON и печатаем его на экран
        JsonUtils.parseCurrentWeatherJson(resultJson);

        // формируем новый JSON объект из нужных нам погодных данных
        String json = JsonUtils.buildWeatherJson();
        System.out.println("\nСозданный нами JSON:\n" + json);

        // преобразуем массив с json содержимым в строку со значениями через запятую
        // то есть в строку, удобную для сохранения в CSV файл
        String csvString = JsonUtils.convertJsonToCsv(resultJson);
        System.out.println("\nСтрока для CSV файла:\n" + csvString);
//        try {
////            String url = "http://eclipse.org";
//            String url = "https://api.guildwars2.com/v2/commerce/prices/43357";
//            Document doc = Jsoup.connect(url).get();
//            String title = doc.title();
//            System.out.println("Title : " + title);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
