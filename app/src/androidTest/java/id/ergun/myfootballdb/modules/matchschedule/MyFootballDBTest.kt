package id.ergun.myfootballdb.modules.matchschedule

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import id.ergun.myfootballdb.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MyFootballDBTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MatchScheduleActivity::class.java)

    @Test
    fun myFootballDBTest() {
        Thread.sleep(2000)

        onView(withId(rv_event))
                .check(matches(isDisplayed()))
        onView(withId(rv_event)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5,click()))

        onView(withId(add_to_favorite)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Added to favorite"))
                .check(ViewAssertions.matches(isDisplayed()))

        pressBack()

        onView(withId(navigation_match_schedule))
                .check(ViewAssertions.matches(isDisplayed()))

        onView(withId(navigation_favorite))
                .perform(click())

        onView(withId(rv_event))
                .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(rv_event)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
    }
}