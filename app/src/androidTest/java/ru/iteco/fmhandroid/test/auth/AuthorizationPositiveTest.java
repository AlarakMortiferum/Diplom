package ru.iteco.fmhandroid.test.auth;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.data.TestData;
import ru.iteco.fmhandroid.screen.MainScreen;
import ru.iteco.fmhandroid.test.BaseTest;

@RunWith(AllureAndroidJUnit4.class)
@Epic("Мобильное приложение Мобильный хоспис")
@Feature("Авторизация")
public class AuthorizationPositiveTest extends BaseTest {

    private final MainScreen mainScreen = new MainScreen();

    @Test
    @DisplayName("Успешная авторизация")
    @Description("Пользователь с валидными данными может войти в приложение")
    public void successfulAuthorizationShouldOpenMainScreen() {
        ensureLoggedOut();

        authScreen.enterLogin(TestData.VALID_LOGIN)
                .enterPassword(TestData.VALID_PASSWORD)
                .clickSignIn();

        mainScreen.assertOpened();
    }
}
