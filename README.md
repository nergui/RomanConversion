## Roman Numeral Converter

* [Summary](#Summary)
* [How to run the project](#How-to-run)
* [Error handling](#Error-handling)
* [Additional endpoints](#Additional-endpoints)
* [Packaging layout](#Packaging-layout)
* [Testing](#Testing)
* [Dependency attribution](#Dependency-attribution)
* [How to run roman conversion service in docker container](#How-to-run-roman-conversion-service-in-docker-container)
* [References](#References)

## Summary
Rest java web service to convert numbers to roman numeral as strings. The main service endpoint is `/romannumeral` and can be used as follows:

Request:
```js
GET http://localhost:8080/romannumeral?query=21
```
Response:
```js
{"input":"21","output":"XXI"}
```

## Error handling
Request:
```js
GET http://localhost:8080/romannumeral?query=-1
```
Response:
```js
{"status":"400","message":"Query input must be a valid number and between 1 and 3999","time":"Sat Dec 25 17:32:19 PST 2021"}
```
Request:
```js
GET http://localhost:8080/romannumeral?query=WrongInput
```
Response:
```js
{"status":"400","message":"Query input must be a valid number.","time":"Sat Dec 25 17:58:11 PST 2021"}
```
## Additional-endpoints
Also available are the following spring boot actuator endpoints:
```js
/actuator
/actuator/metrics
/actuator/health
/actuator/loggers
```
## How-to-run
To run the application, run the following command in a terminal:
```js
./gradlew bootRun
```
* Build `./gradlew build`
* Run test with coverage `./gradlew jacocoTestReport`
## Packaging-Layout
* The main packages are located in `src/main/java/com/roman/converter`
    1. Controller - Currently, I have only one controller for our service.
    2. RomanNumeral - Model
    3. Service - Currently, I have an interface for roman number conversion maybe later we might come up with different implications
* Unit tests and Integration tests are located in `src/test/java/com/roman/converter`

## Dependency-attribution  
* Gradle
* Java
* Spring boot 2.6.2
* Spring boot web
* Spring boot actuator
* JUnitParams 1.1.1
* Lombok 1.18.22

## How-to-run-roman-conversion-service-in-docker-container
1. Create a jar
```js
./gradlew build
```
2. create Docker Image
```js
docker build -t app-roman-conversion-1.0.0.jar .
```
3. Run roman conversion service in a Docker Container
```js
docker run -p 9091:8080 app-roman-conversion-1.0.0.jar
```


## References
1. https://en.wikipedia.org/wiki/Roman_numerals
2. http://www.novaroma.org/via_romana/numbers.html

