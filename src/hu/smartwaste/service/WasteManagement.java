package hu.smartwaste.service;

import hu.smartwaste.model.Wastebin;

import java.util.ArrayList;
import java.util.Comparator;

public class WasteManagement {

    private ArrayList<Wastebin> bins;

    public WasteManagement() {
        bins = new ArrayList<>();
    }

    public boolean addBin(Wastebin bin) {
        if (findBin(bin.getId()) != null) {
            return false;
        }

        bins.add(bin);
        return true;
    }

    public boolean removeBin(int id) {
        boolean removed = bins.removeIf(bin -> bin.getId() == id);

        return removed;
    }


    public Wastebin findBin(int id) {
        for (Wastebin bin : bins) {
            if (bin.getId() == id) {
                return bin;
            }
        }
        return null;
    }

    public ArrayList<Wastebin> getAllBins() {
        return new ArrayList<>(bins);
    }

    public ArrayList<Wastebin> getBinsToCollect() {
        ArrayList<Wastebin> result = new ArrayList<>();

        for (Wastebin bin : bins) {
            if (bin.getFillPercentage() >= 70) {
                result.add(bin);
            }
        }

        return result;
    }

    public void sortBinsByFillLevel() {
        bins.sort(Comparator.comparingDouble(
                Wastebin::getFillPercentage
        ).reversed());
    }
    public void sortBins(BinSortingStrategy strategy) {
        strategy.sort(bins);
    }
}
