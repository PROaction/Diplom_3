package com.example.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoverPasswordPage {
    private WebDriver driver;

    private final By loginLink = By.xpath(".//a[text()='Войти']");

    public RecoverPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    public By getLoginLink() {
        return loginLink;
    }
}
