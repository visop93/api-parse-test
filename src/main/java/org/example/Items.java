package org.example;

import com.opencsv.bean.CsvBindByPosition;

import java.io.Serializable;
import java.util.*;

public class Items  implements Serializable {
    public static HashMap<Integer, Items> ITEMS_MAP = new HashMap<>();

    @CsvBindByPosition(position = 0)
    private String name;

    @CsvBindByPosition(position = 1)
    private int id;

    @CsvBindByPosition(position = 2)
    private int buyPrice;

    @CsvBindByPosition(position = 3)
    private int sellPrice;

    @CsvBindByPosition(position = 4)
    private int basicId;

    @CsvBindByPosition(position = 5)
    private int refinedId;

    //required to read csv file
    public Items () {}

    public Items(int id, int buyPrice, int sellPrice, String name, int basicId, int refinedId) {
        this.id = id;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.name = name;
        this.basicId = basicId;
        this.refinedId = refinedId;

        App.logger.info(this.toString());

        ITEMS_MAP.put(id, this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getBasicId() {
        return basicId;
    }

    public void setBasicId(int basicId) {
        this.basicId = basicId;
    }

    public int getRefinedId() {
        return refinedId;
    }

    public void setRefinedId(int refinedId) {
        this.refinedId = refinedId;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", buyPrice=" + buyPrice +
                ", sellPrice=" + sellPrice +
                ", basicId=" + basicId +
                ", refinedId=" + refinedId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Items items = (Items) o;
        return id == items.id && buyPrice == items.buyPrice && sellPrice == items.sellPrice && basicId == items.basicId && refinedId == items.refinedId && Objects.equals(name, items.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, buyPrice, sellPrice, basicId, refinedId);
    }
}
