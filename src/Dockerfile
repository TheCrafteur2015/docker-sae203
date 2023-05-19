# source=docker/dockerfile:1
FROM debian:latest

RUN apt-get update && apt-get -y install openjdk-17-jdk openjdk-17-jre

COPY ./ChatServer.java /app

WORKDIR /app

EXPOSE 9001
CMD ["javac ChatServer.java", "java ChatServer"]
