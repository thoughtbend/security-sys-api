FROM openjdk:8-jdk-alpine

# VOLUME /tmp

# ARG JAR_FILE

LABEL application="security-sys-api" api-layer="sys"

EXPOSE 8080

COPY target/security-sys-api-0.0.3-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar","-Dspring.profiles.active=nonprodserver","/app.jar"]