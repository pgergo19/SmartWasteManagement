
package hu.smartwaste.model;

public class GarbageTruck {

    public enum CollectionResult {
        SUCCESS,
        NEEDS_CONFIRMATION,
        OVER_CAPACITY
    }

    private int id;
    private String licensePlate;
    private int maxCapacity;
    private int currentLoad;

    public GarbageTruck(int id, String licensePlate, int maxCapacity) {
        if (id <= 0) {
            throw new InvalidBinException("Az azonosítónak pozitívnak kell lennie!");
        }
        if (licensePlate == null || licensePlate.isBlank()) {
            throw new InvalidBinException("Nem adott meg rendszámot");
        }
        if (maxCapacity <= 0) {
            throw new InvalidBinException("A kapacitásnak pozitívnak kell lennie!");
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

    public double getLoadPercentage() {
        return (double) currentLoad / maxCapacity * 100;
    }

    public boolean isFull() {
        return currentLoad >= (int) (maxCapacity * 1.10);
    }

    public boolean isNominalCapacityReached() {
        return currentLoad >= maxCapacity;
    }

    public void emptyTruck() {
        currentLoad = 0;
    }

    public CollectionResult collectBin(Wastebin bin, boolean confirmed) {
        if (bin == null) {
            throw new IllegalArgumentException("A kuka nem lehet null!");
        }

        int newLoad = currentLoad + bin.getFillLevel();
        int maxAllowedLoad = (int) (maxCapacity * 1.10);

        // A 110%-os határt nem léphetjük túl.
        if (newLoad > maxAllowedLoad) {
            return CollectionResult.OVER_CAPACITY;
        }

        // A névleges kapacitás túllépéséhez megerősítés kell.
        if (newLoad > maxCapacity && !confirmed) {
            return CollectionResult.NEEDS_CONFIRMATION;
        }

        // Sikeres begyűjtés.
        currentLoad = newLoad;
        bin.empty();

        return CollectionResult.SUCCESS;
    }
}
