# --- Stage 1: Build the application with Maven & JDK 21 ---
# Use a Maven image that includes JDK 21 to match your pom.xml
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Set the working directory for the build
WORKDIR /build

# Copy pom.xml and download dependencies first to leverage Docker's build cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Run the Maven package command to build the jar. We skip tests for faster cloud builds.
RUN mvn package -DskipTests


# --- Stage 2: Create the final, lightweight runtime image ---
# Use a minimal JRE 21 image for a smaller and more secure final container
FROM eclipse-temurin:21-jre-alpine

# Set the working directory for the runtime
WORKDIR /app

# Copy the jar file that was created in the 'builder' stage
COPY --from=builder /build/target/JobListing-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# The command to run the application
ENTRYPOINT ["java","-jar","/app/app.jar"]