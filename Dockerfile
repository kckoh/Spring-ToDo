FROM openjdk:17-jdk-slim-buster

WORKDIR /backend

COPY build/libs/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
