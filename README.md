# Housework application

A program egy házimunka alkalmazás egy kisebb játékkal kiegészítve. A játékban egy háztartást szimulálunk, ahol minden hét végére a lakóknak el kell érnie egy kitűzött pontszámot, amelyet házimunka elvégzésével kaphatnak.


## 1. Követelményanalízis
### 1.1 Követelmények
##### Funkcionális követelmények
- Regisztráció
- Bejelentkezés
- Bejelentkezett felhasználóknak
  - Házimunkák böngészése
  - Házimunka végzés
- Tulajdonos
  - Házimunka létrehozása
  - Házimunka szerkesztése
  - Házimunka eltávolítása
  - Felhasználó eltávolítása
  - Felhasználók nyomon követése

##### Nem funkciónális követelmények
- Felhasználóbarát, minimalista felület
- Autentikációval védett funkciók
- Reszponzív felület
- Pontrendszer

### 1.2 Szakterületi fogalomjegyzék

### 1.3 Használatieset-modell
Szerepek
- Vendég
- Regisztrált felhasználó / Bérlő
- Tulajdonos

![alt Use-Case Diagram](https://github.com/thelfter/housework-app/blob/master/house-work-uc-diagram.png)

## 2. Tervezés
### 2.1 Végpontok
- `POST /api/register?roomId=` Regisztrálás
- `GET /api/users` Tagok listázása
- `GET /api/users/:id` Egy tag adatlapja
- `PUT /api/users/:id/add-score?scorePoint=` Egy tag pontszámának növelése
- `PUT /api/users/:userId/assign/:taskId` Házimunka hozzárendelése egy felhasználóhoz
- `PUT /api/users/:userId/unassign/:taskId` Egy felhasználó házimunka hozzárendelésének megszűntetése 
- `DELETE /api/users/:id` Egy tag törlése

- `POST /api/tasks` Új házimunka hozzáadása
- `GET /api/tasks` Házimunkák böngészése
- `GET /api/tasks/:id` Egy házimunka adatlapja
- `PUT /api/tasks/:id` Házimunka módosítása
- `PUT /api/tasks/approve` Házimunka jóváhagyása
- `PATCH /api/tasks/:id/finished` Házimunka státusza 'Kész'-re állítása
- `DELETE /api/tasks/:id` Házimunka törlése

- `GET /api/rooms` Szobák lekérése
- `GET /api/rooms/:id` Egy szoba lekérése


### 2.2 Adatbázisterv

![alt Database schema](https://github.com/thelfter/housework-app/blob/master/sql.svg)
