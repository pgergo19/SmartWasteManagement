# Smart Waste Management

## Projektbemutatás

A Smart Waste Management egy Java nyelven fejlesztett, objektumorientált hulladékkezelő rendszer, amely konzolos felületen keresztül teszi lehetővé a hulladékgyűjtő edények és a hulladékszállító teherautók kezelését.

A program célja a hulladékgyűjtés folyamatának egyszerűsítése, a gyűjtőedények telítettségének nyomon követése, valamint a begyűjtési műveletek és az adatok tartós tárolásának kezelése.

## Főbb funkciók

- Kommunális és szelektív hulladékgyűjtők kezelése.
- Gyűjtőedények hozzáadása, listázása és törlése.
- Teherautók hozzáadása, listázása és törlése.
- A gyűjtőedények telítettségének nyomon követése.
- Begyűjtésre váró edények listázása.
- Hulladék begyűjtése kapacitásellenőrzéssel.
- Teherautók rakományának kezelése és ürítése.
- Gyűjtőedények rendezése különböző szempontok szerint.
- Adatok mentése és visszatöltése fájlból.

## Technológiák

- Java 17
- Maven
- JUnit 5
- IntelliJ IDEA
- Git és GitHub

## Objektumorientált megoldások

A projekt az objektumorientált programozás fontos alapelveit alkalmazza:

- **Egységbezárás (Encapsulation):** az osztályok adattagjai privátak, az adatokhoz gettereken és metódusokon keresztül lehet hozzáférni.
- **Öröklődés (Inheritance):** a `Normalbin` és a `Recyclingbin` osztály a közös `Wastebin` absztrakt osztályból származik.
- **Polimorfizmus (Polymorphism):** a különböző gyűjtőedények saját módon valósítják meg a `getType()` metódust.
- **Absztrakció (Abstraction):** a `Wastebin` absztrakt osztály és a rendezési stratégiákat meghatározó interfész közös működési alapot biztosít.

## Alkalmazott tervezési minta

A projekt a **Strategy tervezési mintát** alkalmazza a hulladékgyűjtő edények rendezésére.

A `BinSortingStrategy` interfész határozza meg a rendezési stratégiák közös működését. A különböző implementációk eltérő rendezési szempontokat valósítanak meg:

- `FillLevelStrategy`: rendezés telítettség szerint, csökkenő sorrendben.
- `AddressStrategy`: rendezés cím szerint, növekvő, ábécérendben.

A megoldás lehetővé teszi új rendezési módszerek bevezetését anélkül, hogy a `WasteManagement` osztály működését módosítani kellene.

## Adattárolás

A program Java szerializáció segítségével tárolja az adatokat a `data.dat` fájlban.

A program indulásakor megpróbálja betölteni a korábban mentett adatokat, kilépéskor pedig elmenti az aktuális állapotot.

## Telepítés és futtatás

### Követelmények

- Java Development Kit (JDK) 17 vagy újabb.
- IntelliJ IDEA.
- Maven támogatás.

### Futtatás IntelliJ IDEA-ban

1. Klónozd a GitHub-repozitóriumot, vagy nyisd meg a projekt könyvtárát.
2. Nyisd meg a projektet IntelliJ IDEA-ban.
3. Töltsd be a Maven-projektet a `pom.xml` fájl alapján.
4. Keresd meg a `Main.java` osztályt.
5. Indítsd el a programot a `Run` lehetőséggel.

### Tesztek futtatása

A tesztek futtatásához nyisd meg az IntelliJ IDEA Maven paneljét, majd a Lifecycle részen indítsd el a `test` feladatot.

A projekt JUnit 5 alapú automatikus teszteket tartalmaz.

## Tesztelés

A projekt jelenleg 42 automatizált tesztet tartalmaz, amelyek többek között az alábbi területeket ellenőrzik:

- Modellosztályok és adatvalidáció.
- Hulladékgyűjtők és teherautók kezelése.
- Hulladékbegyűjtési folyamatok és kapacitásellenőrzés.
- Rendezési stratégiák.
- Adatmentés és -betöltés.
- Defenzív másolatok működése.

## Projektstruktúra

```text
SmartWasteManagement/
│
├── src/
│   └── hu/smartwaste/
│       ├── model/
│       ├── service/
│       └── Main.java
│
├── test/
│   └── hu/smartwaste/
│       ├── model/
│       └── service/
│
├── docs/
├── pom.xml
├── .gitignore
└── README.md
```

## Dokumentáció

A projekt további dokumentációja a `docs` könyvtárban található:

- UML osztálydiagram.
- Felhasználói történetek (User Stories).
- A tervezési minta részletes bemutatása.
- Tervezési döntések és korlátozások.

## Készítők

A projekt a Java programozás II. tantárgy keretében készült.