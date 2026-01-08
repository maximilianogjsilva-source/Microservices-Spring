FROM eclipse-temurin:21-jdk
ARG JAR_FILE=target/service-registry-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} service-registry.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "service-registry.jar"]