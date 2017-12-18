FROM openjdk:8-jdk-alpine

VOLUME /tmp

ADD target/spring-security-with-jwt-1.0.0.jar app.jar

ENTRYPOINT exec java -jar app.jar