package hu.smartwaste.service;

import hu.smartwaste.model.Wastebin;
import java.util.List;

public interface BinSortingStrategy {

    void sort(List<Wastebin> bins);

}