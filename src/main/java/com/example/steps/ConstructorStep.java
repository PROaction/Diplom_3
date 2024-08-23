package com.example.steps;


import com.example.pages.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class ConstructorStep {
    private final WebDriver driver;

    public ConstructorStep(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Активация вкладки")
    public void tabActivate(HomePage homePage, By tabLocator) {
        homePage.hasTitle();

        if (!homePage.isTabActive(tabLocator)){
            homePage.clickTab(tabLocator);
        }
        homePage.isTabActive(tabLocator);
    }
}
