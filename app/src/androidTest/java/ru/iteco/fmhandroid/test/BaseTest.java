package ru.iteco.fmhandroid.test;

import androidx.test.core.app.ActivityScenario;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.Until;

import org.junit.After;
import org.junit.Before;

import ru.iteco.fmhandroid.data.TestData;
import ru.iteco.fmhandroid.screen.AuthScreen;
import ru.iteco.fmhandroid.screen.TopBar;
import ru.iteco.fmhandroid.ui.AppActivity;

public abstract class BaseTest {

    private static final String APP_PACKAGE = "ru.iteco.fmhandroid";
    private static final long START_STATE_TIMEOUT = 7000;
    private static final long AUTHORIZED_STATE_TIMEOUT = 7000;

    protected ActivityScenario<AppActivity> scenario;
    protected UiDevice device;
    protected final AuthScreen authScreen = new AuthScreen();
    protected final TopBar topBar = new TopBar();

    @Before
    public void baseSetUp() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        scenario = ActivityScenario.launch(AppActivity.class);
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
        device.waitForIdle();
    }

    @After
    public void baseTearDown() {
        if (scenario != null) {
            scenario.close();
        }
    }

    protected void ensureLoggedOut() {
        waitForStableStartState();

        if (isAuthorizedAreaVisible()) {
            topBar.logout();
            waitUntilAuthScreenVisible();
            authScreen.waitUntilOpened();
            return;
        }

        authScreen.waitUntilOpened();
    }

    protected void ensureAuthorized() {
        waitForStableStartState();

        if (isAuthorizedAreaVisible()) {
            return;
        }

        authScreen.waitUntilOpened()
                .login(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);

        waitUntilAuthorizedAreaVisible();
        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
        device.waitForIdle();
    }

    private void waitForStableStartState() {
        long end = System.currentTimeMillis() + START_STATE_TIMEOUT;

        while (System.currentTimeMillis() < end) {
            if (isAuthScreenVisible() || isAuthorizedAreaVisible()) {
                return;
            }
            device.waitForIdle();
        }
    }

    private void waitUntilAuthScreenVisible() {
        device.wait(Until.hasObject(By.res(APP_PACKAGE, "enter_button")), START_STATE_TIMEOUT);
    }

    private void waitUntilAuthorizedAreaVisible() {
        device.wait(Until.hasObject(By.res(APP_PACKAGE, "main_menu_image_button")), AUTHORIZED_STATE_TIMEOUT);
    }

    private boolean isAuthScreenVisible() {
        return device.hasObject(By.res(APP_PACKAGE, "enter_button"));
    }

    private boolean isAuthorizedAreaVisible() {
        return device.hasObject(By.res(APP_PACKAGE, "main_menu_image_button"));
    }
}