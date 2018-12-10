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
- `GET /` Főoldal
- `POST /register` Regisztrálás
- `GET /users` Tagok listázása
- `GET /users/:id` Egy tag adatlapja
- `PUT /users/:id/add-score?scorePoint=` Egy tag pontszámának növelése
- `PUT /users/:id/assigne` Házimunka hozzárendelése egy felhasználóhoz
- `PUT /users/:id/set-to-owner` Egy felhasználó kinevezése tulajdonossá
- `DELETE /users/:id` Egy tag törlése

- `POST /tasks` Új házimunka hozzáadása
- `GET /tasks` Házimunkák böngészése
- `GET /tasks/:id` Egy házimunka adatlapja
- `PUT /tasks/:id` Házimunka módosítása
- `PUT /tasks/:id/finished` Házimunka státusza 'Kész'-re állítása
- `DELETE /tasks/:id` Házimunka törlése


### 2.2 Adatbázisterv

![alt Database schema](https://github.com/thelfter/housework-app/blob/master/sql.svg)
