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

![alt Use-Case Diagram](https://github.com/thelfter/housework-app/blob/master/house-work-uc-diagram.png)

## 2. Tervezés
### 2.1 Végpontok
- `GET /` Főoldal
- `GET /login` Bejelentkező oldal
- `POST /login` Bejelentkezés
- `GET /logout` Kijelentkezés
- `GET /register` Regisztrációs oldal
- `POST /register` Regisztrálás
- `GET /users` Tagok listázása
- `GET /users/:id` Egy tag adatlapja
- `PUT /users/:id` Egy tag adatainak módosítása
- `GET /users/:id/finished` Egy tag elvégzett feladatai
- `GET /users/:id/delete` Egy tag törlése
- `GET /housework` Házimunkák böngészése
- `GET /housework/:id` Egy házimunka adatlapja
- `GET /housework/:id/edit` Egy házimunka szerkesztésének űrlapja
- `POST /housework/:id/edit` Házimunka módosításának elküldése
- `GET /housework/:id/assigne` Házimunka végzése
- `GET /housework/:id/delete` Házimunka törlése
- `GET /housework/add` Új házimunka hozzáadásának űrlapja
- `POST /housework/add` Új házimunka mentése
