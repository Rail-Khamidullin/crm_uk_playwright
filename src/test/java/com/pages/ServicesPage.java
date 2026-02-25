package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class ServicesPage extends BasePage {

    public ServicesPage(Page page) {
        super(page);
    }

    @Override
    @Step("Страница 'Каталог услуг' отображается")
    public boolean isPageLoaded() {
        // Ждем появления кнопки (с таймаутом 10 секунд)
        try {
            // Ждем появления заголовка (10 секунд)
            page.locator("[aria-label='Каталог услуг']").waitFor(new Locator.WaitForOptions()
                    .setTimeout(10000));
            return true; // если исключения не было, элемент найден
        } catch (Exception e) {
            return false; // элемент не найден за 10 секунд
        }
    }
}
