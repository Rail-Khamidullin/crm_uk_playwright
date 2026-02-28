package com.tests;

import com.pages.LoginPage;
import com.pages.ServicesPage;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.data.InitialData.REGISTRATION_EMAIL;
import static com.data.InitialData.REGISTRATION_PASSWORD;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServicesTest extends BaseTest {

    @Test
    @DisplayName("Открытие блока 'Услуги'")
    @Description("Проверка отображение блока 'Услуги'")
    public void testServices() {
        // Создаем объект страницы авторизации
        LoginPage loginPage = new LoginPage(page);
        // Выполняем вход с тестовыми данными и выбираем блок 'Услуги'
        ServicesPage servicesPage = loginPage.login(
                REGISTRATION_EMAIL,
                REGISTRATION_PASSWORD
        ).tapToButtonMenu().tapToServices();

        // Проверяем отображение 'Каталог услуг' в блоке 'Услуги'
        assertTrue(servicesPage.isPageLoaded(),
                "Страница 'Каталог услуг' не загрузилась");
    }
}
