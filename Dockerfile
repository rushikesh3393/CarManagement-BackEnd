# Use a base image with Java installed
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file built from your project into the container
COPY target/CarGarageManagementSystem-0.0.1-SNAPSHOT.jar app.jar

# Command to run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
