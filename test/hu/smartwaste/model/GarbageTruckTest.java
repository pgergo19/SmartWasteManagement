
package hu.smartwaste.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GarbageTruckTest {

    @Test
    void emptyTruckShouldResetCurrentLoad() {
        GarbageTruck truck = new GarbageTruck(1, "ABC-123", 10000);
        Wastebin bin = new Normalbin(1, "Pecs, Fo utca 1", 10000, 5000);

        truck.collectBin(bin, false);

        assertEquals(5000, truck.getCurrentLoad());

        truck.emptyTruck();

        assertEquals(0, truck.getCurrentLoad());
    }

    @Test
    void emptyTruckShouldRemainEmptyWhenAlreadyEmpty() {
        GarbageTruck truck = new GarbageTruck(1, "ABC-123", 10000);

        truck.emptyTruck();

        assertEquals(0, truck.getCurrentLoad());
    }
}
