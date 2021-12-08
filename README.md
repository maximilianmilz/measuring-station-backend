[![docker build](https://github.com/maximilianmilz/measuring-station-backend/actions/workflows/docker.yml/badge.svg?branch=master)](https://github.com/maximilianmilz/measuring-station-backend/actions/workflows/docker.yml)

## How to start the application?
* Install the latest [PostgreSQL server](https://www.postgresql.org/download/).
* Change database credentials and URL in [application.properties](src/main/resources/application.properties).
* Start the application :)

## Requirements

* JDK 11
* PostgreSQL
* Lombok

## jOOQ Model Generation

The jOOQ generation takes place in a different project `jooq`.

Requirements/preparation:
* check usage of JVM 13
* check `pom.xml` if jOOQ and postgreSQL library versions match with main project.

The following commands will create the jOOQ models.
```bash
cd jooq
mvn generate-sources
```
