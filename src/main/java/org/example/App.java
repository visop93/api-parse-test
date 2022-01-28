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
    public static void main(String[] args) {
        //создаем URL
        String gw2api = "https://api.guildwars2.com/v2/commerce/prices/43357";
        URL urlGw2 = JsonUtils.createUrl(gw2api);

        //загружаем полученный Json в виде строки
        String gw2ApiResult = JsonUtils.parseUrl(urlGw2);

        //parse необходимую информацию
        JsonUtils.parseApiRespondJSONObject(gw2ApiResult);

        Items.ITEMS_LIST.forEach(i -> System.out.println(i.toString()));

        //test with weather
//        URL url = JsonUtils.createUrl(WEATHER_URL);
//        String wResult = JsonUtils.parseUrl(url);
//        JsonUtils.parseApiRespondJSONObject(wResult);
    }
}
