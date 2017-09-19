FROM openjdk:8
COPY build/libs/mech-orders-manager-0.1.0.jar /app.jar
EXPOSE 8080/tcp
ENTRYPOINT ["java", "-jar", "/app.jar"] cd