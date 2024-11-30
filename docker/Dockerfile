# Step 1: Use a lightweight JRE base image
FROM eclipse-temurin:17-jre-alpine

  # Step 2: Set the working directory inside the container
WORKDIR /app

  # Step 3: Copy the application JAR file into the container
COPY target/lats-0.0.1-SNAPSHOT.jar app.jar

  # Step 4: Expose the port your application uses
EXPOSE 6868

  # Step 5: Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]