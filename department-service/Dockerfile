FROM eclipse-temurin:21-jdk
ARG JAR_FILE=target/department-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} department-service.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "department-service.jar"]