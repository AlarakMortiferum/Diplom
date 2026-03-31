package ru.iteco.fmhandroid.screen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.contrib.RecyclerViewActions;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.helper.ViewActionsExt;

public class ControlPanelScreen {

    public ControlPanelScreen assertOpened() {
        Allure.step("Проверка, что открыт экран Control panel");
        onView(isRoot()).perform(ViewActionsExt.waitDisplayed(R.id.add_news_image_view, 5000));
        onView(withId(R.id.add_news_image_view)).check(matches(isDisplayed()));
        return this;
    }

    public ControlPanelScreen clickAddNews() {
        Allure.step("Нажатие на кнопку добавления новости");
        onView(withId(R.id.add_news_image_view)).perform(click());
        return this;
    }

    public ControlPanelScreen clickEditNewsAtPosition(int position) {
        Allure.step("Нажатие на редактирование новости в позиции: " + position);
        onView(withId(R.id.news_list_recycler_view)).perform(
                RecyclerViewActions.actionOnItemAtPosition(
                        position,
                        ViewActionsExt.clickChildViewWithId(R.id.edit_news_item_image_view)
                )
        );
        return this;
    }
}