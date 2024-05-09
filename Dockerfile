# syntax=docker/dockerfile:1
FROM ubuntu:latest as build
RUN apt-get update && \
    apt-get install -y git && \
    apt-get install -y openjdk-17-jdk-headless && \
    apt-get install -y maven
COPY . ./
RUN mvn clean package

FROM ubuntu:latest as run
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk-headless
COPY --from=build target/demo-0.0.1-SNAPSHOT-jar-with-dependencies.jar .
ENTRYPOINT ["java", "-jar", "demo-0.0.1-SNAPSHOT-jar-with-dependencies.jar"]