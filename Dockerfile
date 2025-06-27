# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw pom.xml .
COPY .mvn .mvn

# Copy the source code
COPY src src

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose port 8080
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/ecom_proj-0.0.1-SNAPSHOT.jar"]
