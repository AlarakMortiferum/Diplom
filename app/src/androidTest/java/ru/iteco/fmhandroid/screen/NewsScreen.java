package ru.iteco.fmhandroid.screen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.contrib.RecyclerViewActions;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.helper.ViewActionsExt;

public class NewsScreen {

    public NewsScreen assertOpened() {
        Allure.step("Проверка, что открыт экран News");
        onView(androidx.test.espresso.matcher.ViewMatchers.isRoot())
                .perform(ViewActionsExt.waitDisplayed(R.id.news_list_swipe_refresh, 5000));
        onView(withId(R.id.news_list_swipe_refresh)).check(matches(isDisplayed()));
        return this;
    }

    public NewsScreen openControlPanel() {
        Allure.step("Переход в Control panel");
        onView(withId(R.id.edit_news_material_button)).perform(click());
        return this;
    }

    public NewsScreen openFilter() {
        Allure.step("Открытие фильтра новостей");
        onView(withId(R.id.filter_news_material_button)).perform(click());
        return this;
    }

    public NewsScreen expandFirstNewsItem() {
        Allure.step("Раскрытие первой новости в списке");
        onView(withId(R.id.news_list_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0,
                        ViewActionsExt.clickChildViewWithId(R.id.view_news_item_image_view))
        );
        return this;
    }

    public NewsScreen assertNewsRecyclerDisplayed() {
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()));
        return this;
    }
}
