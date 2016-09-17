package ru.jollydroid.mvp1;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;


import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by tse on 17/09/16.
 */
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class,
                    true,
                    false);

    @Inject
    public MainPresenter presenter;

    @Singleton
    @Component(modules = {
            AndroidModule.class,
            MockMainModule.class})
    public interface MockApplicationComponent extends ApplicationComponent {
        void inject(MainActivityTest test);
    }

    @Before
    public void setUp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();

        DemoApplication app
                = (DemoApplication) instrumentation.getTargetContext().getApplicationContext();

        MockApplicationComponent component = DaggerMainActivityTest_MockApplicationComponent
                .builder()
                .androidModule(new AndroidModule(app))
                .build();

        component.inject(this);

        app.setComponent(component);
    }

    @Test
    public void mainView_simpleTest() {
        activityTestRule.launchActivity(new Intent());

        onView(withId(R.id.content))
                .check(matches(hasDescendant(withText("Hello World!"))));
    }

    @Test
    public void mainView_clickButton() {
        activityTestRule.launchActivity(new Intent());

        // https://google.github.io/android-testing-support-library/docs/espresso/basics/
        onView(withId(R.id.fab)).perform(click());

        // http://www.baeldung.com/mockito-verify
        verify(presenter, times(1)).buttonPressed();
    }

    @Test
    public void mainView_snackbarDisplayed() {
        MainView view = activityTestRule.launchActivity(new Intent());

        int n = 123;

        view.showCounter(n);

        onView(withId(android.support.design.R.id.snackbar_text))
                .check(matches(withEffectiveVisibility(VISIBLE)));

        onView(
                allOf(
                        withId(android.support.design.R.id.snackbar_text),
                        withText(containsString("" + n)))
        ).check(matches(withEffectiveVisibility(VISIBLE)));
    }
}