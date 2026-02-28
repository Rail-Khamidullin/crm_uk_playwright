package com.tests;

import com.config.ConfigurationManager;
import com.microsoft.playwright.Page;
import com.utils.BrowserManager;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {

    protected BrowserManager browserManager;
    protected Page page;

    @BeforeAll
    public void beforeAll() {
        // Создаем менеджер браузера один раз для всех тестов
        browserManager = new BrowserManager();

        // Дополнительные настройки для запуска в Docker
        setupBrowserForDocker();
    }

    private void setupBrowserForDocker() {
        // Получаем доступ к Playwright и браузеру через рефлексию или добавляем метод в BrowserManager
        // Пока просто выведем информацию
        System.out.println("🔧 Running in Docker mode - browser should be headless");
    }

    @BeforeEach
    public void setUp() {
        // Для каждого теста создаем новую страницу
        page = browserManager.createPage(ConfigurationManager.getBaseUrl());
        System.out.println("✅ New page created for test: " + this.getClass().getSimpleName());
    }

    @AfterEach
    public void tearDown() {
        // Проверяем, упал ли тест
        boolean hasFailed = false; // Нужно получить реальный статус теста

        if (ConfigurationManager.isVideoEnabled() && hasFailed) {
            browserManager.takeScreenshot(this.getClass().getSimpleName() + "_failed");
        }
        // Закрываем страницу после каждого теста
        browserManager.closePage();
    }

    @AfterAll
    public void afterAll() {
        // Закрываем браузер после всех тестов
        browserManager.close();
    }
}
