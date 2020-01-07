FROM openjdk:8u131-jdk-alpine
ADD /movies-authentication/target/movies-authentication-0.0.1-SNAPSHOT.jar app-authentication.jar
ENTRYPOINT ["java","-jar","/app-authentication.jar"]
