package ru.iteco.fmhandroid.screen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.helper.ViewActionsExt;

public class MainScreen {

    public MainScreen assertOpened() {
        Allure.step("Проверка, что открыт главный экран");
        onView(isRoot())
                .perform(ViewActionsExt.waitDisplayed(R.id.main_swipe_refresh, 5000));
        onView(withId(R.id.main_swipe_refresh)).check(matches(isDisplayed()));
        onView(withId(R.id.container_list_news_include_on_fragment_main)).check(matches(isDisplayed()));
        return this;
    }
}