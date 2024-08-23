package com.example;


import com.example.models.responses.UserGetCreateResponseModel;
import com.example.pages.HomePage;
import com.example.steps.ConstructorStep;
import com.example.steps.UserSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.stream.Stream;

import static com.example.utils.Constants.*;
import static com.example.utils.Constants.BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstructorTest {
    private WebDriver driver;
    private UserApi userApi = new UserApi();
    private ConstructorStep constructorSteps;

    @BeforeEach
    @Step("setUp")
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        constructorSteps = new ConstructorStep(driver);
    }

    @AfterEach
    @Step("tearDown")
    public void tearDown() {
        driver.quit();

        Response response = userApi.getUser(email, password);

        if (response.getStatusCode() == 200) {
            UserGetCreateResponseModel userGetResponse = response.as(UserGetCreateResponseModel.class);

            if (userGetResponse.isSuccess()) {
                Response deleteResponse = userApi.deleteUser(userGetResponse.getAccessToken());
                assertEquals(202, deleteResponse.getStatusCode());
            }
        }
    }


    @ParameterizedTest
    @CsvSource({
            "bun",
            "sauce",
            "filling"
    })
    public void testTabActivate(String tabName) {
        Response response = userApi.createUser(email, password, name);
        assertEquals(200, response.statusCode());

        driver.get(BASE_URL);

        HomePage homePage = new HomePage(driver);

        By tabLocator = null;
        switch (tabName) {
            case "bun":
                tabLocator = homePage.getBunTab();
                break;
            case "sauce":
                tabLocator = homePage.getSauceTab();
                break;
            case "filling":
                tabLocator = homePage.getFillingTab();
                break;
        }

        constructorSteps.tabActivate(homePage, tabLocator);
    }
}
