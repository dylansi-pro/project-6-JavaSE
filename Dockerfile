FROM eclipse-temurin:26-jre-alpine

WORKDIR /app

COPY target/DSBank-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar

COPY src/main/resources /app/src/main/resources

ENTRYPOINT ["java", "-jar", "app.jar"]