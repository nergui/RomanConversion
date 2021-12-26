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
GET http://localhost:8080/romannumera?query=9.1
```
Response: 400 BAD_REQUEST

## Additional-endpoints
Also available are the following spring boot actuator endpoints:
```js
/actuator
/actuator/health
/actuator/info
/actuator/metrics
```
## How-to-run
To run the application, run the following command in a terminal:
```js
./gradlew bootRun
```
* Build and test with `./gradlew build`
## Packaging-Layout
* The main packages are located in `src/main/java/com/roman/converter`
    1. Controller - Currently, I have only one controller for our service.
    2. RomanNumeral - Model
    3. Service - Currently, I have an interface for roman number conversion maybe later we might come up with different implications
* Unit tests and Integration tests are located in `src/test/java/com/roman/converter`
## Testing
As per Unit tests and Integration tests, 95% of line covered as per `jacoco test report` and please see the below details
```js
./gradlew jacocoTestReport
```
```js
Test Coverage:
- Class Coverage: 100%
- Method Coverage: 83.3%
- Branch Coverage: 100%
- Line Coverage: 95%
- Instruction Coverage: 93.1%
- Complexity Coverage: 87.5%

```
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

