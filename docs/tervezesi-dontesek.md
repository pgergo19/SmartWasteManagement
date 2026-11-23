# Tervezési döntések és korlátozások

## 1. A projekt felépítése

A program objektumorientált felépítést követ. A különböző felelősségi köröket külön csomagokba és osztályokba szerveztük.

- **model:** a rendszer alapvető adatait és objektumait tartalmazza, például a hulladékgyűjtőket és a kukásautókat.
- **service:** az üzleti logikáért, az objektumok kezeléséért, rendezéséért és az adatok tárolásáért felel.
- **Main:** a konzolos felhasználói felületet és a menürendszert biztosítja.

A megoldás célja, hogy az adatokat kezelő osztályok és a felhasználói felület feladatai elkülönüljenek egymástól.

## 2. Adattárolás

A program az adatokat Java szerializáció segítségével, a `data.dat` nevű fájlban tárolja.

Induláskor megpróbálja betölteni a korábban elmentett adatokat, kilépéskor pedig elmenti az aktuális állapotot.

A szerializáció választásának előnye, hogy külső adatbázis vagy külön JSON-könyvtár nélkül is megvalósítható az objektumok tartós tárolása.

## 3. Hulladékgyűjtők kezelése

A hulladékgyűjtők telítettségét százalékos értékként is nyilvántartjuk.

A begyűjtésre váró edények listájába azok kerülnek, amelyek elérték a 70%-os telítettséget.

A `Wastebin` absztrakt osztályból két konkrét típus származik:

- `Normalbin`: kommunális hulladékgyűjtő.
- `Recyclingbin`: szelektív hulladékgyűjtő, amelyhez hulladéktípus is tartozik.

## 4. Kukásautók kapacitásának kezelése

A kukásautók rendelkeznek névleges maximális kapacitással.

A program a névleges kapacitás elérésekor figyelmeztetést ad. A névleges kapacitás túllépése esetén megerősítést kér a felhasználótól, de legfeljebb a névleges kapacitás 110%-áig engedélyezi a begyűjtést.

A 110%-os határ túllépése esetén a begyűjtés nem hajtható végre.

## 5. Hibakezelés és adatellenőrzés

A program ellenőrzi a fontosabb bemeneti adatokat, például az azonosítókat, a kapacitásokat és a telítettségi értékeket.

Az érvénytelen objektumadatok kezelésére saját `InvalidBinException` kivételosztályt használ.

A konzolos felületen a hibás számbevitelek kezelése is megvalósul, így a felhasználó javíthatja a hibás adatokat.

## 6. A rendszer korlátozásai

A jelenlegi alkalmazás egy egyszerű, konzolos prototípus, ezért az alábbi korlátozásokkal rendelkezik:

- Nem rendelkezik grafikus felhasználói felülettel.
- Nem használ külső adatbázist, az adatokat helyi fájlban tárolja.
- Nem támogat több felhasználót vagy jogosultsági szinteket.
- A begyűjtési és telítettségi szabályok előre meghatározott értékekkel működnek.
- A rendezés jelenleg telítettség és cím alapján lehetséges.

## 7. Összegzés

A tervezés során fontos szempont volt az egyszerű, átlátható és bővíthető objektumorientált felépítés. A program a feladatkiírásban szereplő alapvető követelményeket teljesíti, miközben lehetőséget biztosít későbbi funkciók hozzáadására.