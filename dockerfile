# Используем образ с Java и полной поддержкой браузеров
FROM maven:3.9.9-eclipse-temurin-21

# Устанавливаем Node.js и зависимости для браузеров
RUN apt-get update && apt-get install -y \
    curl \
    wget \
    gnupg \
    unzip \
    xvfb \
    libnss3 \
    libatk-bridge2.0-0 \
    libdrm2 \
    libxkbcommon0 \
    libgbm1 \
    libasound2 \
    && rm -rf /var/lib/apt/lists/*

# Устанавливаем Node.js 20
RUN curl -fsSL https://deb.nodesource.com/setup_20.x | bash - \
    && apt-get install -y nodejs

# Устанавливаем Playwright глобально
RUN npm install -g playwright

# Устанавливаем браузеры Playwright
RUN npx playwright install chromium --with-deps

WORKDIR /tests

# Копируем файлы проекта
COPY pom.xml .
COPY src ./src

# Скачиваем зависимости Maven
RUN mvn dependency:go-offline

# Запускаем тесты (переменные окружения для headless режима)
ENV PLAYWRIGHT_CHROMIUM_LAUNCH_OPTIONS="--no-sandbox --disable-dev-shm-usage"
CMD ["mvn", "clean", "test"]