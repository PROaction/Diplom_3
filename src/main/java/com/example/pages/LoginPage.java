package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.example.utils.Constants.BASE_WAIT;
import static com.example.utils.Waiters.waitForVisibilityOfElementLocated;

public class LoginPage {
    private final WebDriver driver;

    private final By emailField = By.xpath(".//label[text()='Email']//following-sibling::input");
    private final By passwordField = By.xpath(".//label[text()='Пароль']//following-sibling::input");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By registrationLink = By.xpath(".//a[text()='Зарегистрироваться']");
    private final By title = By.xpath(".//h2[text()='Вход']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickRegistrationLink() {
        driver.findElement(registrationLink).click();
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
