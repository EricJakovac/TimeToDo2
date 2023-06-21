package com.example.timetodo;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import android.view.View;
import android.widget.TimePicker;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class TimeTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void TimeAddTest() {
        // Dodavanje novog zadatka
        String TEKST = "Novi zadatak (vrijeme)";
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.newTaskText)).perform(typeText(TEKST));

        // Klik na gumb za postavljanje vremena zadatka
        onView(withId(R.id.timePickerButton)).perform(click());

        // Odabir vremena i klik na gumb "OK"
        onView(withId(android.R.id.button1)).perform(click());

        // Klik na gumb za spremanje zadatka
        onView(withId(R.id.newTaskButton)).perform(click());

        // Provjera je li zadatak uspješno ubačen u bazu
        onView(ViewMatchers.withText(TEKST)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Pauza od 3 sekunde
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Brisanje zadatka
        onView(withText(TEKST)).perform(swipeLeft());

        // Klik na gumb "Potvrdi" u dijalogu
        onView(withText("Potvrdi")).perform(ViewActions.click());
    }

    private static ViewAction setTimeOnTimePicker(final int hours, final int minutes) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return Matchers.allOf(ViewMatchers.isAssignableFrom(TimePicker.class), ViewMatchers.isDisplayed());
            }

            @Override
            public String getDescription() {
                return "Set time on TimePicker";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TimePicker timePicker = (TimePicker) view;
                timePicker.setHour(hours);
                timePicker.setMinute(minutes);
            }
        };
    }
}
