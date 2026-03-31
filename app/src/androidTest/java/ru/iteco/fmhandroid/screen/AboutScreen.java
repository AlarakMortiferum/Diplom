package ru.iteco.fmhandroid.screen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.helper.ViewActionsExt;

public class AboutScreen {

    public AboutScreen assertOpened() {
        Allure.step("Проверка, что открыт экран About");
        onView(androidx.test.espresso.matcher.ViewMatchers.isRoot())
                .perform(ViewActionsExt.waitDisplayed(R.id.about_version_value_text_view, 5000));
        onView(withId(R.id.about_version_value_text_view)).check(matches(isDisplayed()));
        onView(withId(R.id.about_privacy_policy_value_text_view)).check(matches(isDisplayed()));
        onView(withId(R.id.about_terms_of_use_value_text_view)).check(matches(isDisplayed()));
        return this;
    }

    public AboutScreen assertVersion(String version) {
        Allure.step("Проверка версии приложения: " + version);
        onView(withId(R.id.about_version_value_text_view)).check(matches(withText(version)));
        return this;
    }
}
