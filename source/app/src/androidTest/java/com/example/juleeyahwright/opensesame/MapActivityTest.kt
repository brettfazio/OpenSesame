package com.example.juleeyahwright.opensesame



import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.example.juleeyahwright.opensesame.CreateReport.CreateReportActivity
import com.example.juleeyahwright.opensesame.Map.MapActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class MapActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MapActivity::class.java)

    @get:Rule var permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION)

    /*
    Testing: When add button is clicked, user is taken to CreateReportActivity
    Pass Criteria: After button is clicked, CreateReportActivity is the newly displayed screen
    */
    @Test
    fun addButtonAndTapMapTest() {
        Intents.init()
        onView(withId(R.id.add_button)).perform(click())

        onView(withId(R.id.map)).perform(click())

        Thread.sleep(3000)
        Intents.intended(IntentMatchers.hasComponent(CreateReportActivity::class.java.name))
        Intents.release()
    }

    /*
    Testing: An add button appears
    Pass Criteria: The add button is displayed with the text ADD and the + symbol
    */
    @Test
    fun assertAddButtonStartStateTest() {
        onView(withId(R.id.add_button)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.add_button)).check(ViewAssertions.matches(ViewMatchers.withText("Add")))
    }

    /*
    Testing: When the add button is pressed, the add button changes state
             and the map can now be clicked to place a marker
    Pass Criteria: The add button is transformed into a Cancel button
    */
    @Test
    fun assertProcessingStateTest() {
        onView(withId(R.id.add_button)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.add_button)).perform(click())
        onView(withId(R.id.add_button)).check(ViewAssertions.matches(ViewMatchers.withText("Cancel")))
    }

    /*
    Testing: When the cancel button is pressed, the attempt to make a marker is cancelled
    Pass Criteria: The cancel button returns to an add button and regular map interactivity resumes
    */
    @Test
    fun assertCancellingTest() {
        onView(withId(R.id.add_button)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.add_button)).perform(click())
        onView(withId(R.id.add_button)).perform(click())
        onView(withId(R.id.add_button)).check(ViewAssertions.matches(ViewMatchers.withText("Add")))
    }

    /*
    Testing: The user can click add and cancel multiple times and it will be in the right state
    Pass Criteria: When the add button is pressed, cancelled, and then add button is clicked again,
                   the add state is the current state
    */
    @Test
    fun assertBackToProcessingTest() {
        onView(withId(R.id.add_button)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.add_button)).perform(click())
        onView(withId(R.id.add_button)).perform(click())
        onView(withId(R.id.add_button)).perform(click())
        onView(withId(R.id.add_button)).check(ViewAssertions.matches(ViewMatchers.withText("Cancel")))
    }

    /*
    Testing: When toggling between add and cancel states, the map is still there
    Pass Criteria: The map is present
    */
    @Test
    fun uselessMapTapTest() {
        onView(withId(R.id.add_button)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.add_button)).perform(click())
        onView(withId(R.id.add_button)).perform(click())

        onView(withId(R.id.map)).perform(click())

        Thread.sleep(3000)

        onView(withId(R.id.map)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    /*
    Testing: A user can cancel the act of creating a report at any point in the process before submit
    Pass Criteria: Clicking add to add a marker and then clicking the back button
                   returns the state back to the original state
    */
    @Test
    fun createAndCancelTest() {
        Intents.init()
        onView(withId(R.id.add_button)).perform(click())

        onView(withId(R.id.map)).perform(click())

        Thread.sleep(3000)
        Intents.intended(IntentMatchers.hasComponent(CreateReportActivity::class.java.name))
        Intents.release()

        pressBack()
        onView(withId(R.id.add_button)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.add_button)).check(ViewAssertions.matches(ViewMatchers.withText("Add")))
    }
}
