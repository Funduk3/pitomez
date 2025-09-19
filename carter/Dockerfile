# Этап сборки
FROM gradle:8.7.0-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle bootJar --no-daemon

# Этап запуска
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

# Активируем prod-профиль
ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=prod"]
