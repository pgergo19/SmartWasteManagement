
package hu.smartwaste.service;

import hu.smartwaste.model.ApplicationData;
import hu.smartwaste.model.GarbageTruck;
import hu.smartwaste.model.Normalbin;
import hu.smartwaste.model.Wastebin;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class DataStorageTest {

    @TempDir
    Path tempDir;

    @Test
    void saveAndLoadShouldRestoreData() {
        WasteManagement wasteManagement = new WasteManagement();
        TruckManagement truckManagement = new TruckManagement();

        Wastebin bin = new Normalbin(
                1, "Pecs, Fo utca 1", 10000, 5000
        );

        GarbageTruck truck = new GarbageTruck(
                1, "ABC-123", 10000
        );

        truck.collectBin(bin, false);

        wasteManagement.addBin(bin);
        truckManagement.addTruck(truck);

        String filePath = tempDir.resolve("test-data.dat").toString();
        DataStorage storage = new DataStorage(filePath);

        storage.saveData(wasteManagement, truckManagement);

        ApplicationData loadedData = storage.loadData();

        assertEquals(1, loadedData.getBins().size());
        assertEquals(1, loadedData.getTrucks().size());

        assertEquals(1, loadedData.getBins().get(0).getId());
        assertEquals(0, loadedData.getBins().get(0).getFillLevel());

        assertEquals(1, loadedData.getTrucks().get(0).getId());
        assertEquals(5000, loadedData.getTrucks().get(0).getCurrentLoad());
    }

    @Test
    void loadingNonExistingFileShouldReturnEmptyData() {
        String filePath = tempDir.resolve("missing.dat").toString();
        DataStorage storage = new DataStorage(filePath);

        ApplicationData loadedData = storage.loadData();

        assertTrue(loadedData.getBins().isEmpty());
        assertTrue(loadedData.getTrucks().isEmpty());
    }
}
