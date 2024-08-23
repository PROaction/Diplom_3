package com.example.steps;

import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.example.pages.RegisterPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class UserSteps {
    private WebDriver driver;

    public UserSteps(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Переход на страницу регистрации")
    public void navigateRegisterPage(HomePage homePage, LoginPage loginPage) {
        // Переход на страницу Входа
        homePage.clickPersonalAccount();

        // Переход на страницу Регистрации
        loginPage.clickRegistrationLink();
    }
    private void performRegistration(RegisterPage registerPage,
                                     String email, String password, String name) {

        // Регистрация
        registerPage.setEmailField(email);
        registerPage.setPasswordField(password);
        registerPage.setNameField(name);
        registerPage.clickRegisterButton();
    }

    @Step("Успешная регистрация пользователя")
    public void registerUser(RegisterPage registerPage, LoginPage loginPage, HomePage homePage,
                             String email, String password, String name) {
        performRegistration(registerPage, email, password, name);

        assertTrue(loginPage.hasTitle(), "Ошибка регистрации");
    }

    @Step("Регистрация пользователя с некорректным паролем")
    public void registerUserWithIncorrectPassword(RegisterPage registerPage, LoginPage loginPage, HomePage homePage,
                                                  String email, String password, String name) {
        performRegistration(registerPage, email, password, name);

        assertTrue(registerPage.hasIncorrectPasswordText(),
                "Ожидаемое сообщение о некорректном пароле отсутствует.");
    }

    @Step("Переход на страницу авторизации")
    public void navigateLoginPage(HomePage homePage) {
        homePage.clickLoginAccountButton();
    }

    @Step("Авторизация пользователя")
    public void loginUser(HomePage homePage, LoginPage loginPage, String email, String password) {
        loginPage.hasTitle();
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        assertTrue(homePage.hasCreateOrderButton(), "Ошибка входа");
    }
}
