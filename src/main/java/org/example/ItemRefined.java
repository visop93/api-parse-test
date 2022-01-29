package org.example;

public class ItemRefined extends Items{
    private int basicId;

    public ItemRefined(int id, int buyPrice, int sellPrice, String name, int basicId) {
        super(id, buyPrice, sellPrice, name);
        this.basicId = basicId;
    }

    @Override
    public String toString() {
        return "ItemRefined{" +
                "basicId=" + basicId +
                super.toString();
    }
}
