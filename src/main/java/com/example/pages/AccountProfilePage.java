package com.example.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.example.utils.Constants.BASE_WAIT;
import static com.example.utils.Waiters.waitForVisibilityOfElementLocated;

public class AccountProfilePage extends BasePage {

    private final By profileLink = By.xpath(".//a[text()='Профиль']");
    private final By nameField = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By emailField = By.xpath(".//label[text()='Логин']/following-sibling::input");
    private final By passwordField = By.xpath(".//label[text()='Пароль']/following-sibling::input");


    public AccountProfilePage(WebDriver driver) {
        super(driver);
    }

    public String getNameValue() {
        return driver.findElement(nameField).getAttribute("value");
    }

    public String getEmailValue() {
        return driver.findElement(emailField).getAttribute("value");
    }

    public String getPasswordValue() {
        return driver.findElement(passwordField).getAttribute("value");
    }

    public boolean hasProfileLink() {
        try {
            WebElement profileLinkElement = waitForVisibilityOfElementLocated(driver, profileLink, BASE_WAIT);
            return profileLinkElement != null;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
