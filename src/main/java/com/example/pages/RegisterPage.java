package com.example.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.example.utils.Constants.BASE_WAIT;
import static com.example.utils.Waiters.waitForVisibilityOfElementLocated;

public class RegisterPage extends BasePage {

    private final By nameField = By.xpath(".//label[text()='Имя']//following-sibling::input");
    private final By emailField = By.xpath(".//label[text()='Email']//following-sibling::input");
    private final By passwordField = By.xpath(".//label[text()='Пароль']//following-sibling::input");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By incorrectPasswordText = By.xpath(".//p[text()='Некорректный пароль']");
    private final By loginLink = By.xpath(".//a[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void setNameField(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    public By getLoginLink() {
        return loginLink;
    }

    public boolean hasIncorrectPasswordText() {
        try {
            WebElement incorrectPasswordTextElement =
                    waitForVisibilityOfElementLocated(driver, incorrectPasswordText, BASE_WAIT);
            return incorrectPasswordTextElement != null;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
