package hu.smartwaste.model;

import hu.smartwaste.service.TruckManagement;

public class GarbageTruck {

    private int id;
    private String licensePlate;
    private int maxCapacity;
    private int currentLoad;

    public GarbageTruck(int id, String licensePlate, int maxCapacity) {
        if (id <= 0) {
            throw new InvalidBinException("Az azonosítónak pozitívnak kell lennie!");
        }
        if (licensePlate==null || licensePlate.isBlank()) {
            throw new InvalidBinException("Nem adott meg rendszámot");
        }
        if (maxCapacity <= 0) {
            throw new InvalidBinException("Az kapacitásnak pozitívnak kell lennie!");
        }

        this.id = id;
        this.licensePlate = licensePlate;
        this.maxCapacity = maxCapacity;
        this.currentLoad = 0;
    }

    public int getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public boolean isFull() {
        return currentLoad >= maxCapacity;
    }

    public void emptyTruck() {
        currentLoad = 0;
    }
    public boolean collectBin(Wastebin bin) {

        if (currentLoad + bin.getFillLevel() > maxCapacity) {
            return false;
        }

        currentLoad += bin.getFillLevel();
        bin.empty();

        return true;
    }
}
