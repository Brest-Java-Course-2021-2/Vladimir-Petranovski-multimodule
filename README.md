[![Java CI with Maven](https://github.com/Brest-Java-Course-2021-2/Vladimir-Petranovski-multimodule/actions/workflows/maven.yml/badge.svg)](https://github.com/Brest-Java-Course-2021-2/Vladimir-Petranovski-multimodule/actions/workflows/maven.yml)

# Brest Java Course 2021 2

# Vladimir-Petranovski-multimodule

This is sample 'Human Resources' web application.

## Requirements

* JDK 11
* Apache Maven

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
