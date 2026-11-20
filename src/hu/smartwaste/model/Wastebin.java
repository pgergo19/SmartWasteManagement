package hu.smartwaste.model;

public abstract class Wastebin {

    private int id;
    private String address;
    private int capacity;
    private int fillLevel;


    public Wastebin(int id, String address, int capacity, int fillLevel) {

        if (id <= 0) {
            throw new InvalidBinException("Az azonosítónak pozitívnak kell lennie!");
        }

        if (address == null || address.isBlank()) {
            throw new InvalidBinException("A cím nem lehet üres!");
        }


        if (capacity <= 0) {
            throw new InvalidBinException("A kapacitásnak pozitívnak kell lennie!");
        }

        if (fillLevel < 0 || fillLevel > capacity) {
            throw new InvalidBinException(
                    "A telítettség nem lehet negatív vagy nagyobb a kapacitásnál!"
            );
        }

        this.id = id;
        this.address = address;
        this.capacity = capacity;
        this.fillLevel = fillLevel;
    }


    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFillLevel() {
        return fillLevel;
    }

    public double getFillPercentage() {
        return (double) fillLevel / capacity * 100;
    }

    public boolean isFull() {
        return fillLevel >= capacity;
    }

    public void empty() {
        fillLevel = 0;
    }

    public abstract String getType();

}
