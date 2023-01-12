package ru.netology.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.data.SQLHelper;
import ru.netology.pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class LoginTests {

    @BeforeEach
    public void openPage() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void cleanDataBase() {
        SQLHelper.cleanDatabase();
    }

    @Test
    void shouldLoginTestUser() {
        var loginPage = new LoginPage();
        var authInfo = DataGenerator.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyPage();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.verify(verificationCode.getCode());
    }

    @Test
    void shouldBeAnErrorIfTheUserIsNotInTheDatabase() {
        var loginPage = new LoginPage();
        var authInfo = DataGenerator.generateRandomUser();
        loginPage.validLogin(authInfo);
        loginPage.verifyErrorNotification();
    }

    @Test
    void shouldBeAnErrorIfUsingWrongVerificationCode() {
        var loginPage = new LoginPage();
        var authInfo = DataGenerator.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyPage();
        var verificationCode = DataGenerator.generateRandomVerificationCode();
        verificationPage.verify(verificationCode.getCode());
        verificationPage.verifyErrorNotification();
    }

}
