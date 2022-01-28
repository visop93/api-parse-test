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
        int id = 43357;
        JsonUtils.parseId(id);

        int id2 = 93326;
        JsonUtils.parseId(id2);

        Items.ITEMS_LIST.forEach(i -> System.out.println(i.toString()));
    }
}
