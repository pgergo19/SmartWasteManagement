package hu.smartwaste;

import hu.smartwaste.model.Normalbin;
import hu.smartwaste.model.Recyclingbin;
import hu.smartwaste.model.Wastebin;
import hu.smartwaste.model.Wastetype;
import hu.smartwaste.model.InvalidBinException;
import hu.smartwaste.service.WasteManagement;
import hu.smartwaste.model.GarbageTruck;
import hu.smartwaste.service.TruckManagement;
import hu.smartwaste.service.FillLevelStrategy;
import hu.smartwaste.service.AddressStrategy;


import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TruckManagement truckManagement = new TruckManagement();
    private static final WasteManagement management = new WasteManagement();


    public static void main(String[] args) {

        int choice;


            System.out.println("\n===== SMART WASTE MANAGEMENT =====");
            System.out.println("1. Kuka hozzáadása");
            System.out.println("2. Kukák listázása");
            System.out.println("3. Kuka törlése");
            System.out.println("4. Gyűjtésre váró kukák");
            System.out.println("5. Parancssor");
            System.out.println("6. Alapértelmezett kukásautó létrehozása");
            System.out.println("7. Egyedi kukásautó létrehozása");
            System.out.println("8. Kukásautók listázása");
            System.out.println("9. Kukásautó törlése");
            System.out.println("10. Hulladékbegyűjtés");
            System.out.println("11. Kukák rendezése");
            System.out.println("0. Kilépés");
        do {
            System.out.print("Választás: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    try {

                        System.out.println("\n--- Kuka hozzáadása ---");


                        int id;

                        while (true) {
                            id = readInt("Kuka ID: ");

                            if (management.findBin(id) != null) {
                                System.out.println("Ez az ID már használatban van! Adj meg másikat!");
                            } else {
                                break;
                            }
                        }



                        System.out.print("Kuka címe: ");
                        String address = scanner.nextLine();



                        int capacity = readInt("Kuk a kapacitása (liter): ");


                        System.out.print("Jelenlegi telítettség (liter): ");
                        int fillLevel = scanner.nextInt();
                        if (fillLevel < 0 || fillLevel > capacity) {
                            throw new InvalidBinException(
                                    "A telítettség nem lehet negatív vagy nagyobb a kapacitásnál!"
                            );
                        }

                        System.out.println("Kuka típusa:");
                        System.out.println("1. Kommunális");
                        System.out.println("2. Szelektív");
                        System.out.print("Választás: ");

                        int type = scanner.nextInt();
                        scanner.nextLine();

                        if (type == 1) {


                            if (management.addBin(
                                    new Normalbin(id, address, capacity, fillLevel))) {
                                System.out.println("Kommunális kuka hozzáadva!");
                            } else {
                                System.out.println("Hiba: Már létezik ilyen azonosítójú kuka!");
                            }

                        } else if (type == 2) {
                            System.out.println("Hulladék típusa:");
                            System.out.println("1. Papír");
                            System.out.println("2. Műanyag");
                            System.out.println("3. Üveg");
                            System.out.print("Választás: ");

                            int wasteChoice = scanner.nextInt();
                            scanner.nextLine();

                            Wastetype wasteType = null;

                            switch (wasteChoice) {
                                case 1:
                                    wasteType = Wastetype.PAPER;
                                    break;
                                case 2:
                                    wasteType = Wastetype.PLASTIC;
                                    break;
                                case 3:
                                    wasteType = Wastetype.GLASS;
                                    break;
                                default:
                                    System.out.println("Érvénytelen hulladéktípus!");
                                    break;
                            }
                            if (wasteType == null) {
                                break;
                            }
                            if (management.addBin(
                                    new Recyclingbin(id, address, capacity, fillLevel, wasteType))) {
                                System.out.println("Szelektív kuka hozzáadva!");
                            } else {
                                System.out.println("Hiba: Már létezik ilyen azonosítójú kuka!");
                            }





                        } else {
                            System.out.println("Érvénytelen kukatípus!");
                        }
                    }

                catch (InvalidBinException e){
                    System.out.println("Hiba: " + e.getMessage());

                }
                    System.out.println("--- Kérem a következő utasítást ---");
                    break;




                case 2:
                    System.out.println("--- Kukák listázása ---");
                    findBin();
                    System.out.println("--- Kérem a következő utasítást ---");
                    break;

                case 3:

                    int kid = readInt("Törlendő kuka ID-ja: ");

                    if (management.removeBin(kid)) {
                        System.out.println("A kuka sikeresen törölve!");
                    } else {
                        System.out.println(
                                "Hiba: Nem található ilyen azonosítójú kuka!"
                        );
                    }
                    System.out.println("--- Kérem a következő utasítást ---");
                    break;

                case 4:
                    System.out.println("Gyűjtésre váró kukák:");

                    for (Wastebin bin : management.getBinsToCollect()) {
                        System.out.println(
                                "ID: " + bin.getId()
                                        + " | Cím: " + bin.getAddress()
                                        + " | Telítettség: "
                                        + bin.getFillPercentage() + "%"
                        );
                    }
                    System.out.println("--- Kérem a következő utasítást ---");
                    break;

                case 5:
                    System.out.println("\n===== SMART WASTE MANAGEMENT =====");
                    System.out.println("1. Kuka hozzáadása");
                    System.out.println("2. Kukák listázása");
                    System.out.println("3. Kuka törlése");
                    System.out.println("4. Gyűjtésre váró kukák");
                    System.out.println("5. Parancssor");
                    System.out.println("0. Kilépés");
                    System.out.println("6. Alapértelmezett kukásautó létrehozása");
                    System.out.println("7. Egyedi kukásautó létrehozása");
                    System.out.println("8. Kukásautók listázása");
                    System.out.println("9. Kukásautó törlése");
                    System.out.println("10. Hulladékbegyűjtés");
                    System.out.println("11. Kukák rendezése");
                    System.out.println("--- Kérem a következő utasítást ---");

                    break;

                case 6:

                    createDefaultTruck();
                    System.out.println("--- Kérem a következő utasítást ---");

                    break;

                case 7:
                    createCustomTruck();
                    System.out.println("--- Kérem a következő utasítást ---");
                    break;

                case 8:
                    listTrucks();
                    System.out.println("--- Kérem a következő utasítást ---");
                    break;

                case 9:
                    System.out.println("Kukásautó törlése:");


                    int deleteChoice = readInt("1. Törlés ID alapján\n2. Törlés rendszám alapján\nVálasztás: ");


                    if (deleteChoice == 1) {
                        System.out.print("Add meg a kukásautó ID-ját: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        if (truckManagement.removeTruck(id)) {
                            System.out.println("Kukásautó sikeresen törölve!");
                        } else {
                            System.out.println("A kukásautó nem található, vagy nem üres a raktere!");
                        }

                    } else if (deleteChoice == 2) {
                        System.out.print("Add meg a rendszámot: ");
                        String licensePlate = scanner.nextLine();

                        if (truckManagement.removeTruckByLicensePlate(licensePlate)) {
                            System.out.println("Kukásautó sikeresen törölve!");
                        } else {
                            System.out.println("A kukásautó nem található, vagy nem üres a raktere!");
                        }

                    } else {
                        System.out.println("Érvénytelen választás!");
                    }
                    System.out.println("--- Kérem a következő utasítást ---");
                    break;

                case 10:
                    collectWaste();
                    System.out.println("--- Kérem a következő utasítást ---");
                    break;

                case 11:
                    sortBins();
                    System.out.println("--- Kérem a következő utasítást ---");
                    break;

                case 0:
                    System.out.println("Viszlát!");
                    break;

                default:
                    System.out.println("Érvénytelen menüpont!");
                    System.out.println("--- Kérem a következő utasítást ---");
            }


        } while (choice != 0);

        scanner.close();


    }

    private static void createDefaultTruck() {
        GarbageTruck truck = new GarbageTruck(1, "SWM-001", 5000);

        if (truckManagement.addTruck(truck)) {
            System.out.println("Az alapértelmezett kukásautó sikeresen létrejött!");
        } else {
            System.out.println("Már létezik ilyen azonosítójú kukásautó!");
        }
    }

    private static void createCustomTruck() {


        int id;

        while (true) {
            id = readInt("Kukásautó ID: ");

            if (truckManagement.findTruck(id) != null) {
                System.out.println("Ez az ID már használatban van! Adj meg másikat!");
            } else {
                break;
            }
        }

        System.out.print("Rendszám: ");
        String licensePlate = scanner.nextLine();


        int maxCapacity = readInt("Maximális kapacitás (liter): ");


        try {
            GarbageTruck truck = new GarbageTruck(id, licensePlate, maxCapacity);

            if (truckManagement.addTruck(truck)) {
                System.out.println("Kukásautó sikeresen létrehozva!");
            } else {
                System.out.println("Már létezik ilyen ID/rendszám!");
            }
        } catch (InvalidBinException e) {
            System.out.println("Hiba: " + e.getMessage());
        }
    }
    private static void listTrucks() {
        if (truckManagement.getAllTrucks().isEmpty()) {
            System.out.println("Nincsenek regisztrált kukásautók.");
            return;
        }

        for (GarbageTruck truck : truckManagement.getAllTrucks()) {
            System.out.println(
                    "ID: " + truck.getId() +
                            " | Rendszám: " + truck.getLicensePlate() +
                            " | Kapacitás: " + truck.getMaxCapacity() + " liter" +
                            " | Aktuális terhelés: " + truck.getCurrentLoad() + " liter"
            );
        }
    }
    private static void removeTruck() {
        System.out.print("Törlendő kukásautó ID-ja: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (truckManagement.removeTruck(id)) {
            System.out.println("Kukásautó sikeresen törölve!");
        } else {
            System.out.println("Nem található ilyen ID-jú kukásautó.");
        }
    }

    private static void collectWaste() {

        if (truckManagement.getAllTrucks().isEmpty()) {
            System.out.println("Nincsenek regisztrált kukásautók!");
            return;
        }

        if (management.getBinsToCollect().isEmpty()) {
            System.out.println("Jelenleg nincs gyűjtésre váró kuka!");
            return;
        }

        System.out.println("\n--- Hulladékbegyűjtés ---");

        System.out.println("Elérhető kukásautók:");
        listTrucks();


        int truckId = readInt("Add meg a kukásautó ID-ját: ");


        GarbageTruck truck = truckManagement.findTruck(truckId);

        if (truck == null) {
            System.out.println("Nem található ilyen kukásautó!");
            return;
        }

        System.out.println("\nGyűjtésre váró kukák:");

        for (Wastebin bin : management.getBinsToCollect()) {
            System.out.println(
                    "ID: " + bin.getId() +
                            " | Cím: " + bin.getAddress() +
                            " | Típus: " + bin.getType() +
                            " | Kuka kapacitása: " + bin.getCapacity() + " liter" +
                            " | Aktuális hulladék: " + bin.getFillLevel() + " liter" +
                            " | Telítettség: " + bin.getFillPercentage() + "%"
            );
        }

        int binId = readInt("Add meg a begyűjtendő kuka ID-ját: ");


        Wastebin bin = management.findBin(binId);

        if (bin == null || bin.getFillPercentage() < 70) {
            System.out.println("A kuka nem található, vagy még nem gyűjthető!");
            return;
        }

        int oldLoad = truck.getCurrentLoad();

        GarbageTruck.CollectionResult result =
                truck.collectBin(bin, false);

        if (result == GarbageTruck.CollectionResult.NEEDS_CONFIRMATION) {

            System.out.println(
                    "Figyelem! A begyűjtés túllépné a névleges kapacitást."
            );
            System.out.print("Biztosan folytatod? (I/N): ");

            String answer = scanner.nextLine();

            if (!answer.equalsIgnoreCase("I")) {
                System.out.println("A begyűjtés megszakítva.");
                return;
            }

            result = truck.collectBin(bin, true);
        }

        if (result == GarbageTruck.CollectionResult.OVER_CAPACITY) {
            System.out.println(
                    "Hiba! A begyűjtés túllépné a maximális, 110%-os kapacitást!"
            );
            return;
        }

        if (result == GarbageTruck.CollectionResult.SUCCESS) {

            System.out.println("A hulladékbegyűjtés sikeres!");

            double oldPercentage =
                    (double) oldLoad / truck.getMaxCapacity() * 100;

            double newPercentage = truck.getLoadPercentage();

            if (oldPercentage < 90 && newPercentage >= 90) {
                System.out.println(
                        "Figyelem! A kukásautó hamarosan eléri a névleges kapacitását!"
                );
            }

            if (oldPercentage < 100 && newPercentage >= 100) {
                System.out.println(
                        "A kukásautó elérte a névleges kapacitását!"
                );
            }

            if (oldPercentage <= 100 && newPercentage > 100) {
                System.out.println(
                        "Figyelem! A kukásautó túllépte a névleges kapacitását!"
                );
            }

            System.out.println(
                    "Jelenlegi terhelés: " +
                            truck.getCurrentLoad() + " / " +
                            truck.getMaxCapacity() + " liter"
            );
        }
    }
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            try {
                int number = Integer.parseInt(input.trim());

                if (number <= 0) {
                    System.out.println("Hibás bevitel! Pozitív egész számot adj meg!");
                    continue;
                }

                return number;

            } catch (NumberFormatException e) {
                System.out.println("Hibás bevitel! Kérlek, egész számot adj meg!");
            }
        }
    }
    private static void findBin(){
        for (Wastebin bin : management.getAllBins()) {
            System.out.println(
                    "ID: " + bin.getId()
                            + " | Cím: " + bin.getAddress()
                            + " | Típus: " + bin.getType()
                            + " | Telítettség: "
                            + bin.getFillPercentage() + "%"
            );
        }
    }
    private static void sortBins() {

        System.out.println("\n--- Kukák rendezése ---");
        System.out.println("1. Telítettség szerint");
        System.out.println("2. Cím szerint");

        int choice = readInt("Választás: ");

        switch (choice) {
            case 1:
                management.sortBins(new FillLevelStrategy());
                System.out.println("Kukák rendezve telítettség szerint!");
                break;

            case 2:
                management.sortBins(new AddressStrategy());
                System.out.println("Kukák rendezve cím szerint!");
                break;

            default:
                System.out.println("Érvénytelen választás!");
        }
    }
}

