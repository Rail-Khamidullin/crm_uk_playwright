package com.pages.headerPage;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.pages.BasePage;
import com.pages.ServicesPage;
import com.utils.BasePageFactory;
import io.qameta.allure.Step;

public class HeaderPage extends BasePage {

    // Локатор для кнопки "Взаимоотношения с клиентами"
    private final Locator clientRelationButton;

    public HeaderPage(Page page) {
        super(page);
        this.clientRelationButton = page.getByText("Взаимоотношения с клиентами");;
    }

    @Step("Проверка отображения кнопки 'Взаимоотношения с клиентами'")
    public boolean isClientRelationButtonVisible() {
        return clientRelationButton.isVisible();
    }

    @Step("Получение текста кнопки 'Взаимоотношения с клиентами'")
    public String getClientRelationButtonText() {
        return clientRelationButton.textContent();
    }

    @Override
    public boolean isPageLoaded() {
        // Ждем появления кнопки (с таймаутом 10 секунд)
        try {
            // Ждем появления кнопки (10 секунд)
            clientRelationButton.waitFor(new Locator.WaitForOptions()
                    .setTimeout(10000));
            return true; // если исключения не было, элемент найден
        } catch (Exception e) {
            return false; // элемент не найден за 10 секунд
        }
    }

    @Step("Выбираем кнопку 'Меню'")
    public HeaderPage tapToButtonMenu() {
        page.locator("[aria-label='Меню']").click();
        return this;
    }

    @Step("Выбираем кнопку 'Услуги'")
    public ServicesPage tapToServices() {
        Locator services = page.locator("xpath=//li[@role='menuitem']//p[text()='Услуги']");
        return BasePageFactory.openPage(services, page, ServicesPage.class);
    }
}
