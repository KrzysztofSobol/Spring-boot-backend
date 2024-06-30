FROM openjdk:22-jdk
MAINTAINER kex.com
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]