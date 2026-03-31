package ru.iteco.fmhandroid.test.news;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.data.TestData;
import ru.iteco.fmhandroid.screen.ControlPanelScreen;
import ru.iteco.fmhandroid.screen.CreateEditNewsScreen;
import ru.iteco.fmhandroid.screen.NewsScreen;
import ru.iteco.fmhandroid.test.BaseTest;

@RunWith(AllureAndroidJUnit4.class)
@Epic("Мобильное приложение Мобильный хоспис")
@Feature("Работа с новостями")
public class NewsCrudTest extends BaseTest {

    private final NewsScreen newsScreen = new NewsScreen();
    private final ControlPanelScreen controlPanelScreen = new ControlPanelScreen();
    private final CreateEditNewsScreen createEditNewsScreen = new CreateEditNewsScreen();

    @Test
    @DisplayName("Открытие раздела News и раскрытие новости")
    @Description("Авторизованный пользователь открывает раздел News и раскрывает первую карточку новости")
    public void authorizedUserCanOpenNewsAndExpandFirstItem() {
        ensureAuthorized();

        topBar.goToNews();
        newsScreen.assertOpened()
                .assertNewsRecyclerDisplayed()
                .expandFirstNewsItem();
    }

    @Test
    @DisplayName("Создание новости с валидными данными")
    @Description("Авторизованный пользователь открывает Control panel, создает новость и возвращается к списку новостей")
    public void createNewsWithValidDataShouldReturnToControlPanel() {
        ensureAuthorized();

        topBar.goToNews();
        newsScreen.assertOpened().openControlPanel();
        controlPanelScreen.assertOpened().clickAddNews();

        createEditNewsScreen.assertOpened()
                .selectCategory(TestData.NEWS_CATEGORY_ADVERTISEMENT)
                .enterTitle(TestData.uniqueTitle("АвтоНовость"))
                .chooseCurrentDate()
                .chooseCurrentTime()
                .enterDescription(TestData.uniqueDescription("АвтоОписание"))
                .save();

        controlPanelScreen.assertOpened();
    }

    @Test
    @DisplayName("Редактирование валидной новости")
    @Description("Авторизованный пользователь открывает Control panel, редактирует валидную новость и возвращается к списку")
    public void editValidNewsShouldReturnToControlPanel() {
        ensureAuthorized();

        topBar.goToNews();
        newsScreen.assertOpened().openControlPanel();
        controlPanelScreen.assertOpened().clickEditNewsAtPosition(1);

        createEditNewsScreen.assertOpened()
                .enterTitle(TestData.uniqueTitle("ИзмененнаяНовость"))
                .save();

        controlPanelScreen.assertOpened();
    }
}