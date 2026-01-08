FROM eclipse-temurin:21-jdk
ARG JAR_FILE=target/api-gateway-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} api-gateway.jar
EXPOSE 8060
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]