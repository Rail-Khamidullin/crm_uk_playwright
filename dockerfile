# Dockerfile
FROM maven:3.9.9-eclipse-temurin-21-alpine

WORKDIR /tests

# Копируем файлы проекта
COPY pom.xml .
COPY src ./src

# Запускаем тесты (команда будет переопределяться в Jenkins)
CMD ["mvn", "test"]