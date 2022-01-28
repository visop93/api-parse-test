package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
//            String url = "http://eclipse.org";
            String url = "https://api.guildwars2.com/v2/commerce/prices/43357";
            Document doc = Jsoup.connect(url).get();
            String title = doc.title();
            System.out.println("Title : " + title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
