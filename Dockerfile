# source=docker/dockerfile:1
FROM debian:latest

RUN apt-get update && apt-get install -y openjdk-11-jdk

COPY ChatServer.java /app/
COPY ChatClient.java /app/

RUN javac /app/ChatServer.java -encoding utf8
RUN javac /app/ChatClient.java -encoding utf8

EXPOSE 9001
CMD ["java", "-cp", "/app", "ChatServer"]

#RUN apt-get update && \
#    apt-get -y install openjdk-17-jdk openjdk-17-jre

#COPY ./src/ChatServer.java ./

#RUN javac ChatServer.java -encoding utf8

#EXPOSE 9001
#CMD ["java", "ChatServer"]
