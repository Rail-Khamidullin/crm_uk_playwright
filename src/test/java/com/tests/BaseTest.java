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
    }

    @BeforeEach
    public void setUp() {
        // Для каждого теста создаем новую страницу
        page = browserManager.createPage(ConfigurationManager.getBaseUrl());
        System.out.println("✅ New page created for test: " + getTestName());
    }

    @AfterEach
    public void tearDown() {
        // Сохраняем скриншот если тест упал
        if (ConfigurationManager.isVideoEnabled() && hasTestFailed()) {
            browserManager.takeScreenshot(getTestName() + "_failed");
        }
        // Закрываем страницу после каждого теста
        browserManager.closePage();
    }

    @AfterAll
    public void afterAll() {
        // Закрываем браузер после всех тестов
        browserManager.close();
    }

    // Вспомогательные методы
    private String getTestName() {
        // Получаем имя текущего теста
        return this.getClass().getSimpleName();
    }

    private boolean hasTestFailed() {
        // Проверка, упал ли тест
        return false; // Заглушка
    }
}
