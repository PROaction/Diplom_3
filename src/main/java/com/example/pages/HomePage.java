package com.example.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.nio.file.WatchEvent;

import static com.example.utils.Constants.BASE_WAIT;
import static com.example.utils.Waiters.waitForVisibilityOfElementLocated;

public class HomePage {
    private final WebDriver driver;

    private final By personalAccountButton = By.xpath(".//p[text()=\"Личный Кабинет\"]");
    private final By title = By.xpath(".//h1[text()='Соберите бургер']");
    private final By loginAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickPersonalAccount() {
        driver.findElement(personalAccountButton).click();
    }

    public void clickLoginAccountButton() {
        driver.findElement(loginAccountButton).click();
    }

    public By getLoginAccountButton() {
        return loginAccountButton;
    }

    public By getPersonalAccountButton() {
        return personalAccountButton;
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

    public boolean hasTitle() {
        try {
            WebElement titleElement = waitForVisibilityOfElementLocated(driver, title, BASE_WAIT);
            return titleElement != null;
        } catch (TimeoutException e) {
            return false;
        }

    }
}
