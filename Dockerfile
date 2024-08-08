# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

# Copy the project files to the container
COPY . .

# Download Maven dependencies
COPY pom.xml .
RUN mvn clean install

# Stage 2: Create the final image with Distroless JDK
FROM gcr.io/distroless/java17-debian12

WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port
EXPOSE 9092

# Command to run the application
CMD ["app.jar"]