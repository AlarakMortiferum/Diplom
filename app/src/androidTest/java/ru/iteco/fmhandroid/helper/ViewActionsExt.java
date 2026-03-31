package ru.iteco.fmhandroid.helper;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matcher;

import java.util.concurrent.TimeoutException;

public final class ViewActionsExt {

    private ViewActionsExt() {
    }

    public static ViewAction waitDisplayed(@IdRes final int viewId, final long timeoutMs) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id " + viewId + " for " + timeoutMs + " ms";
            }

            @Override
            public void perform(UiController uiController, View view) {
                long endTime = System.currentTimeMillis() + timeoutMs;

                do {
                    View target = view.getRootView().findViewById(viewId);
                    if (target != null && target.isShown()) {
                        return;
                    }
                    uiController.loopMainThreadForAtLeast(50);
                } while (System.currentTimeMillis() < endTime);

                throw new PerformException.Builder()
                        .withActionDescription(getDescription())
                        .withViewDescription(view.toString())
                        .withCause(new TimeoutException("View with id " + viewId + " was not displayed"))
                        .build();
            }
        };
    }

    public static ViewAction clickChildViewWithId(@IdRes final int viewId) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(View.class);
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id: " + viewId;
            }

            @Override
            public void perform(UiController uiController, View view) {
                View target = view.findViewById(viewId);
                if (target == null) {
                    throw new PerformException.Builder()
                            .withActionDescription(getDescription())
                            .withViewDescription(view.toString())
                            .withCause(new NullPointerException("Child view with id " + viewId + " not found"))
                            .build();
                }
                target.performClick();
                uiController.loopMainThreadUntilIdle();
            }
        };
    }
}