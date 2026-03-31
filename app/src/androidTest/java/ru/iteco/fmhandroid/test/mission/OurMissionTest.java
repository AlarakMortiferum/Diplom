package ru.iteco.fmhandroid.test.mission;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.screen.OurMissionScreen;
import ru.iteco.fmhandroid.test.BaseTest;

@RunWith(AllureAndroidJUnit4.class)
@Epic("Мобильное приложение Мобильный хоспис")
@Feature("Цитаты / Our Mission")
public class OurMissionTest extends BaseTest {

    private final OurMissionScreen ourMissionScreen = new OurMissionScreen();

    @Before
    public void prepareAuthorizedState() {
        ensureAuthorized();
    }

    @Test
    @DisplayName("Авторизованный пользователь может открыть раздел Our Mission и раскрыть первую цитату")
    @Description("После открытия раздела Our Mission первая карточка цитаты раскрывается и отображается её описание")
    public void authorizedUserCanOpenOurMissionAndExpandQuote() {
        ourMissionScreen.openScreen()
                .assertOpened()
                .expandFirstQuote()
                .assertFirstQuoteDescriptionVisible();
    }
}