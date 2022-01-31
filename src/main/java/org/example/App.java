package org.example;

public class App {
    public static void main(String[] args) {
        //I want to add logs here
//        CSVWorker.parseApiById();
//        CSVWorker.writeItemsCSV(Items.ITEMS_MAP);
        Items.ITEMS_MAP = CSVWorker.readItemsCSV();

        Items.ITEMS_MAP.values().forEach(i -> System.out.println(i.toString()));
    }
}
