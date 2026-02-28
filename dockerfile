# Используем официальный образ Playwright для Java
FROM mcr.microsoft.com/playwright/java:v1.48.0-jammy

WORKDIR /tests

# Копируем файлы проекта
COPY pom.xml .
COPY src ./src

# Скачиваем зависимости Maven
RUN mvn dependency:go-offline

# Браузеры уже предустановлены в образе!
# Не нужно ничего дополнительно устанавливать

# Запускаем тесты
CMD ["mvn", "clean", "test"]