package items;

import java.util.HashMap;

public abstract class ItemsDB {
    public static HashMap<Integer, Item> ITEMS_MAP = new HashMap<>();

    ItemsDB() {}
}
