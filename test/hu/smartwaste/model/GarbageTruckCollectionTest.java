
package hu.smartwaste.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GarbageTruckCollectionTest {

    @Test
    void collectionBelowCapacityShouldSucceed() {
        GarbageTruck truck = new GarbageTruck(1, "ABC-123", 10000);
        Wastebin bin = new Normalbin(1, "Pecs, Fo utca 1", 10000, 5000);

        GarbageTruck.CollectionResult result = truck.collectBin(bin, false);

        assertEquals(GarbageTruck.CollectionResult.SUCCESS, result);
        assertEquals(5000, truck.getCurrentLoad());
        assertEquals(0, bin.getFillLevel());
    }

    @Test
    void reachingCapacityShouldSucceed() {
        GarbageTruck truck = new GarbageTruck(1, "ABC-123", 10000);
        Wastebin bin = new Normalbin(1, "Pecs, Fo utca 1", 10000, 10000);

        GarbageTruck.CollectionResult result = truck.collectBin(bin, false);

        assertEquals(GarbageTruck.CollectionResult.SUCCESS, result);
        assertEquals(10000, truck.getCurrentLoad());
    }

    @Test
    void exceedingCapacityShouldRequireConfirmation() {
        GarbageTruck truck = new GarbageTruck(1, "ABC-123", 10000);
        Wastebin bin = new Normalbin(1, "Pecs, Fo utca 1", 10500, 10500);

        GarbageTruck.CollectionResult result = truck.collectBin(bin, false);

        assertEquals(GarbageTruck.CollectionResult.NEEDS_CONFIRMATION, result);
        assertEquals(0, truck.getCurrentLoad());
        assertEquals(10500, bin.getFillLevel());
    }

    @Test
    void confirmedCollectionAboveCapacityShouldSucceed() {
        GarbageTruck truck = new GarbageTruck(1, "ABC-123", 10000);
        Wastebin bin = new Normalbin(1, "Pecs, Fo utca 1", 10500, 10500);

        GarbageTruck.CollectionResult result = truck.collectBin(bin, true);

        assertEquals(GarbageTruck.CollectionResult.SUCCESS, result);
        assertEquals(10500, truck.getCurrentLoad());
        assertEquals(0, bin.getFillLevel());
    }

    @Test
    void exceedingMaximumAllowedCapacityShouldFail() {
        GarbageTruck truck = new GarbageTruck(1, "ABC-123", 10000);
        Wastebin bin = new Normalbin(1, "Pecs, Fo utca 1", 12000, 11001);

        GarbageTruck.CollectionResult result = truck.collectBin(bin, true);

        assertEquals(GarbageTruck.CollectionResult.OVER_CAPACITY, result);
        assertEquals(0, truck.getCurrentLoad());
        assertEquals(11001, bin.getFillLevel());
    }

    @Test
    void collectingNullBinShouldThrowException() {
        GarbageTruck truck = new GarbageTruck(1, "ABC-123", 10000);

        assertThrows(
                IllegalArgumentException.class,
                () -> truck.collectBin(null, false)
        );
    }
}
