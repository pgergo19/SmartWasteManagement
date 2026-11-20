package hu.smartwaste;

import hu.smartwaste.model.Normalbin;
import hu.smartwaste.model.Recyclingbin;
import hu.smartwaste.model.Wastebin;
import hu.smartwaste.model.Wastetype;
import hu.smartwaste.model.InvalidBinException;
import hu.smartwaste.service.WasteManagement;
import hu.smartwaste.model.GarbageTruck;
import hu.smartwaste.service.TruckManagement;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TruckManagement truckManagement = new TruckManagement();


    public static void main(String[] args) {


        WasteManagement management = new WasteManagement();

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
            System.out.println("0. Kilépés");
        do {
            System.out.print("Választás: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    try {

                        System.out.println("\n--- Kuka hozzáadása ---");

                        System.out.print("Kuka azonosítója: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        if (id <= 0) {
                            throw new InvalidBinException("Az azonosítónak pozitívnak kell lennie!");
                        }

                        System.out.print("Kuka címe: ");
                        String address = scanner.nextLine();
                        if (address == null || address.isBlank()) {
                            throw new InvalidBinException("A cím nem lehet üres!");
                        }

                        System.out.print("Kuka kapacitása (liter): ");
                        int capacity = scanner.nextInt();
                        if (capacity <= 0) {
                            throw new InvalidBinException(
                                    "A kapacitásnak pozitívnak kell lennie!"
                            );

                        }

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

                        for (Wastebin bin : management.getAllBins()) {
                            System.out.println(
                                    "ID: " + bin.getId()
                                        + " | Cím: " + bin.getAddress()
                                        + " | Típus: " + bin.getType()
                                        + " | Telítettség: "
                                        + bin.getFillPercentage() + "%"
                        );
                    }
                    System.out.println("--- Kérem a következő utasítást ---");
                    break;

                case 3:
                    System.out.print("Törlendő kuka ID-ja: ");
                    int kid = scanner.nextInt();

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
                    System.out.println("1. Törlés ID alapján");
                    System.out.println("2. Törlés rendszám alapján");
                    System.out.print("Választás: ");

                    int deleteChoice = scanner.nextInt();
                    scanner.nextLine();

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

        System.out.print("Kukásautó ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Rendszám: ");
        String licensePlate = scanner.nextLine();

        System.out.print("Maximális kapacitás (liter): ");
        int maxCapacity = scanner.nextInt();
        scanner.nextLine();

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
}