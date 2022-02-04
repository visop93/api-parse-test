package items;

import com.opencsv.bean.CsvBindByPosition;
import logcreator.LogCreator;

import java.util.Objects;

public class Item {
    @CsvBindByPosition(position = 0)
    private String name;

    @CsvBindByPosition(position = 1)
    private int id;

    @CsvBindByPosition(position = 2)
    private int buyPrice;

    @CsvBindByPosition(position = 3)
    private int sellPrice;

    //required to read csv file
    public Item () {}

    public Item(String name, int id, int buyPrice, int sellPrice) {
        this.name = name;
        this.id = id;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;

        ItemsDB.ITEMS_MAP.put(id, this);

        LogCreator.logger.info("{} has been instantiated and added to the item map", this.toString());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id && buyPrice == item.buyPrice && sellPrice == item.sellPrice && Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, buyPrice, sellPrice);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", buyPrice=" + buyPrice +
                ", sellPrice=" + sellPrice +
                '}';
    }
}
