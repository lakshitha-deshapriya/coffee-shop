FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /reservation-service.jar
ENTRYPOINT ["java","-jar","reservation-service.jar"]