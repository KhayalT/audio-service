FROM ubuntu:20.04

# Install necessary dependencies
RUN apt-get update && \
    apt-get install -y wget

# Download and install Java 21
RUN mkdir -p /opt/java && \
    wget -q -O /opt/java/openjdk-21.tar.gz https://example.com/path/to/your/java-21.tar.gz && \
    tar -xf /opt/java/openjdk-21.tar.gz -C /opt/java/ && \
    rm /opt/java/openjdk-21.tar.gz

# Set environment variables
ENV JAVA_HOME /opt/java/java-21
ENV PATH $PATH:$JAVA_HOME/bin

# Your additional Dockerfile configuration goes here...

# Set the working directory
WORKDIR /app

# Copy your application JAR file (replace 'your-app.jar' with your actual JAR file)
COPY target/audioservice-0.0.1-SNAPSHOT.jar app.jar

# Entry point command
CMD ["java", "-jar", "app.jar"]