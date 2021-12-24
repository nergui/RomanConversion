FROM openjdk:11
EXPOSE 8080
ADD build/libs/app-roman-conversion-1.0.0.jar app-roman-conversion-1.0.0.jar
ENTRYPOINT ["java","-jar","/app-roman-conversion-1.0.0.jar"]