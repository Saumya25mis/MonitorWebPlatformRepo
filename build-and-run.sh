#!/bin/sh

# Build the target image using Dockerfile2
docker build -t my-custom-image -f /DockerFile2 .

# Run a container from the built image
docker run -t my-custom-image:latest
