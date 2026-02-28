# Используем официальный образ Playwright с Maven
FROM maven:3.9.9-eclipse-temurin-21-alpine

# Устанавливаем необходимые зависимости для Playwright
RUN apk add --no-cache \
    chromium \
    nss \
    freetype \
    harfbuzz \
    ca-certificates \
    ttf-freefont \
    && apk add --no-cache --virtual .build-deps \
    udev \
    ttf-opensans \
    && rm -rf /var/cache/apk/*

WORKDIR /tests

# Копируем файлы проекта
COPY pom.xml .
COPY src ./src

# Устанавливаем Playwright браузеры
RUN mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install chromium"

# Команда для запуска тестов
CMD ["mvn", "clean", "test"]