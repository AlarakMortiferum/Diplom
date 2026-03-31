package ru.iteco.fmhandroid.screen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.contrib.RecyclerViewActions;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.helper.ViewActionsExt;

public class OurMissionScreen {

    public OurMissionScreen openScreen() {
        Allure.step("Открытие экрана Our Mission");
        onView(withId(R.id.our_mission_image_button)).perform(click());
        return this;
    }

    public OurMissionScreen assertOpened() {
        Allure.step("Проверка, что открыт экран Our Mission");
        onView(isRoot()).perform(ViewActionsExt.waitDisplayed(R.id.our_mission_item_list_recycler_view, 5000));
        onView(withId(R.id.our_mission_item_list_recycler_view)).check(matches(isDisplayed()));
        return this;
    }

    public OurMissionScreen expandFirstQuote() {
        Allure.step("Раскрытие первой цитаты");
        onView(withId(R.id.our_mission_item_list_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        return this;
    }

    public OurMissionScreen assertFirstQuoteDescriptionVisible() {
        Allure.step("Проверка отображения описания раскрытой цитаты");
        onView(allOf(
                withId(R.id.our_mission_item_description_text_view),
                isDisplayed()
        )).check(matches(isDisplayed()));
        return this;
    }
}