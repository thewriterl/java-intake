FROM openjdk:11
LABEL maintainer="thewriterl.github.io"
ADD target/java-intake-0.0.1-SNAPSHOT.jar demo-ted-talk.jar
ENTRYPOINT ["java","-jar","demo-ted-talk.jar"]