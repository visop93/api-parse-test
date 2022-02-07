package files.h2sql;

import files.txt.TxtUtils;
import items.ItemsDB;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class H2SqlUtilsTest {
    @Test
    void readTable() {
        //fill ItemDB.ITEMS_MAP
        TxtUtils.readIDs();

        H2SqlUtils db;
        try {
            db = new H2SqlUtils("Items");
            assertEquals(ItemsDB.ITEMS_MAP, db.readTable(ItemsDB.ITEMS_MAP));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}