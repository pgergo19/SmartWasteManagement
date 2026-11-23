package hu.smartwaste.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ApplicationData implements Serializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<Wastebin> bins;
    private ArrayList<GarbageTruck> trucks;

    public ApplicationData(ArrayList<Wastebin> bins,
                           ArrayList<GarbageTruck> trucks) {
        this.bins = bins;
        this.trucks = trucks;
    }

    public ArrayList<Wastebin> getBins() {
        return new ArrayList<>(bins);
    }

    public ArrayList<GarbageTruck> getTrucks() {
        return new ArrayList<>(trucks);
    }
}