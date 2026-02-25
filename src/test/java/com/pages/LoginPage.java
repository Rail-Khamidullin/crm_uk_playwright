package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.pages.headerPage.HeaderPage;
import com.utils.BasePageFactory;
import io.qameta.allure.Step;

public class LoginPage extends BasePage {

    // Локаторы (инициализируем в конструкторе)
    private final Locator loginField;
    private final Locator passwordField;
    private final Locator enterButton;

    public LoginPage(Page page) {
        super(page);
        // Инициализация локаторов после получения page
        this.loginField = page.locator("[type='email']");
        this.passwordField = page.getByLabel("Пароль");
        this.enterButton = page.getByText("Войти");
    }

    // Комбинированный метод для полной авторизации
    @Step("Авторизация с логином: {login}")
    public HeaderPage login(String login, String password) {
        enterLogin(login);
        enterPassword(password);
        return BasePageFactory.openPage(enterButton, page, HeaderPage.class);
    }

    @Step("Ввод логина: {login}")
    public void enterLogin(String login) {
        loginField.fill(login);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        passwordField.fill(password);
    }

    @Step("Проверка отображения страницы авторизации")
    @Override
    public boolean isPageLoaded() {
        // Проверяем, что страница авторизации загружена
        return loginField.isVisible() && enterButton.isVisible();
    }
}
