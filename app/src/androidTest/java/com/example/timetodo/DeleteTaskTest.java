package com.example.timetodo;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DeleteTaskTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testDeleteTask() {
        String TEKST = "Zadatak za brisanje";
        //Dodavanje zadatka
        onView(withId(R.id.fab)).perform(ViewActions.click());
        onView(withId(R.id.newTaskText)).perform(ViewActions.typeText(TEKST));
        onView(withId(R.id.newTaskButton)).perform(ViewActions.click());
        onView(withText(TEKST)).check(matches(isDisplayed()));

        // Brisanje zadatka
        onView(withText("Zadatak za brisanje")).perform(swipeLeft());

        // Klik na gumb "Poništi" u dijalogu
        onView(withText("Poništi")).perform(ViewActions.click());

        // Brisanje zadatka
        onView(withText("Zadatak za brisanje")).perform(swipeLeft());

        // Klik na gumb "Potvrdi" u dijalogu
        onView(withText("Potvrdi")).perform(ViewActions.click());

    }
}