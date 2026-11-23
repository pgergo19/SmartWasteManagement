
package hu.smartwaste.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinTypeTest {

    @Test
    void normalbinShouldReturnCorrectType() {
        Wastebin bin = new Normalbin(1, "Pecs, Fo utca 1", 10000, 5000);

        assertEquals("Kommunális", bin.getType());
    }

    @Test
    void recyclingbinShouldReturnCorrectType() {
        Wastebin bin = new Recyclingbin(
                2, "Pecs, Kossuth utca 1", 10000, 5000, Wastetype.PAPER
        );

        assertEquals("Szelektív - PAPER", bin.getType());
    }

    @Test
    void recyclingbinShouldStoreWasteType() {
        Recyclingbin bin = new Recyclingbin(
                2, "Pecs, Kossuth utca 1", 10000, 5000, Wastetype.PLASTIC
        );

        assertEquals(Wastetype.PLASTIC, bin.getWasteType());
    }

    @Test
    void differentBinTypesShouldBehavePolymorphically() {
        Wastebin normalbin = new Normalbin(
                1, "Pecs, Fo utca 1", 10000, 5000
        );

        Wastebin recyclingbin = new Recyclingbin(
                2, "Pecs, Kossuth utca 1", 10000, 5000, Wastetype.GLASS
        );

        assertEquals("Kommunális", normalbin.getType());
        assertEquals("Szelektív - GLASS", recyclingbin.getType());
    }
}
