package ru.iteco.fmhandroid.test.auth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.data.TestData;
import ru.iteco.fmhandroid.test.BaseTest;

@RunWith(AllureAndroidJUnit4.class)
@Epic("Мобильное приложение Мобильный хоспис")
@Feature("Авторизация")
public class AuthorizationNegativeTest extends BaseTest {

    @Before
    public void prepareLoggedOutState() {
        ensureLoggedOut();
    }

    @Test
    @DisplayName("Авторизация с пустыми полями")
    @Description("При попытке войти с пустыми полями отображается сообщение об ошибке")
    public void emptyCredentialsShouldShowValidationToast() {
        authScreen.clickSignIn()
                .assertEmptyCredentialsToast();
    }

    @Test
    @DisplayName("Авторизация с пустым логином")
    @Description("При пустом логине и заполненном пароле отображается сообщение о незаполненных обязательных полях")
    public void emptyLoginShouldShowValidationToast() {
        authScreen.enterPassword(TestData.VALID_PASSWORD)
                .clickSignIn()
                .assertEmptyCredentialsToast();
    }

    @Test
    @DisplayName("Авторизация с пустым паролем")
    @Description("При заполненном логине и пустом пароле отображается сообщение о незаполненных обязательных полях")
    public void emptyPasswordShouldShowValidationToast() {
        authScreen.enterLogin(TestData.VALID_LOGIN)
                .clickSignIn()
                .assertEmptyCredentialsToast();
    }
}