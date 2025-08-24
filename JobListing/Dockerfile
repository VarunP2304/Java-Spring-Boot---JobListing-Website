# --- Stage 1: Build the application with Maven ---
FROM maven:3.8.5-openjdk-17 AS builder

# Set the working directory for the build
WORKDIR /build

# Copy the pom.xml first to leverage Docker layer caching
COPY pom.xml .

# Copy the rest of the source code
COPY src ./src

# Run the Maven package command to build the jar. We skip tests for faster cloud builds.
RUN mvn package -DskipTests


# --- Stage 2: Create the final, lightweight runtime image ---
FROM openjdk:17-slim

# Set the working directory for the runtime
WORKDIR /app

# Copy the jar file that was created in the 'builder' stage
COPY --from=builder /build/target/JobListing-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# The command to run the application
ENTRYPOINT ["java","-jar","/app/app.jar"]