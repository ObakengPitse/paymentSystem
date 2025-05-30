FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy Maven wrapper and pom.xml first
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Make mvnw executable
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy the rest of the source
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

# Run the generated jar (replace with your actual JAR name if needed)
CMD ["java", "-jar", "target/Payment-System-0.0.1-SNAPSHOT.jar app.jar"]
