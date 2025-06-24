# ---------- Stage 1: Build the app ----------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy all source code and pom.xml
COPY . .

# Package the Spring Boot app
RUN mvn clean package -DskipTests

# ---------- Stage 2: Run the app ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]