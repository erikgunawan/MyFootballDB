package id.ergun.myfootballdb.modules

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import id.ergun.myfootballdb.R.id.*
import id.ergun.myfootballdb.modules.main.MainActivity
import id.ergun.myfootballdb.utils.espresso.EspressoTestingIdlingResource
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MyFootballDBTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Rule
    @JvmField
    var mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.WRITE_CALENDAR",
                    "android.permission.READ_CALENDAR")

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoTestingIdlingResource.getIdlingResource())
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoTestingIdlingResource.getIdlingResource())
    }

    @Test
    fun teamViewTest() {
        onView(withId(navigation_main))
                .check(ViewAssertions.matches(isDisplayed()))

        onView(withId(navigation_team))
                .perform(click())
        onView(withId(rv_team))
                .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(rv_team)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        onView(withId(add_to_favorite)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Added to favorite"))
                .check(ViewAssertions.matches(isDisplayed()))

        pressBack()

        onView(withId(navigation_main))
                .check(ViewAssertions.matches(isDisplayed()))

        onView(withId(navigation_favorite))
                .perform(click())

        onView(withId(tab_favorite))
                .check(ViewAssertions.matches(isDisplayed()))

        onView(allOf(withText("Teams"),
                isDescendantOfA(withId(tab_favorite))))
                .check(matches(isDisplayed()))
                .perform(click())

        onView(withId(rv_team))
                .check(ViewAssertions.matches(isDisplayed()))

        onView(withId(rv_team)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))

        onView(withId(add_to_favorite)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Removed from favorite"))
                .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun matchViewTest() {
        onView(withId(navigation_main))
                .check(ViewAssertions.matches(isDisplayed()))

        onView(withId(tab_match))
                .check(ViewAssertions.matches(isDisplayed()))

        onView(allOf(withText("Next Match"),
                isDescendantOfA(withId(tab_match))))
                .check(matches(isDisplayed()))
                .perform(click())


        onView(withId(rv_event_next))
                .check(ViewAssertions.matches(isDisplayed()))

        onView(withId(rv_event_next)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))

        onView(withId(add_to_favorite)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Added to favorite"))
                .check(ViewAssertions.matches(isDisplayed()))

        pressBack()

        onView(withId(navigation_main))
                .check(ViewAssertions.matches(isDisplayed()))

        onView(withId(navigation_favorite))
                .perform(click())

        onView(withId(tab_favorite))
                .check(ViewAssertions.matches(isDisplayed()))

        onView(allOf(withText("Matches"),
                isDescendantOfA(withId(tab_favorite))))
                .perform(click())
                .check(matches(isDisplayed()))

        onView(withId(rv_event))
                .check(ViewAssertions.matches(isDisplayed()))

        onView(withId(rv_event)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))

        onView(withId(add_to_favorite)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Removed from favorite"))
                .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun playerViewTest() {
        onView(withId(navigation_main))
                .check(ViewAssertions.matches(isDisplayed()))

        onView(withId(navigation_team))
                .perform(click())
        onView(withId(rv_team))
                .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(rv_team)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))

        onView(withId(tab_team))
                .check(ViewAssertions.matches(isDisplayed()))

        onView(allOf(withText("Players"),
                isDescendantOfA(withId(tab_team))))
                .check(matches(isDisplayed()))
                .perform(click())

        onView(withId(rv_player))
                .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(rv_player)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))

        onView(withId(tv_player_description))
                .check(ViewAssertions.matches(isDisplayed()))


    }
}