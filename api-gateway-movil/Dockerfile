# Usar una imagen base de OpenJDK con soporte para Alpine
FROM maven:3.9.6-eclipse-temurin-22-jammy AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:22-jdk-slim
WORKDIR /app
COPY --from=build /app/target/api-gateway-movil-0.0.1-SNAPSHOT.jar /app/api-gateway-movil.jar

EXPOSE 8087
CMD ["java", "-jar", "api-gateway-movil.jar"]