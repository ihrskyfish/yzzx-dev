FROM mcr.microsoft.com/devcontainers/java:1-8-bullseye

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Verify Maven installation
RUN mvn -version 

# Install Gradle
# RUN apt-get update && \
#     apt-get install -y gradle && \
#     apt-get clean && \
#     rm -rf /var/lib/apt/lists/*