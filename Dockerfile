FROM openjdk:17-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file into the container
COPY ./build/libs/DataAnalysis.jar /app/app.jar

# Command to run the application
CMD ["java", "-jar", "/app/app.jar"]
