# Use an official OpenJDK 17 runtime as the base image
FROM openjdk:17-slim

# Set the working directory inside the container to /app
WORKDIR /app

# Copy the compiled .jar file from your target folder into the container
# Make sure the .jar file name matches what Maven creates
COPY target/JobListing-0.0.1-SNAPSHOT.jar app.jar

# Tell Docker that the container will listen on port 8080
EXPOSE 8080

# The command to run your application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]