# Task Manager

## 0. Wymagania

Upewnij się, że masz zainstalowane:

`docker`
`maven`
`jdk11`

## 1. Instalacja i uruchomienie

Sklonuj repozytorium do folderu `tasks-service`

`git clone -b okta https://github.com/jalowiec/ask2021 tasks-service`

Zbuduj projekt

`cd tasks-service`\
`mvn install`

Uruchom kontenery

`docker-compose up`

## 2. Konfiguracja Keycloak

Wejdź na adres

http://localhost:8180

przejdź do `Administration Console` i zaloguj się używając loginu i hasła `admin`

### 2.1 Realm

W lewym górnym rogu najedź na `Master` i wybierz opcję `Add realm`. Nazwij go `TaskManager`.

### 2.2 Client

Wybierz w menu `Clients` i wciśnij `Create` w prawym górnym rogu tabeli.

Utwórz klienta z id `task-login`.

Po zapisaniu uzupełnij pole

`Valid Redirect URIs` : `http://localhost:8200/tasks/*`

i zapisz zmiany.

### 2.3 Roles

Wybierz w menu `Roles` i wciśnij `Add role` w prawym górnym rogu tabeli.

Wpisz nazwę `user` i zapisz.

Jeszcze raz wybierz w menu `Roles` i wejdź w zakładkę `Default Roles`. Zaznacz rolę `user` i wciśnij `Add selected`.

### 2.4 Users

Wybierz w menu `Users` i wciśnij `Add user` w prawym górnym rogu tabeli.

Wypełnij pola `Username`, `First Name`, `Last Name` i wciśnij przycisk `Save`.

Wejdź w zakładkę `Credentials` i wpisz hasło użytkownia odznaczając pole `Temporary`
### 2.5 Login flow

Wejdź w menu 'Authentication' i wciśnij `New` w prawym górnym rogu tabeli.

Wpisz alias `Task Manager Flow` i zapisz.

W prawym górnym rogu tabeli wybierz `Add execution`, wybierz w selektorze `Create User If Unique` i zapisz.
W tabeli zaznacz `Requirement` na `ALTERNATIVE`.

### 2.6 Indentity Provider

W panelu administracyjnym Keycloak wybierz w menu `Identity Providers` i w selektorze wybierz `OpenID Connect v1.0`.

Zmień alias na `okta-oidc` i wpisz `Display Name` : `Okta OpenID`.

W polu `First Login Flow` wybierz utworzony `Task Manager Flow`.

Skopiuj zawartość pola `Redirect URI` (domyślnie `http://localhost:8180/auth/realms/TaskManager/broker/okta-oidc/endpoint`)

## 3. Konfiguracja Okta

Nie zamykając okna z konfiguracją Identity Providera zaloguj się w panelu administracyjnym Okta

`https://***.okta.com/home/admin-entry`

Przejdź do menu `Applications` i wybierz `Add application`, a następnie `Create New App`.

Zmień `Sign on method` na `OpenID Connect` i naciśnij `Create`.

Ustaw nazwę aplikacji na `TaskManager` i dodaj w `Login redirect URIs` skopiowany wcześniej z panelu administracyjnego Keycloak adres

`http://localhost:8180/auth/realms/TaskManager/broker/okta-oidc/endpoint`

a do `Logout redirect URIs` adres

`http://localhost:8200/`

Zapisz utworzoną aplikację.

Z konfiguracji aplikacji w panelu Okta skopiuj wartości `Client ID` oraz `Client secret`.

Wklej je w konfiguracji Keycloak odpowiednio w pola `Client ID` i `Client secret` w sekcji `OpenID Connect Config`.

W polu `Client Authentication` wybierz `Client secret sent as post`.\
W polu `Default Scopes` wpisz `openid profile email`.

### 3.1 Endpointy

Nie zamykając konfiguracji Okta i Keycloak przejdź pod adres

`https://***.okta.com/oauth2/default/.well-known/openid-configuration?client_id=***`

Podając poprawny adres konta Okta oraz wygenerowany `Client ID`.

Z uzyskanej odpowiedzi znajdź i skopiuj wartości pól:

`authorization_endpoint`\
`token_endpoint`\
`end_session_endpoint`

i wprowadź je w konfiguracji Identity Provider w panelu Keycloak odpowiednio w polach:

`Authorization URL`\
`Token URL`\
`Logout URL`

Zapisz utworzony Identity Provider.

### 3.2 Użytkownicy

W panelu administracyjnym Okta w ustawieniach utworzonej aplikacji przejdź do zakładki `Assignments`.

Wybierz `Assign`->`Assign to People`.

Kliknij `Assign` przy użytkowniku i zapisz wciskając `Save and Go Back`.

## 4. Używanie aplikacji

### 4.0 Uruchomienie

Uruchom aplikację komendą

`java -jar target/tasks-service-0.0.1-SNAPSHOT.jar`

### 4.1 Logowanie

Przejdź pod adres

http://localhost:8200

i naciśnij przycisk `Check tasks`.

Możesz zalogować się jako użytkownik utworzony w Keycloak podając nazwę użytkownika, hasło i używając przycisku `Sign In` lub zalogować się jako użytkownik utworzony w panelu Okta używając przycisku `Okta OpenID`.

### 4.2 Dodawanie zadań

W panelu `Add task` wpisać tytuł i opis zadania i utworzyć je przyciskiem `Create`.

### 4.3 Wykonywanie zadań

Zadanie wyświetlone w tabeli można oznaczyć jako wykonane przyciskiem `Done`.

### 4.4 Usuwanie zadań

Zadania w tabeli można usuwać przyciskiem `Delete`.

### 4.5 Wylogowanie

Do wylogowania służy przycisk `Logout`.