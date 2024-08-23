package com.example.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;

    By homeLink = By.className("AppHeader_header__logo__2D0X2");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickHomePage() {
        driver.findElement(homeLink).click();
    }
}
