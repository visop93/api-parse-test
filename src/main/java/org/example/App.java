package org.example;

public class App {
    public static void main(String[] args) {
        CSVWorker.readMaterialsCSV();
        Items.ITEMS_LIST.forEach(i -> System.out.println(i.toString()));
    }
}
