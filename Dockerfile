FROM openjdk:17
ADD /target/news-api-0.0.1-SNAPSHOT.jar newsApi-backend.jar
ENTRYPOINT ["java", "-jar", "newsApi-backend.jar"]