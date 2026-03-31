package ru.iteco.fmhandroid.screen;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.helper.ToastMatcher;
import ru.iteco.fmhandroid.helper.ViewActionsExt;
import io.qameta.allure.kotlin.Allure;

public class AuthScreen {

    public AuthScreen waitUntilOpened() {
        Allure.step("Ожидание экрана авторизации");
        onView(androidx.test.espresso.matcher.ViewMatchers.isRoot())
                .perform(ViewActionsExt.waitDisplayed(R.id.enter_button, 5000));
        onView(withId(R.id.enter_button)).check(matches(isDisplayed()));
        return this;
    }

    public AuthScreen enterLogin(String login) {
        Allure.step("Ввод логина: " + login);
        onView(org.hamcrest.Matchers.allOf(
                isDescendantOfA(withId(R.id.login_text_input_layout)),
                org.hamcrest.Matchers.instanceOf(android.widget.EditText.class)
        )).perform(replaceText(login), closeSoftKeyboard());
        return this;
    }

    public AuthScreen enterPassword(String password) {
        Allure.step("Ввод пароля");
        onView(org.hamcrest.Matchers.allOf(
                isDescendantOfA(withId(R.id.password_text_input_layout)),
                org.hamcrest.Matchers.instanceOf(android.widget.EditText.class)
        )).perform(replaceText(password), closeSoftKeyboard());
        return this;
    }

    public AuthScreen clickSignIn() {
        Allure.step("Нажатие на кнопку входа");
        onView(withId(R.id.enter_button)).perform(click());
        return this;
    }

    public AuthScreen login(String login, String password) {
        return enterLogin(login)
                .enterPassword(password)
                .clickSignIn();
    }

    public AuthScreen assertEmptyCredentialsToast() {
        Allure.step("Проверка toast о пустом логине или пароле");
        onView(withText(R.string.empty_login_or_password))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
        return this;
    }

    public AuthScreen assertWrongCredentialsToast() {
        Allure.step("Проверка toast о неверном логине или пароле");
        onView(withText(R.string.wrong_login_or_password))
                .inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
        return this;
    }
}
