package ru.iteco.fmhandroid.screen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class TopBar {

    public TopBar openMainMenu() {
        Allure.step("Открытие главного меню");
        onView(withContentDescription(R.string.main_menu)).perform(click());
        return this;
    }

    public TopBar openAuthorizationMenu() {
        Allure.step("Открытие меню авторизации");
        onView(withContentDescription(R.string.authorization)).perform(click());
        return this;
    }

    public TopBar goToNews() {
        openMainMenu();
        Allure.step("Переход в раздел News из меню");
        onView(withText(R.string.news)).inRoot(isPlatformPopup()).perform(click());
        return this;
    }

    public TopBar goToMain() {
        openMainMenu();
        Allure.step("Переход в раздел Main из меню");
        onView(withText(R.string.main)).inRoot(isPlatformPopup()).perform(click());
        return this;
    }

    public TopBar goToAbout() {
        openMainMenu();
        Allure.step("Переход в раздел About из меню");
        onView(withText(R.string.about)).inRoot(isPlatformPopup()).perform(click());
        return this;
    }

    public TopBar goToOurMission() {
        Allure.step("Переход в раздел Our Mission по кнопке в верхней панели");
        onView(withContentDescription(R.string.our_mission)).perform(click());
        return this;
    }

    public TopBar logout() {
        openAuthorizationMenu();
        Allure.step("Выход из учетной записи");
        onView(withText(R.string.log_out)).inRoot(isPlatformPopup()).perform(click());
        return this;
    }

    public TopBar backFromAbout() {
        Allure.step("Возврат с экрана About назад");
        onView(withId(R.id.about_back_image_button)).perform(click());
        return this;
    }
}
