
package hu.smartwaste.service;

import hu.smartwaste.model.ApplicationData;

import java.io.*;
import java.util.ArrayList;

public class DataStorage {

    private final String fileName;

    // Alapértelmezett konstruktor a főprogramnak
    public DataStorage() {
        this("data.dat");
    }

    // Teszteléshez használható konstruktor
    public DataStorage(String fileName) {
        this.fileName = fileName;
    }

    public void saveData(WasteManagement wasteManagement,
                         TruckManagement truckManagement) {

        ApplicationData data = new ApplicationData(
                wasteManagement.getAllBins(),
                truckManagement.getAllTrucks()
        );

        try (ObjectOutputStream output =
                     new ObjectOutputStream(new FileOutputStream(fileName))) {

            output.writeObject(data);
            System.out.println("Adatok sikeresen elmentve!");

        } catch (IOException e) {
            System.out.println("Hiba történt mentés közben: " + e.getMessage());
        }
    }

    public ApplicationData loadData() {

        File file = new File(fileName);

        if (!file.exists()) {
            return new ApplicationData(new ArrayList<>(), new ArrayList<>());
        }

        try (ObjectInputStream input =
                     new ObjectInputStream(new FileInputStream(fileName))) {

            return (ApplicationData) input.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Hiba történt betöltés közben: " + e.getMessage());
            return new ApplicationData(new ArrayList<>(), new ArrayList<>());
        }
    }
}
