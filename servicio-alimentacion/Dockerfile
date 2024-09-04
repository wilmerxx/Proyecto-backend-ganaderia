# Usar una imagen base de OpenJDK con soporte para Alpine

FROM maven:3.9.6-eclipse-temurin-22-jammy AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:22-jdk-slim
WORKDIR /app
COPY --from=build /app/target/ganaderiaGrupo1-0.0.1-SNAPSHOT.jar /app/servicio-alimentacion.jar

EXPOSE 8081
CMD ["java", "-jar", "servicio-alimentacion.jar"]

