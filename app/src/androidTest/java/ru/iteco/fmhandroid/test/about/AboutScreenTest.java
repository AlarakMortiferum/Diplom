package ru.iteco.fmhandroid.test.about;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.screen.AboutScreen;
import ru.iteco.fmhandroid.test.BaseTest;

@RunWith(AllureAndroidJUnit4.class)
@Epic("Мобильное приложение Мобильный хоспис")
@Feature("О приложении")
public class AboutScreenTest extends BaseTest {

    private final AboutScreen aboutScreen = new AboutScreen();

    @Before
    public void prepareAuthorizedState() {
        ensureAuthorized();
    }

    @Test
    @DisplayName("Авторизованный пользователь может открыть экран About")
    @Description("После авторизации пользователь может открыть экран About")
    public void authorizedUserCanOpenAboutScreen() {
        topBar.goToAbout();
        aboutScreen.assertOpened();
    }
}