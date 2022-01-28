package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Items {
    public static List<Items> ITEMS_LIST = new ArrayList<>();
    private int id,
        buyPrice,
        sellPrice;

    public Items(int id, int buyPrice, int sellPrice) {
        this.id = id;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;

        ITEMS_LIST.add(this);
    }

    public int getId() {
        return id;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", buyPrice=" + buyPrice +
                ", sellPrice=" + sellPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Items items = (Items) o;
        return id == items.id && buyPrice == items.buyPrice && sellPrice == items.sellPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, buyPrice, sellPrice);
    }
}
