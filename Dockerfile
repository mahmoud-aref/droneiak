# Use the official Gradle image as a base
FROM gradle:latest AS build

# Set the working directory in the container
WORKDIR /app

# Copy all modules to app
ADD . /app

# Build the application
RUN cd /app/presentation && gradle clean build --info


# Stage 2: Use AdoptOpenJDK as the base image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/presentation/build/libs/*.jar /app/presentation-0.0.1.jar

# Expose the port the application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "presentation-0.0.1.jar"]
