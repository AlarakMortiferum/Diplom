package ru.iteco.fmhandroid.screen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class CreateEditNewsScreen {

    public CreateEditNewsScreen assertOpened() {
        Allure.step("Проверка, что открыт экран создания или редактирования новости");
        onView(withId(R.id.save_button)).check(matches(isDisplayed()));
        return this;
    }

    public CreateEditNewsScreen selectCategory(String category) {
        Allure.step("Выбор категории: " + category);
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(scrollTo(), click());
        onView(withText(category)).inRoot(isPlatformPopup()).perform(click());
        return this;
    }

    public CreateEditNewsScreen enterTitle(String title) {
        Allure.step("Ввод заголовка новости: " + title);
        onView(withId(R.id.news_item_title_text_input_edit_text))
                .perform(scrollTo(), replaceText(title), closeSoftKeyboard());
        return this;
    }

    public CreateEditNewsScreen chooseCurrentDate() {
        Allure.step("Выбор текущей даты публикации");
        onView(withId(R.id.news_item_publish_date_text_input_edit_text)).perform(scrollTo(), click());
        onView(withText(R.string.fragment_positive_button)).perform(click());
        return this;
    }

    public CreateEditNewsScreen chooseCurrentTime() {
        Allure.step("Выбор текущего времени публикации");
        onView(withId(R.id.news_item_publish_time_text_input_edit_text)).perform(scrollTo(), click());
        onView(withText(R.string.fragment_positive_button)).perform(click());
        return this;
    }

    public CreateEditNewsScreen enterDescription(String description) {
        Allure.step("Ввод описания новости");
        onView(withId(R.id.news_item_description_text_input_edit_text))
                .perform(scrollTo(), replaceText(description), closeSoftKeyboard());
        return this;
    }

    public CreateEditNewsScreen save() {
        Allure.step("Сохранение новости");
        onView(withId(R.id.save_button)).perform(scrollTo(), click());
        return this;
    }

    public CreateEditNewsScreen cancel() {
        Allure.step("Отмена создания или редактирования новости");
        onView(withId(R.id.cancel_button)).perform(scrollTo(), click());
        return this;
    }
}
