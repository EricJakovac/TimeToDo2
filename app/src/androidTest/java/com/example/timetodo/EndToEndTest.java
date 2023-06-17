package com.example.timetodo;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EndToEndTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testEndToEndFlow() {
        // Dodavanje novog zadatka
        String TEKST = "Novi zadatak";
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.newTaskText)).perform(typeText(TEKST));
        onView(withId(R.id.newTaskButton)).perform(click());

        // Označavanje zadatka kao dovršenog
        onView(withId(R.id.todoCheckbox)).perform(click());

        // Označavanje zadatka kao nedovršenog
        onView(withId(R.id.todoCheckbox)).perform(click());

        // Izmjena zadatka
        String PROMIJENI = "Izmijenjeni zadatak";
        onView(withText(TEKST)).perform(swipeRight());
        onView(withId(R.id.newTaskText)).perform(clearText());
        onView(withId(R.id.newTaskText)).perform(typeText(PROMIJENI));
        onView(withText("Spremi")).perform(click());
        onView(withText(PROMIJENI)).check(matches(isDisplayed()));

        // Brisanje zadatka
        onView(withText(PROMIJENI)).perform(swipeLeft());

        // Klik na gumb "Potvrdi" u dijalogu
        onView(withText("Potvrdi")).perform(ViewActions.click());
    }
}