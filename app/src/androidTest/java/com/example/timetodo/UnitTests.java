package com.example.timetodo;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class UnitTests {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test //Unit test za dodavanje zadatka i provjera je li prikazan
    public void insertTask() {
        String TEKST = "Dodaj zadatak";
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.newTaskText)).perform(typeText(TEKST));
        onView(withId(R.id.newTaskButton)).perform(click());
        onView(withText(TEKST)).check(matches(isDisplayed()));
    }

    @Test //Unit test za označavanje zadatka kao dovršenog
    public void testMarkTaskAsCompleted() {
        onView(withId(R.id.todoCheckbox)).perform(click());
    }

    @Test //Unit test za označavanje zadatka kao nedovršenog
    public void testMarkTaskAsUnCompleted() {
        onView(withId(R.id.todoCheckbox)).perform(click());
    }

    @Test //Unit test za swipe udesno (SwipeRight)
    public void swipeRightTest() { onView(withText("Dodaj zadatak")).perform(swipeRight()); }

    @Test //Unit test za swipe ulijevo (Swipeleft)
    public void swipeLeftTest() { onView(withText("Dodaj zadatak")).perform(swipeLeft()); }
}
