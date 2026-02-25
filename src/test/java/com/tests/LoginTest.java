package com.tests;

import com.pages.LoginPage;
import com.pages.headerPage.HeaderPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.data.InitialData.REGISTRATION_EMAIL;
import static com.data.InitialData.REGISTRATION_PASSWORD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Проверка успешной авторизации и отображения кнопки")
    void testSuccessfulLogin() {
        // Создаем объект страницы авторизации
        LoginPage loginPage = new LoginPage(page);

        // Проверяем, что страница авторизации загружена
        assertTrue(loginPage.isPageLoaded(),
                "Страница авторизации не загрузилась");

        // Выполняем вход с тестовыми данными
        HeaderPage headerPage = loginPage.login(
                REGISTRATION_EMAIL,
                REGISTRATION_PASSWORD
        );

        // Проверяем, что главная страница загрузилась
        assertTrue(headerPage.isPageLoaded(),
                "Главная страница не загрузилась после авторизации");

        // Проверяем наличие кнопки "Взаимоотношения с клиентами"
        assertTrue(headerPage.isClientRelationButtonVisible(),
                "Кнопка 'Взаимоотношения с клиентами' не отображается на странице");

        // Проверяем текст кнопки
        String buttonText = headerPage.getClientRelationButtonText();
        assertEquals("Взаимоотношения с клиентами", buttonText,
                "Текст кнопки не соответствует ожидаемому");
    }
}
