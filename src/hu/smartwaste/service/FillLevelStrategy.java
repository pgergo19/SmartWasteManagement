package hu.smartwaste.service;

import hu.smartwaste.model.Wastebin;
import java.util.List;

public class FillLevelStrategy implements BinSortingStrategy {

    @Override
    public void sort(List<Wastebin> bins) {
        bins.sort((b1, b2) ->
                Double.compare(b2.getFillPercentage(), b1.getFillPercentage())
        );
    }
}