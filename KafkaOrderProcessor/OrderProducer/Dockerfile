FROM openjdk:17
COPY target/*.jar app.jar
EXPOSE 9090
ENTRYPOINT ["sh" ,"-c", "java -jar /app.jar"]