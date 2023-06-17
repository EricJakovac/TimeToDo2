package com.example.timetodo;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testUI() {
        //Dodavanje zadatka
        String TEKST = "Ovo je TEST!";
        onView(withId(R.id.tasksRecyclerView)).check(matches(isDisplayed())); // Provjera vidljivosti RecyclerView-a
        onView(withId(R.id.fab)).perform(click()); // Klik na FAB gumb za otvaranje dijaloga
        onView(withId(R.id.newTaskText)).perform(typeText(TEKST)); // Unos teksta
        onView(withId(R.id.newTaskButton)).perform(click()); // Klik na gumb za spremanje
        onView(withText(TEKST)).check(matches(isDisplayed())); // Provjera je li uneseni tekst prikazan u RecyclerView-u
    }
}

