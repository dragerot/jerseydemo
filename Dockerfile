FROM openjdk:8-jdk-alpine
VOLUME /tmp
WORKDIR /target
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
#ADD jerseydemo-1.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]