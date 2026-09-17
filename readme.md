## Travel planner and journal app
___
To jest aplikacja do planowania podróży i dokumentowania swoich wspomnień.
Użytkownik może utworzyć własną wycieczkę, określając miejsce i termin podróży. Aplikacja, wykorzystując zewnętrzne REST API, pobiera informacje o atrakcjach dostępnych w miejscu podróży. Użytkownik może wybrać interesujące go miejsca i przypisać je do swojego planu wycieczki.
Po rozpoczęciu podróży użytkownik będzie mógł oznaczać zaplanowane miejsca jako odwiedzone oraz dodawać do nich własną ocenę i komentarz. Po zakończeniu podróży użytkownik będzie mógł napisać swoje wspomnienie w dzienniku.

### Główne funkcjonalności
- rejestracja i logowanie użytkownika - Spring Security, hashowanie haseł z BCrypt,
- tworzenie, edycja i usuwanie wycieczek - operacje na bazie danych MySQL
- wyszukiwanie lokalizacji za pomocą zewnętrznego API - RestClient do komunikowania się z Geoapify,
- wyszukiwanie atrakcji turystycznych za pomocą zewnętrznego API,
- dodawanie wybranych atrakcji do wycieczki,
- oznaczanie podróży jako zakończonych i dodawanie wpisu do dziennika.

### Technologie
- Java 17
- Maven
- Spring Boot 4
- Spring Web
- Spring Data JPA/Hibernate
- Spring Security, BCrypt
- MySQL
- REST API
- OpenAPI / Swagger

### Jak uruchomić
1. Sklonuj repozytorium
2. Utwórz bazę danych `create database travelPlannerJournal`
3. W katalogu głównym projektu utwórz plik `.env`:
```
SQL_USERNAME=<Twoja nazwa użytkownika>
SQL_PASSWORD=<Twoje hasło>

GEOAPIFY_API_KEY=<klucz API do Geoapify>
```
4. Uruchom aplikację
5. Przetestuj działanie poprzez Swaggera `http://localhost:8080/api/swagger-ui/index.html#/`
    - załóż konto `POST /auth/register`
    - zaloguj się `POST /auth/login`
    - możesz utworzyć wycieczkę i nią zarządzać


### W planach:
- wystawianie ocen i dodawanie komentarzy do odwiedzonych atrakcji,
- planowanie atrakcji na poszczególne dni,
- pobieranie prognozy pogody dla miejsca i terminu wycieczki,
- rekomendowanie atrakcji na podstawie warunków pogodowych,
- przeglądanie historii oraz podsumowania zakończonych podróży (średnia ocena, procent odwiedzonych atrakcji z zaplanowanej podróży).