# Use a base image with a Java runtime environment
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the necessary files into the container
COPY build/libs/fat.jar /app/fat.jar

# Expose the port that your Ktor application listens on
EXPOSE 8080

# Command to run your Ktor application
CMD ["java", "-jar", "fat.jar"]
