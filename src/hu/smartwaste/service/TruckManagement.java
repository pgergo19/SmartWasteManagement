package hu.smartwaste.service;


import hu.smartwaste.model.GarbageTruck;
import java.util.ArrayList;

public class TruckManagement {

    private ArrayList<GarbageTruck> trucks = new ArrayList<>();
    public boolean addTruck(GarbageTruck truck) {
        if (findTruck(truck.getId()) != null) {
            return false;
        }

        if (findTruckByLicensePlate(truck.getLicensePlate()) != null) {
            return false;
        }

        trucks.add(truck);
        return true;
    }
    public boolean removeTruck(int id) {
        GarbageTruck truck = findTruck(id);

        if (truck == null || truck.getCurrentLoad() > 0) {
            return false;
        }

        return trucks.remove(truck);
    }
    public boolean removeTruckByLicensePlate(String licensePlate) {
        GarbageTruck truck = findTruckByLicensePlate(licensePlate);

        if (truck == null || truck.getCurrentLoad() > 0) {
            return false;
        }

        return trucks.remove(truck);
    }

    public GarbageTruck findTruck(int id) {
        for (GarbageTruck truck : trucks) {
            if (truck.getId() == id) {
                return truck;
            }
        }
        return null;
    }
    public GarbageTruck findTruckByLicensePlate(String licensePlate) {
        for (GarbageTruck truck : trucks) {
            if (truck.getLicensePlate().equalsIgnoreCase(licensePlate)) {
                return truck;
            }
        }
        return null;
    }

    public ArrayList<GarbageTruck> getAllTrucks() {
        return new ArrayList<>(trucks);
    }


}