FROM maven:3.9.6-eclipse-temurin-22-jammy AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:22-jdk-slim
WORKDIR /app
COPY --from=build /app/target/api-gateway-ganaderia-0.0.1-SNAPSHOT.jar /app/api-gateway-ganaderia.jar

EXPOSE 8086
CMD ["java", "-jar", "api-gateway-ganaderia.jar"]
