# Use Eclipse Temurin JDK 21 base image
FROM eclipse-temurin:21-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy Maven wrapper files and pom.xml first (for Docker cache)
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Download dependencies (uses cache if pom.xml hasn't changed)
RUN ./mvnw dependency:go-offline

# Copy the rest of the project source
COPY src ./src

# Build the application (skip tests if desired)
RUN ./mvnw clean package -DskipTests

# Expose the port your app runs on (usually 8080 for Spring Boot)
EXPOSE 8080

# Run the generated jar (replace with your actual JAR name if needed)
CMD ["java", "-jar", "target/Payment-System-0.0.1-SNAPSHOT.jar app.jar"]
