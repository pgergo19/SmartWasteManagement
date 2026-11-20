package hu.smartwaste.service;

import hu.smartwaste.model.ApplicationData;
import hu.smartwaste.model.Wastebin;
import hu.smartwaste.model.GarbageTruck;

import java.io.*;
import java.util.ArrayList;

public class DataStorage {

    private static final String FILE_NAME = "data.dat";

    public void saveData(WasteManagement wasteManagement,
                         TruckManagement truckManagement) {

        ApplicationData data = new ApplicationData(
                wasteManagement.getAllBins(),
                truckManagement.getAllTrucks()
        );

        try (ObjectOutputStream output =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            output.writeObject(data);
            System.out.println("Adatok sikeresen elmentve!");

        } catch (IOException e) {
            System.out.println("Hiba történt mentés közben: " + e.getMessage());
        }
    }

    public ApplicationData loadData() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return new ApplicationData(new ArrayList<>(), new ArrayList<>());
        }

        try (ObjectInputStream input =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            return (ApplicationData) input.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Hiba történt betöltés közben: " + e.getMessage());
            return new ApplicationData(new ArrayList<>(), new ArrayList<>());
        }
    }
}