
package hu.smartwaste.service;

import hu.smartwaste.model.GarbageTruck;
import hu.smartwaste.model.Normalbin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TruckManagementTest {

    private TruckManagement management;

    @BeforeEach
    void setUp() {
        management = new TruckManagement();
    }

    @Test
    void addTruckShouldSucceed() {
        GarbageTruck truck = new GarbageTruck(1, "ABC-123", 10000);

        assertTrue(management.addTruck(truck));
        assertEquals(1, management.getAllTrucks().size());
    }

    @Test
    void addTruckWithDuplicateIdShouldFail() {
        GarbageTruck first = new GarbageTruck(1, "ABC-123", 10000);
        GarbageTruck second = new GarbageTruck(1, "XYZ-456", 15000);

        assertTrue(management.addTruck(first));
        assertFalse(management.addTruck(second));
        assertEquals(1, management.getAllTrucks().size());
    }

    @Test
    void addTruckWithDuplicateLicensePlateShouldFail() {
        GarbageTruck first = new GarbageTruck(1, "ABC-123", 10000);
        GarbageTruck second = new GarbageTruck(2, "abc-123", 15000);

        assertTrue(management.addTruck(first));
        assertFalse(management.addTruck(second));
        assertEquals(1, management.getAllTrucks().size());
    }

    @Test
    void findTruckByIdShouldReturnCorrectTruck() {
        GarbageTruck truck = new GarbageTruck(1, "ABC-123", 10000);
        management.addTruck(truck);

        assertSame(truck, management.findTruck(1));
        assertNull(management.findTruck(99));
    }

    @Test
    void findTruckByLicensePlateShouldIgnoreCase() {
        GarbageTruck truck = new GarbageTruck(1, "ABC-123", 10000);
        management.addTruck(truck);

        assertSame(truck, management.findTruckByLicensePlate("abc-123"));
        assertNull(management.findTruckByLicensePlate("XYZ-456"));
    }

    @Test
    void removeEmptyTruckShouldSucceed() {
        GarbageTruck truck = new GarbageTruck(1, "ABC-123", 10000);
        management.addTruck(truck);

        assertTrue(management.removeTruck(1));
        assertTrue(management.getAllTrucks().isEmpty());
    }

    @Test
    void removeTruckWithLoadShouldFail() {
        GarbageTruck truck = new GarbageTruck(1, "ABC-123", 10000);
        Normalbin bin = new Normalbin(1, "Pecs, Fo utca 1", 10000, 5000);

        truck.collectBin(bin, false);
        management.addTruck(truck);

        assertFalse(management.removeTruck(1));
        assertEquals(1, management.getAllTrucks().size());
    }

    @Test
    void removeTruckByLicensePlateShouldSucceed() {
        GarbageTruck truck = new GarbageTruck(1, "ABC-123", 10000);
        management.addTruck(truck);

        assertTrue(management.removeTruckByLicensePlate("abc-123"));
        assertTrue(management.getAllTrucks().isEmpty());
    }

    @Test
    void removeNonExistingTruckShouldFail() {
        assertFalse(management.removeTruck(99));
        assertFalse(management.removeTruckByLicensePlate("XYZ-456"));
    }
}
