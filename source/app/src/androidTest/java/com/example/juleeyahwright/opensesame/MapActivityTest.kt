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

    @Test
    fun addButtonAndTapMapTest() {
        Intents.init()
        onView(withId(R.id.add_button)).perform(click())

        onView(withId(R.id.map)).perform(click())

        Thread.sleep(3000)
        Intents.intended(IntentMatchers.hasComponent(CreateReportActivity::class.java.name))
        Intents.release()
    }

    @Test
    fun assertAddButtonStartStateTest() {
        onView(withId(R.id.add_button)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.add_button)).check(ViewAssertions.matches(ViewMatchers.withText("Add")))
    }

    @Test
    fun assertProcessingStateTest() {
        onView(withId(R.id.add_button)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.add_button)).perform(click())
        onView(withId(R.id.add_button)).check(ViewAssertions.matches(ViewMatchers.withText("Cancel")))
    }

    @Test
    fun assertCancellingTest() {
        onView(withId(R.id.add_button)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.add_button)).perform(click())
        onView(withId(R.id.add_button)).perform(click())
        onView(withId(R.id.add_button)).check(ViewAssertions.matches(ViewMatchers.withText("Add")))
    }

    @Test
    fun assertBackToProcessingTest() {
        onView(withId(R.id.add_button)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.add_button)).perform(click())
        onView(withId(R.id.add_button)).perform(click())
        onView(withId(R.id.add_button)).perform(click())
        onView(withId(R.id.add_button)).check(ViewAssertions.matches(ViewMatchers.withText("Cancel")))
    }

    @Test
    fun uselessMapTapTest() {
        onView(withId(R.id.add_button)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.add_button)).perform(click())
        onView(withId(R.id.add_button)).perform(click())

        onView(withId(R.id.map)).perform(click())

        Thread.sleep(3000)

        onView(withId(R.id.map)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

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
