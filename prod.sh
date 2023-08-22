#!/bin/bash

# Build the Docker image (if needed)
docker build -t ktor-auth .

# Run the Docker container
docker run -p 8080:8080 ktor-auth
