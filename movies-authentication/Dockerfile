#FROM openjdk:8u131-jdk-alpine
#ADD /movies-authentication/target/movies-authentication-0.0.1-SNAPSHOT.jar movies-authentication-authentication.jar
#ENTRYPOINT ["java",","-jar","/movies-authentication-authentication.jar"]

#### Stage 1: Build the movies-authenticationlication
FROM openjdk:8-jdk-alpine as build

# Set the current working directory inside the image
WORKDIR /movies-authentication

# Copy maven executable to the image
RUN ls
RUN pwd
COPY mvnw . 
COPY .mvn .mvn 

# Copy the pom.xml file
COPY pom.xml .

# Build all the dependencies in preparation to go offline. 
# This is a separate step so the dependencies will be cached unless 
# the pom.xml file has changed.
RUN chmod -R 777 mvnw
RUN ./mvnw dependency:go-offline -B

# Copy the project source
COPY src src

# Package the movies-authenticationlication

RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

#### Stage 2: A minimal docker image with command to run the movies-authentication 
FROM openjdk:8-jre-alpine

ARG DEPENDENCY=/movies-authentication/target/dependency

# Copy project dependencies from the build stage
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /movies-authentication/lib
COPY --from=build ${DEPENDENCY}/META-INF /movies-authentication/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /movies-authentication

ENTRYPOINT ["java","-cp","movies-authentication:movies-authentication/lib/*","com.movies.authentication.MoviesApplication"]