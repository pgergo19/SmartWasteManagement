
package hu.smartwaste.model;

public class Normalbin extends Wastebin {

    public Normalbin(int id, String address, int capacity, int fillLevel) {
        super(id, address, capacity, fillLevel);
    }

    @Override
    public String getType() {
        return "Kommunális";
    }
}
