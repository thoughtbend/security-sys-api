FROM openjdk:8-jdk-alpine

# VOLUME /tmp

# ARG JAR_FILE

WORKDIR /app

LABEL application="security-sys-api" api-layer="sys"

EXPOSE 8080

RUN mkdir config

ENV CLASSPATH=/app/config:$CLASSPATH
#ENV SPRING_CONFIG_LOCATION=/data/config

COPY target/security-sys-api-0.0.3-SNAPSHOT.jar app.jar

# ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar","-Dspring.profiles.active=nonprodserver","/app.jar", "--spring.config.location=classpath:/data/config"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -classpath /config","-Dlogging.config=file:/app/config/log4j2.xml","-Dspring.profiles.active=nonprodserver","-jar","/app/app.jar"]