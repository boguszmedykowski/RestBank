FROM eclipse-temurin:21-jdk-alpine

# Install bash to run the wait-for-it.sh script
RUN apk add --no-cache bash

# Copy the wait-for-it.sh script into the container
COPY wait-for-it.sh /wait-for-it.sh

# Make the script executable
RUN chmod +x /wait-for-it.sh

VOLUME /tmp

# Copy the JAR file into the container
COPY target/RestBank-0.0.1-SNAPSHOT.jar app.jar

# Use full path to the wait-for-it.sh script and bash to execute it
ENTRYPOINT ["/bin/bash", "/wait-for-it.sh", "db:3306", "--", "java", "-jar", "/app.jar"]
