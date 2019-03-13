Spotify Favorites
=================
Application that allows browsing Spotify resources and marking them as favorites.

Features
--------
* integrated spotify authorization
* local embedded mongo database that do not require installation
* spotify artist and tracks database browsing
* creating local tracks and artists lists of favorites
* user activity logging


Stack
-----
* Spring Boot
* <a href="https://github.com/flapdoodle-oss/de.flapdoodle.embed.mongo">Embedded Mongo</a>
* Thymeleaf
* JUnit
* Mockito
* Lombok
* Bootstrap, DataTables 
* Docker

Building
========
Spotify
-------
1. Enter <a href="https://developer.spotify.com/dashboard/">Spotify Developer Dashboard</a>
2. Create an application
3. Save `Client ID` and `Client Secret`
4. Whitelist `http://localhost:8080/login/spotify` as `Redirect Uri` in `Edit Settings`

Local Configuration
-------------
1. Open `/app/src/main/resources/application.yml`
2. Change the following lines according to your `Client ID` and `Client Secret` from Spotify Dashboard
```yaml
spotify:
  client:
    clientId: <Your Client ID>
    clientSecret: <Your Client Secret>
```
Building and running
--------
1. Run in /app
```bash
./mvnw clean package
```
2. Change directory to /app/target and run
```bash
java -jar spotifyfavorites-0.0.1-SNAPSHOT.jar
```
3. Application is running at `localhost:8080`

Running with Docker
---------------
1. After building application change directory to /app/target
2. Rename `spotifyfavorites-0.0.1-SNAPSHOT.jar` to `App.jar`
3. Copy `App.jar` to /docker/app/
4. Change directory to /docker and run
```bash
docker-compose up -d
```
Disabling integration tests
---------------------------
To disable integration tests open `/app/src/test/test/java/com/mdud/spotifyfavorites/TestConfig.java` and set
```java
static final boolean INTEGRATION_TESTS = false;
```

Screenshots
===========
![Searching Spotify](/docs/sf1.png)
![Favorite Artists](/docs/sf2.png)
![Logs](/docs/sf3.png)