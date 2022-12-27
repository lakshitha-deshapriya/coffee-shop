FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /shop-service.jar
ENTRYPOINT ["java","-jar","shop-service.jar"]