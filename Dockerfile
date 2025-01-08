# Use a lightweight OpenJDK image with Java 17
FROM openjdk:17-jdk-alpine

# Set the path to your JAR file
#ARG JAR_FILE=target/*.jar
# Set the argument for the JAR file
ARG JAR_FILE=target/cinab-0.0.1-SNAPSHOT.jar

# Copy the JAR file into the container
COPY ${JAR_FILE} app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
