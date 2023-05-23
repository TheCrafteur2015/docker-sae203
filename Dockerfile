# source=docker/dockerfile:1
FROM debian:latest

RUN apt-get update && apt-get install -y openjdk-11-jdk

COPY ChatServer.java /app/

RUN javac /app/ChatServer.java -encoding utf8

CMD ["java", "-cp", "/app", "ChatServer"]

EXPOSE 9001
