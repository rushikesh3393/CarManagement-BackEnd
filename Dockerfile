# --------- Build Stage ---------
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

# Copy the entire project
COPY . .

# Build the Spring Boot project (skip tests if needed)
RUN mvn clean package -DskipTests

# --------- Run Stage ---------
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the generated JAR from the build stage
COPY --from=build /app/target/CarGarageManagementSystem-0.0.1-SNAPSHOT.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
