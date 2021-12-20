FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/ics-01.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
