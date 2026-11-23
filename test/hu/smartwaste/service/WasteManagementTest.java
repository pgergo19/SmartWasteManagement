
package hu.smartwaste.service;

import hu.smartwaste.model.Normalbin;
import hu.smartwaste.model.Wastebin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WasteManagementTest {

    private WasteManagement management;

    @BeforeEach
    void setUp() {
        management = new WasteManagement();
    }

    @Test
    void addBinShouldSucceed() {
        Wastebin bin = new Normalbin(1, "Pecs, Fo utca 1", 1000, 500);

        assertTrue(management.addBin(bin));
        assertEquals(1, management.getAllBins().size());
    }

    @Test
    void addDuplicateBinShouldFail() {
        Wastebin first = new Normalbin(1, "Pecs, Fo utca 1", 1000, 500);
        Wastebin second = new Normalbin(1, "Pecs, Kossuth utca 2", 800, 300);

        assertTrue(management.addBin(first));
        assertFalse(management.addBin(second));
        assertEquals(1, management.getAllBins().size());
    }

    @Test
    void findBinShouldReturnCorrectBin() {
        Wastebin bin = new Normalbin(1, "Pecs, Fo utca 1", 1000, 500);
        management.addBin(bin);

        assertSame(bin, management.findBin(1));
        assertNull(management.findBin(99));
    }

    @Test
    void removeBinShouldSucceed() {
        Wastebin bin = new Normalbin(1, "Pecs, Fo utca 1", 1000, 500);
        management.addBin(bin);

        assertTrue(management.removeBin(1));
        assertTrue(management.getAllBins().isEmpty());
    }

    @Test
    void removeNonExistingBinShouldFail() {
        assertFalse(management.removeBin(99));
    }
    @Test
    void sortByFillLevelShouldSortDescending() {
        Wastebin bin1 = new Normalbin(1, "Pecs, Fo utca 1", 1000, 300);
        Wastebin bin2 = new Normalbin(2, "Pecs, Kossuth utca 2", 1000, 900);
        Wastebin bin3 = new Normalbin(3, "Pecs, Arany Janos utca 3", 1000, 500);

        management.addBin(bin1);
        management.addBin(bin2);
        management.addBin(bin3);

        management.sortBins(new FillLevelStrategy());

        assertEquals(2, management.getAllBins().get(0).getId());
        assertEquals(3, management.getAllBins().get(1).getId());
        assertEquals(1, management.getAllBins().get(2).getId());
    }
    @Test
    void sortByAddressShouldSortAlphabetically() {
        Wastebin bin1 = new Normalbin(1, "Pecs, Zrinyi utca 1", 1000, 300);
        Wastebin bin2 = new Normalbin(2, "Pecs, Arany Janos utca 2", 1000, 500);
        Wastebin bin3 = new Normalbin(3, "Pecs, Kossuth utca 3", 1000, 700);

        management.addBin(bin1);
        management.addBin(bin2);
        management.addBin(bin3);

        management.sortBins(new AddressStrategy());

        assertEquals(2, management.getAllBins().get(0).getId());
        assertEquals(3, management.getAllBins().get(1).getId());
        assertEquals(1, management.getAllBins().get(2).getId());
    }
    @Test
    void sortingEmptyListShouldNotThrowException() {
        assertDoesNotThrow(() ->
                management.sortBins(new FillLevelStrategy())
        );

        assertTrue(management.getAllBins().isEmpty());
    }
}

