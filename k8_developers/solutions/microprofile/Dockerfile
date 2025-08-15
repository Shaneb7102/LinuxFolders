FROM openjdk:8-jdk-alpine
#VOLUME /tmp
COPY target/microprofile-solution-0.1.0-thorntail.jar  app.jar
ENTRYPOINT ["java","-jar","-Djava.net.preferIPv4Stack=true", "app.jar"]