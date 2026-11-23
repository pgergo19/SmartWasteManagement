package hu.smartwaste.service;

import hu.smartwaste.model.Wastebin;
import java.util.List;

public class AddressStrategy implements BinSortingStrategy {

    @Override
    public void sort(List<Wastebin> bins) {
        bins.sort((b1, b2) ->
                b1.getAddress().compareToIgnoreCase(b2.getAddress())
        );
    }
}