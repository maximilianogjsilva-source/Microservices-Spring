FROM eclipse-temurin:21-jdk
ARG JAR_FILE=target/employee-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} employee-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "employee-service.jar"]