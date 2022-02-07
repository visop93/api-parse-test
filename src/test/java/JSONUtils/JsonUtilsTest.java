package JSONUtils;

import items.ItemsDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilsTest {
    //not part of api on 05.02.2022
    public static String[] idArrFail() {
        return new String[] {"44", "41", "209999", "ara"};
    }

    @BeforeEach
    void init() {
        ItemsDB.ITEMS_MAP = new HashMap<>();
    }

    @Test
    public void emptyMap() {
        assertEquals(0, ItemsDB.ITEMS_MAP.size());
    }

    @ParameterizedTest
    @MethodSource(value = "idArrFail")
    void testParseLineWithFailParameters(String str) {
        JsonUtils.parseLine(str);
        assertEquals(0, ItemsDB.ITEMS_MAP.size());
    }

    @Test
    void demoTestMethod() {
        assertTrue(true);
    }

    @Test
    @DisplayName("Pass legit ID, adds new element.")
    void parseLineWorks() {
        String line = "19718";

        JsonUtils.parseLine(line);

        assertTrue(ItemsDB.ITEMS_MAP.containsKey(Integer.parseInt(line)));
    }

    @Test
    @DisplayName("Pass illegible ID, doesn't new element.")
    void parseLineFakeID() {
        String line = "44";

        JsonUtils.parseLine(line);

        assertEquals(0, ItemsDB.ITEMS_MAP.size());
    }

    @Test
    @DisplayName("Example of handling exceptions")
    void ExceptionThrowExampleTest() {
        assertThrows(NumberFormatException.class, JsonUtils::ExceptionThrowExample);
    }
}