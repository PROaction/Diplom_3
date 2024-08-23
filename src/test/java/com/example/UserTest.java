package com.example;


import com.example.models.responses.UserGetCreateResponseModel;
import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.example.pages.RecoverPasswordPage;
import com.example.pages.RegisterPage;
import com.example.steps.UserSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.stream.Stream;

import static com.example.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private WebDriver driver;
    private UserSteps userSteps;
    private UserApi userApi = new UserApi();


    static Stream<Object[]> buttonDataProvider() {
        return Stream.of(
                new Object[]{"https://stellarburgers.nomoreparties.site", "personalAccountButton"},
                new Object[]{"https://stellarburgers.nomoreparties.site", "loginButton"},
                new Object[]{"https://stellarburgers.nomoreparties.site/register", "loginLink"},
                new Object[]{"https://stellarburgers.nomoreparties.site/forgot-password", "loginLink"}
        );
    }

    @BeforeEach
    @Step("setUp")
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        userSteps = new UserSteps(driver);
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

    @Test
    public void testRegister() {
        driver.get(BASE_URL);

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        userSteps.navigateRegisterPage(homePage, loginPage);
        userSteps.registerUser(registerPage, loginPage, homePage, email, password, name);
        userSteps.loginUser(homePage, loginPage, email, password);
    }

    @Test
    public void testRegisterWithIncorrectPassword() {
        driver.get(BASE_URL);

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        userSteps.registerUserWithIncorrectPassword(registerPage, loginPage, homePage, email, "1234", name);
    }

    @ParameterizedTest
    @MethodSource("buttonDataProvider")
    @Step("Проверка перехода на страницу авторизации с {0} по кнопке {1}")
    public void testNavigationToLogin(String url, String buttonName) {
        driver.get(BASE_URL);

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        RecoverPasswordPage recoverPasswordPage = new RecoverPasswordPage(driver);

        userSteps.navigateRegisterPage(homePage, loginPage);
        userSteps.registerUser(registerPage, loginPage, homePage, email, password, name);

        By buttonLocator = null;
        switch (buttonName) {
            case "personalAccountButton":
                driver.get(url);
                buttonLocator = homePage.getPersonalAccountButton();
                break;
            case "loginButton":
                driver.get(url);
                buttonLocator = homePage.getLoginAccountButton();
                break;
            case "loginLink":
                if (url.contains("/register")) {
                    driver.get(url);
                    buttonLocator = registerPage.getLoginLink();
                }
                else if (url.contains("/forgot-password")) {
                    driver.get(url);
                    buttonLocator = recoverPasswordPage.getLoginLink();
                }
                break;
        }

        WebElement button = driver.findElement(buttonLocator);
        button.click();

        assertTrue(driver.getCurrentUrl().contains("/login"),
                "Не удалось перейти на страницу авторизации");

        userSteps.loginUser(homePage, loginPage, email, password);
    }
}
