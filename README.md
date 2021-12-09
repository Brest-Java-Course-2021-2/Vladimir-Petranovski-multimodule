[![Java CI with Maven](https://github.com/Brest-Java-Course-2021-2/Vladimir-Petranovski-multimodule/actions/workflows/maven.yml/badge.svg)](https://github.com/Brest-Java-Course-2021-2/Vladimir-Petranovski-multimodule/actions/workflows/maven.yml)

# Brest Java Course 2021 2

# Vladimir-Petranovski-multimodule

This is sample 'Motor depot' web application.

## Business logic

${project}/documents/src/motor_depot.md

## Requirements

* JDK 11
* Apache Maven
* Clone repository from https://github.com/Brest-Java-Course-2021-2/Vladimir-Petranovski-multimodule

## Build application:
```
mvn clean install
```

## Run integration tests:
```
mvn clean verify
```

## Run project information ( coverage, dependency, etc. ):
```
mvn site
mvn site:stage

open in browser: ${project}/target/staging/index.html

## Rest server

### Start Rest using Maven Jetty plugin

To start Rest using Maven Jetty plugin use:

```
cd rest-app
mvn jetty:run
```

## Available REST endpoints

### version

```
curl --request GET 'http://localhost:8088/version'
```

### drivers_dto

```
curl --request GET 'http://localhost:8088/drivers_dto'
```

Pretty print json:

```
curl --request GET 'http://localhost:8088/drivers_dto' | json_pp
```

### drivers

#### findAll

```
curl --request GET 'http://localhost:8088/drivers' | json_pp
```

#### findById

```
curl --request GET 'http://localhost:8088/drivers/1' | json_pp
```

### create

```
curl --request POST 'http://localhost:8088/drivers' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
"driverName": "TOLIA",
"driverDateStartWork": 1214677490.532700000,
"driverSalary": 980
}'
```

## update

```
curl --request PATCH 'http://localhost:8088/drivers/3' \
--header 'Content-Type: application/json' \
--data-raw '{
"driverName": "SERGEI",
"driverDateStartWork": 1014677990.532700000,
"driverSalary": 470
}'
```

## delete

```
curl --request DELETE 'http://localhost:8088/drivers/3/delete-driver'
```

### cars

#### findAll

```
curl --request GET 'http://localhost:8088/cars' | json_pp
```

#### findById

```
curl --request GET 'http://localhost:8088/cars/1' | json_pp
```

### create

```
curl --request POST 'http://localhost:8088/cars' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
"carModel": "GIGULI",
"driverId": 2
}'
```

## update

```
curl --request PATCH 'http://localhost:8088/cars/3' \
--header 'Content-Type: application/json' \
--data-raw '{
"carModel": "AUDI",
"driverId": 1
}'
```

## delete

```
curl --request DELETE 'http://localhost:8088/cars/3/delete-car'
```
