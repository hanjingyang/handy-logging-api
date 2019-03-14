FROM openjdk:8-jdk-alpine

# VOLUME /tmp
ARG JAR_FILE_NAME
COPY target/${JAR_FILE_NAME} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]