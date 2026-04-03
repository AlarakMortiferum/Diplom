package ru.iteco.fmhandroid.test.news;

import org.junit.Before;
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

    @Before
    public void prepareAuthorizedState() {
        ensureAuthorized();
    }

    @Test
    @DisplayName("Открытие раздела News и раскрытие новости")
    @Description("Авторизованный пользователь открывает раздел News и раскрывает первую карточку новости")
    public void authorizedUserCanOpenNewsAndExpandFirstItem() {
        topBar.goToNews();
        newsScreen.assertOpened()
                .assertNewsRecyclerDisplayed()
                .expandFirstNewsItem();
    }

    @Test
    @DisplayName("Создание новости с валидными данными")
    @Description("Авторизованный пользователь создает новость и проверяет, что она появилась в списке")
    public void createNewsWithValidDataShouldCreateNewsInList() {
        String createdTitle = TestData.uniqueTitle(TestData.CREATE_NEWS_TITLE_PREFIX);
        String createdDescription = TestData.uniqueDescription(TestData.CREATE_NEWS_DESCRIPTION_PREFIX);

        topBar.goToNews();
        newsScreen.assertOpened().openControlPanel();
        controlPanelScreen.assertOpened().clickAddNews();

        createEditNewsScreen.assertOpened()
                .selectCategory(TestData.NEWS_CATEGORY_ADVERTISEMENT)
                .enterTitle(createdTitle)
                .chooseCurrentDate()
                .chooseCurrentTime()
                .enterDescription(createdDescription)
                .save();

        controlPanelScreen.assertOpened()
                .assertNewsWithTitleDisplayed(createdTitle);
    }

    @Test
    @DisplayName("Редактирование валидной новости")
    @Description("Авторизованный пользователь редактирует новость и проверяет, что измененный заголовок отображается в списке")
    public void editValidNewsShouldChangeTitleInList() {
        String editedTitle = TestData.uniqueTitle(TestData.EDIT_NEWS_TITLE_PREFIX);

        topBar.goToNews();
        newsScreen.assertOpened().openControlPanel();
        controlPanelScreen.assertOpened().clickEditNewsAtPosition(1);

        createEditNewsScreen.assertOpened()
                .enterTitle(editedTitle)
                .save();

        controlPanelScreen.assertOpened()
                .assertNewsWithTitleDisplayed(editedTitle);
    }
}