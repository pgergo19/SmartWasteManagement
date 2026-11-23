# A Strategy tervezési minta bemutatása

## 1. A tervezési minta célja

A Strategy (stratégia) tervezési minta lehetővé teszi, hogy egy adott feladat többféle algoritmusát külön osztályokban valósítsuk meg, és ezek között futás közben lehessen választani.

A projektben ezt a mintát a hulladékgyűjtők rendezésére alkalmazzuk. A felhasználó két rendezési szempont közül választhat anélkül, hogy a `WasteManagement` osztály működését módosítani kellene.

## 2. A megvalósítás

A megoldás központi eleme a `BinSortingStrategy` interfész, amely meghatározza a rendezési stratégiák közös műveletét:

- `sort(List<Wastebin> bins)`

Ezt az interfészt két konkrét osztály valósítja meg:

- **FillLevelStrategy:** a hulladékgyűjtőket telítettségük szerint, csökkenő sorrendben rendezi.
- **AddressStrategy:** a hulladékgyűjtőket címük szerint, ábécérendben rendezi.

A `WasteManagement` osztály `sortBins()` metódusa egy `BinSortingStrategy` paramétert kap, majd meghívja annak `sort()` metódusát. Így a rendezés konkrét módját nem a `WasteManagement` osztálynak kell ismernie.

## 3. A minta előnyei a projektben

- **Bővíthetőség:** később új rendezési szempont is hozzáadható a meglévő osztályok módosítása nélkül.
- **Újrafelhasználhatóság:** a rendezési algoritmusok külön osztályokban találhatók.
- **Rugalmas működés:** a felhasználó futás közben választhat a rendezési lehetőségek közül.
- **Kisebb összekapcsoltság:** a `WasteManagement` osztály nem függ egyetlen konkrét rendezési algoritmustól.

## 4. Összegzés

A Strategy tervezési minta alkalmazásával a hulladékgyűjtők rendezése jól elkülönül a rendszer többi részétől. A megoldás átláthatóbbá, könnyebben bővíthetővé és karbantarthatóbbá teszi a programot.