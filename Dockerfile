FROM openjdk:21-jdk
COPY build/libs/poc-kafka-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "/app.jar"]