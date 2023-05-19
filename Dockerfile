# source=docker/dockerfile:1
FROM debian:latest

RUN apt-get update && \
    apt-get -y install openjdk-17-jdk openjdk-17-jre

COPY ./src/ChatServer.java /app/

RUN mkdir /app/

WORKDIR /app/

RUN javac ChatServer.java

EXPOSE 9001
CMD ["java", "ChatServer"]
