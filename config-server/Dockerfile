FROM eclipse-temurin:21-jdk
ARG JAR_FILE=target/config-server-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} config-server.jar
EXPOSE 8088
ENTRYPOINT ["java", "-jar", "config-server.jar"]