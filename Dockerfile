# Use official Java image
FROM eclipse-temurin:17-jdk-jammy

# Set working directory inside container
WORKDIR /app

# Copy Maven wrapper and pom.xml first to leverage Docker cache
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Build the application (skip tests if desired)
RUN ./mvnw clean package -DskipTests

# Expose the app port
EXPOSE 8080

# Run the jar (replace with actual jar name if needed)
CMD ["java", "-jar", "target/Payment-System-0.0.1-SNAPSHOT.jar app.jar"]
