package org.example;

public class ItemBasic extends Items{
    private int refinedId;

    public ItemBasic(int id, int buyPrice, int sellPrice, String name, int refinedId) {
        super(id, buyPrice, sellPrice, name);
        this.refinedId = refinedId;
    }

    @Override
    public String toString() {
        return "ItemBasic{" +
                "refinedId=" + refinedId +
                super.toString();
    }
}
