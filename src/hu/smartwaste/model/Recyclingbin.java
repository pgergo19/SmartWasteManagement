package hu.smartwaste.model;

public class Recyclingbin extends Wastebin {

    private Wastetype wasteType;

    public Recyclingbin(int id, String address, int capacity,
                        int fillLevel, Wastetype wasteType) {
        super(id, address, capacity, fillLevel);
        this.wasteType = wasteType;
    }

    public Wastetype getWasteType() {
        return wasteType;
    }

    @Override
    public String getType() {
        return "Szelektív - " + wasteType;
    }
}