package com.example.androiduitesting;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import android.content.Intent;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowActivityTest {

    @Rule
    public ActivityScenarioRule<ShowActivity> activityRule =
            new ActivityScenarioRule<>(new Intent(getApplicationContext(), ShowActivity.class)
                    .putExtra("city_name", "Edmonton"));

    @Test
    public void testActivityLaunchesSuccessfully() {
        // If the activity launches without crashing, content_view should be visible
        onView(org.hamcrest.Matchers.allOf(
                androidx.test.espresso.matcher.ViewMatchers.withId(R.id.content_view),
                isDisplayed()
        )).check(matches(isDisplayed()));
    }

    @Test
    public void testCityNameDisplayedCorrectly() {
        onView(androidx.test.espresso.matcher.ViewMatchers.withId(R.id.content_view))
                .check(matches(androidx.test.espresso.matcher.ViewMatchers.withText("Edmonton")));
    }

    @Test
    public void testBackButtonClosesActivity() {
        onView(androidx.test.espresso.matcher.ViewMatchers.withId(R.id.back_button))
                .perform(androidx.test.espresso.action.ViewActions.click());

        // After clicking back, the activity should move to DESTROYED state
        activityRule.getScenario().onActivity(activity ->
                org.junit.Assert.assertTrue(activity.isFinishing()));
    }

}
