
FROM openjdk:21-jdk-slim

WORKDIR /app

COPY settings.gradle /app/settings.gradle
COPY build.gradle /app/build.gradle
COPY gradlew /app/gradlew
COPY gradle /app/gradle

COPY /application/build.gradle /app/application/build.gradle
COPY /application/src /app/application/src

COPY /domain/build.gradle /app/domain/build.gradle
COPY /domain/src /app/domain/src

COPY /infrastructure/build.gradle /app/infrastructure/build.gradle
COPY /infrastructure/src /app/infrastructure/src

COPY /presentation/build.gradle /app/presentation/build.gradle
COPY /presentation/src /app/presentation/src

RUN chmod +x gradlew

EXPOSE 8080

RUN  ./gradlew :presentation:bootJar

CMD ["java", "-jar", "-Dspring.profiles.active=docker" , "/app/presentation/build/libs/presentation-0.0.1.jar"]