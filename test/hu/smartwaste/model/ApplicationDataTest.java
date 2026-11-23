
package hu.smartwaste.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationDataTest {

    @Test
    void constructorShouldStoreBinsAndTrucks() {
        ArrayList<Wastebin> bins = new ArrayList<>();
        ArrayList<GarbageTruck> trucks = new ArrayList<>();

        bins.add(new Normalbin(1, "Pecs, Fo utca 1", 10000, 5000));
        trucks.add(new GarbageTruck(1, "ABC-123", 10000));

        ApplicationData data = new ApplicationData(bins, trucks);

        assertEquals(1, data.getBins().size());
        assertEquals(1, data.getTrucks().size());
    }

    @Test
    void getBinsShouldReturnDefensiveCopy() {
        ArrayList<Wastebin> bins = new ArrayList<>();
        bins.add(new Normalbin(1, "Pecs, Fo utca 1", 10000, 5000));

        ApplicationData data = new ApplicationData(
                bins, new ArrayList<>()
        );

        ArrayList<Wastebin> returnedBins = data.getBins();
        returnedBins.clear();

        assertEquals(1, data.getBins().size());
    }

    @Test
    void getTrucksShouldReturnDefensiveCopy() {
        ArrayList<GarbageTruck> trucks = new ArrayList<>();
        trucks.add(new GarbageTruck(1, "ABC-123", 10000));

        ApplicationData data = new ApplicationData(
                new ArrayList<>(), trucks
        );

        ArrayList<GarbageTruck> returnedTrucks = data.getTrucks();
        returnedTrucks.clear();

        assertEquals(1, data.getTrucks().size());
    }
}

