[![Java CI with Maven](https://github.com/Brest-Java-Course-2021-2/Vladimir-Petranovski-multimodule/actions/workflows/maven.yml/badge.svg)](https://github.com/Brest-Java-Course-2021-2/Vladimir-Petranovski-multimodule/actions/workflows/maven.yml)

# Brest Java Course 2021 2

# Vladimir-Petranovski-MD-SpringBoot

This is sample 'Motor depot' web application.

## Project Information

- [Software requirements specification](documents/src/motor_depot.md)

## Technology Stack

- **Programming Language:** [Java](https://www.java.com) <a href="https://www.java.com" target="_blank"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="20" height="20"/> </a>
- **Core Framework:** [Spring Boot](https://spring.io/projects/spring-boot) <a href="https://spring.io/projects/spring-boot" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="18" height="18"/> </a>
- **Build System:** [Maven](https://maven.apache.org/)
- **Control System:** [Git](https://git-scm.com/) <a href="https://git-scm.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/git-scm/git-scm-icon.svg" alt="git" width="18" height="18"/> </a>
- **License:** [Apache license, version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
- **Automated Testing:**
    - [JUnit5](https://junit.org/junit5/)
    - [Mockito](http://site.mockito.org/)
- **Log:** [Log4j 2](https://logging.apache.org/log4j/2.x/)
- **Database:** [H2](http://www.h2database.com/html/main.html)
- **Template Engine:** [Thymeleaf](https://www.thymeleaf.org/)

## Requirements

* JDK 11+
* Git 2.25.1+
* Apache Maven 3.6.3+

## Build application:
```bash
$ git clone https://github.com/Brest-Java-Course-2021-2/Vladimir-Petranovski-MD-SpringBoot
$ cd Vladimir-Petranovski-MD-SpringBoot
$ mvn clean install
```

## Run tests:
```bash
$ mvn clean test
```

## Run integration tests:
```bash
$ mvn clean verify
```

## Run project information ( coverage, dependency, etc. ):
```bash
$ mvn site
$ mvn site:stage
Open in browser: ./target/staging/index.html
```

## Rest server

### Start Rest using Maven Jetty plugin

To start Rest using Maven Jetty plugin use:

```bash
$ cd rest-app
$ mvn jetty:run
```

## Web

### Start Web using Maven Jetty plugin

To start Web using Maven Jetty plugin use:

```bash
$ cd web-app
$ mvn jetty:run
```

## Available REST endpoints

## version

```bash
$ curl --request GET 'http://localhost:8088/version'
```

## drivers_dto

```bash
$ curl --request GET 'http://localhost:8088/drivers_dto'
```

#### Pretty print json:

```bash
$ curl --request GET 'http://localhost:8088/drivers_dto' | json_pp
```

## drivers

#### findAll

```bash
$ curl --request GET 'http://localhost:8088/drivers' | json_pp
```

#### findById

```bash
$ curl --request GET 'http://localhost:8088/drivers/1' | json_pp
```

### create

```bash
$ curl --request POST 'http://localhost:8088/drivers' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
"driverName": "TOLIA",
"driverDateStartWork": 1214677490.532700000,
"driverSalary": 980
}'
```

### update

```bash
$ curl --request PATCH 'http://localhost:8088/drivers/3' \
--header 'Content-Type: application/json' \
--data-raw '{
"driverName": "SERGEI",
"driverDateStartWork": 1014677990.532700000,
"driverSalary": 470
}'
```

### delete

```bash
$ curl --request DELETE 'http://localhost:8088/drivers/3/delete-driver'
```

## cars

#### findAll

```bash
$ curl --request GET 'http://localhost:8088/cars' | json_pp
```

#### findById

```bash
$ curl --request GET 'http://localhost:8088/cars/1' | json_pp
```

### create

```bash
$ curl --request POST 'http://localhost:8088/cars' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
"carModel": "GIGULI",
"driverId": 2
}'
```

### update

```bash
$ curl --request PATCH 'http://localhost:8088/cars/3' \
--header 'Content-Type: application/json' \
--data-raw '{
"carModel": "AUDI",
"driverId": 1
}'
```

## delete

```bash
$ curl --request DELETE 'http://localhost:8088/cars/3/delete-car'
```
