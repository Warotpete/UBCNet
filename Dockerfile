# 1. Use an official OpenJDK image as a base image
FROM openjdk:17-jdk-slim

# 2. Set a working directory inside the container
WORKDIR /app

# 3. Copy application JAR and data files into the container
COPY build/libs/UBCNet-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/data /app/data

# 4. Expose the application port
EXPOSE 8080

# 5. Environment variables for file paths
ENV ANNOUNCEMENTS_JSON_PATH=/app/data/announcements.json
ENV HOUSING_JSON_PATH=/app/data/housing.json
ENV SHOP_JSON_PATH=/app/data/shop.json
ENV APP_ENV=docker

# 6. Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]