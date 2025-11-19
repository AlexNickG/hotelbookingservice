FROM amazoncorretto:20.0.2

WORKDIR /app

COPY build/libs/hotelbookingservice-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
#CMD ["echo", "SERVER_HOST", "SERVER_PORT"]