# Housework application

A program egy házimunka alkalmazás egy kisebb játékkal kiegészítve. A játékban egy háztartást szimulálunk, ahol minden hónap végére a lakóknak el kell érnie egy kitűzött pontszámot, amelyet házimunka elvégzésével kaphatnak.


## 1. Követelményanalízis
### 1.1 Követelmények
##### Funkcionális követelmények
- Regisztráció
- Bejelentkezés
- Bejelentkezett felhasználóknak
  - Házimunkák böngészése
  - Házimunka végzés
  - Elvégzett házimunkák megtekintése
- Tulajdonos
  - Házimunka létrehozása
  - Házimunka szerkesztése
  - Házimunka eltávolítása
  - Felhasználó eltávolítása
  - Felhasználók nyomon követése

##### Nem funkciónális követelmények
- Felhasználóbarát, minimalista felület
- Autentikációval védett funkciók
- Optimalizáció külöböző böngészőkre
- Reszponzív felület
- Pontrendszer

### 1.2 Szakterületi fogalomjegyzék

### 1.3 Használatieset-modell
Szerepek
- Vendég
- Regisztrált felhasználó / Bérlő
- Tulajdonos
- Admin

![alt Use-Case Diagram](https://github.com/thelfter/housework-app/blob/master/house-work-uc-diagram.png)

## 2. Tervezés
### 2.1 Végpontok
- `GET /` Főoldal
- `POST /register` Regisztrálás
- `GET /users` Tagok listázása
- `GET /users/:id` Egy tag adatlapja
- `PUT /users/:id` Egy tag adatainak módosítása
- `PUT /users/:id/add-score?scorePoint=` Egy tag pontszámának módosítása
- `PUT /users/:id/assigne` Házimunka hozzárendelése egy felhasználóhoz
- `PUT /users/:id/set-to-owner` Egy felhasználó kinevezése tulajdonossá
- `DELETE /users/:id` Egy tag törlése

- `POST /tasks` Új házimunka hozzáadása
- `GET /tasks` Házimunkák böngészése
- `GET /tasks/:id` Egy házimunka adatlapja
- `PUT /tasks/:id` Házimunka módosítása
- `PUT /tasks/:id/finished` Házimunka státusza 'Kész'-re állítása
- `GET /tasks/:id/categories` Házimunka kategóriáinak listázása
- `PUT /tasks/:id/categories` Házimunka kategóriájának beállítása
- `DELETE /tasks/:id` Házimunka törlése

- `GET /rooms` Szobák listázása
- `GET /rooms/:id` Egy szoba adatlapja
- `PUT /rooms/:id` Egy szoba adatainak módosítása
- `DELETE /rooms/:id` Szoba törlése

- `GET /categoy` Feladat kategóriák listázása
- `GET /category/:id` Egy kategória adatlapja
- `PUT /category/:id` Egy kategória adatainak módosítása
- `DELETE /category/:id` Egy kategória törlése


### 2.2 Adatbázisterv

![alt Database schema](https://github.com/thelfter/housework-app/blob/master/sql.svg)
