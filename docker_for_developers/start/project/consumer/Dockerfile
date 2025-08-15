#---BUILD STAGE---
FROM maven:3.9.6-eclipse-temurin-8 AS build
WORKDIR /app

# Cache dependencies
COPY pom.xml .
RUN mvn -DskipTests dependency:go-offline

# Package code into jar
COPY src ./src
RUN mvn -DskipTests package

#---RUN STAGE---
FROM openjdk:8-jdk-alpine
WORKDIR /app

# get jar from build stage and run
COPY --from=build target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

#Hey micheal, is the as-build part not only for the multistage? we finished the consumer docker file if you want to take a look

# uhh
# will have a look