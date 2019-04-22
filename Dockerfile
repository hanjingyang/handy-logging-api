FROM openjdk:8-jdk-alpine

# VOLUME /tmp
#ARG JAR_FILE_NAME
COPY target/handy-logging-api.jar app.jar
COPY apm/elastic-apm-agent-1.4.0.jar elastic-apm-agent.jar
COPY apm/elasticapm.properties elasticapm.properties
ENTRYPOINT ["java","-javaagent:/elastic-apm-agent.jar","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]