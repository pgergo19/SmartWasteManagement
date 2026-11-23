
package hu.smartwaste.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WastebinTest {

    @Test
    void constructorShouldStoreDataCorrectly() {
        Wastebin bin = new Normalbin(1, "Pecs, Fo utca 1", 10000, 5000);

        assertEquals(1, bin.getId());
        assertEquals("Pecs, Fo utca 1", bin.getAddress());
        assertEquals(10000, bin.getCapacity());
        assertEquals(5000, bin.getFillLevel());
    }

    @Test
    void constructorShouldRejectInvalidId() {
        assertThrows(InvalidBinException.class,
                () -> new Normalbin(0, "Pecs, Fo utca 1", 10000, 5000));
    }

    @Test
    void constructorShouldRejectInvalidAddress() {
        assertThrows(InvalidBinException.class,
                () -> new Normalbin(1, "", 10000, 5000));

        assertThrows(InvalidBinException.class,
                () -> new Normalbin(1, null, 10000, 5000));
    }

    @Test
    void constructorShouldRejectInvalidCapacity() {
        assertThrows(InvalidBinException.class,
                () -> new Normalbin(1, "Pecs, Fo utca 1", 0, 0));
    }

    @Test
    void constructorShouldRejectInvalidFillLevel() {
        assertThrows(InvalidBinException.class,
                () -> new Normalbin(1, "Pecs, Fo utca 1", 10000, -1));

        assertThrows(InvalidBinException.class,
                () -> new Normalbin(1, "Pecs, Fo utca 1", 10000, 10001));
    }

    @Test
    void fillPercentageShouldBeCalculatedCorrectly() {
        Wastebin bin = new Normalbin(1, "Pecs, Fo utca 1", 10000, 7500);

        assertEquals(75.0, bin.getFillPercentage(), 0.001);
    }

    @Test
    void isFullShouldReturnCorrectResult() {
        Wastebin bin = new Normalbin(1, "Pecs, Fo utca 1", 10000, 10000);

        assertTrue(bin.isFull());

        bin.empty();

        assertFalse(bin.isFull());
    }

    @Test
    void emptyShouldSetFillLevelToZero() {
        Wastebin bin = new Normalbin(1, "Pecs, Fo utca 1", 10000, 5000);

        bin.empty();

        assertEquals(0, bin.getFillLevel());
    }
}
