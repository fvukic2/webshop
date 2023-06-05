# Naziv Projekta

Projekt Webshop je aplikacija za online kupovinu koja omogućava korisnicima pregled, pretraživanje i kupovinu artikala putem interneta.

![Java](https://img.shields.io/badge/Java-17%2B-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.1-green.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15.2-blue.svg)
![Hibernate](https://img.shields.io/badge/Hibernate-5.6.9-orange.svg)

## Funkcionalnosti

- Pregled dostupnih artikala po kategorijama
- Pretraživanje artikala po nazivu ili ključnim riječima
- Dodavanje artikala u košaricu
- Naručivanje artikala i izrada narudžbe
- Pregled detalja narudžbe 

## Tehnologije

- Java: programski jezik za backend razvoj
- Spring Framework: koristi se za razvoj web aplikacije
- Hibernate: ORM alat za pristup i manipulaciju bazom podataka
- PostgreSQL: baza podataka za pohranu podataka o artiklima, narudžbama i korisnicima

## Upute za instalaciju

1. Klonirajte repozitorij na svoje računalo: git clone https://github.com/fvukic2/webshop.git

2. Uvezite projekt u vašu razvojnu okolinu (npr. Eclipse, IntelliJ itd.)

3. Kreirajte PostgreSQL bazu podataka i ažurirajte postavke veze u `application.properties` datoteci.

4. Pokrenite aplikaciju. Ovo će inicijalizirati bazu podataka i stvoriti tablice za entitete.

5. Testirajte API endpointe putem kontrolera kako biste provjerili ispravnost funkcionalnosti. Možete koristiti alate kao što su Postman ili cURL za slanje HTTP zahtjeva.
