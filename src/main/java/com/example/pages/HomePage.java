package com.example.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.nio.file.WatchEvent;

import static com.example.utils.Constants.BASE_WAIT;
import static com.example.utils.Waiters.waitForVisibilityOfElementLocated;

public class HomePage extends BasePage {

    private final By title = By.xpath(".//h1[text()='Соберите бургер']");
    private final By loginAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By bunTab = By.xpath(".//span[text()='Булки']/parent::div");
    private final By sauceTab = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By fillingTab = By.xpath(".//span[text()='Начинки']/parent::div");


    public HomePage(WebDriver driver) {
        super(driver);
    }


    public void clickLoginAccountButton() {
        driver.findElement(loginAccountButton).click();
    }

    public By getLoginAccountButton() {
        return loginAccountButton;
    }

    public void clickCreateOrderButton() {
        driver.findElement(createOrderButton).click();
    }

    public boolean hasCreateOrderButton() {
        try {
            WebElement createOrderButtonElement =
                    waitForVisibilityOfElementLocated(driver, createOrderButton, BASE_WAIT);
            return createOrderButtonElement != null;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickButTab() {
        driver.findElement(bunTab).click();
    }

    public void clickSauceTab() {
        driver.findElement(sauceTab).click();
    }

    public void clickFillingTab() {
        driver.findElement(fillingTab).click();
    }

    public void clickTab(By tabLocator) {
        driver.findElement(tabLocator).click();
    }

    public By getBunTab() {
        return bunTab;
    }

    public By getSauceTab() {
        return sauceTab;
    }

    public By getFillingTab() {
        return fillingTab;
    }

    public boolean isTabActive(By tabLocator) {
        WebElement tabElement = driver.findElement(tabLocator);
        String classAttribute = tabElement.getAttribute("class");
        return classAttribute.contains("tab_tab_type_current");
    }

    public boolean hasTitle() {
        try {
            WebElement titleElement = waitForVisibilityOfElementLocated(driver, title, BASE_WAIT);
            return titleElement != null;
        } catch (TimeoutException e) {
            return false;
        }

    }
}
