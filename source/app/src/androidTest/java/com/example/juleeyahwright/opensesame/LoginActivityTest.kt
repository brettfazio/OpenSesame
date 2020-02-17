package com.example.juleeyahwright.opensesame


import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
import com.example.juleeyahwright.opensesame.Map.MapActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LoginActivity::class.java)

    @get:Rule var permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION)

    @Before
    fun clearData() {
        val mActivity = mActivityTestRule.activity

        val prefs = mActivity.getSharedPreferences("LOGIN", Context.MODE_PRIVATE)

        prefs.edit().clear().commit()
    }

    @Test
    fun setEmail_loginActivityTest() {
        val appCompatEditText = onView(
                allOf(withId(R.id.emailField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()))
        appCompatEditText.perform(replaceText("cool@cool.com"), closeSoftKeyboard())
        onView(withId(R.id.emailField)).check(ViewAssertions.matches(withText("cool@cool.com")))
    }

    @Test
    fun setPassword_loginActivityTest() {
        onView(withId(R.id.passwordField)).perform(replaceText("123456"), closeSoftKeyboard())
        onView(withId(R.id.passwordField)).check(ViewAssertions.matches(withText("123456")))
    }

    @Test
    fun performLogin_loginActivityTest() {
        onView(withId(R.id.emailField)).perform(replaceText("cool@cool.com"), closeSoftKeyboard())
        onView(withId(R.id.passwordField)).perform(replaceText("123456"), closeSoftKeyboard())


        Intents.init()
        onView(withId(R.id.logInButton)).perform(click())
        Thread.sleep(3000)
        Intents.intended(IntentMatchers.hasComponent(MapActivity::class.java.name), (Intents.times(1)))

        Intents.release()
    }

    @Test
    fun performLoginSpam_loginActivityTest() {
        onView(withId(R.id.emailField)).perform(replaceText("cool@cool.com"), closeSoftKeyboard())
        onView(withId(R.id.passwordField)).perform(replaceText("123456"), closeSoftKeyboard())


        Intents.init()
        onView(withId(R.id.logInButton)).perform(click(), doubleClick())
        onView(withId(R.id.logInButton)).perform(click())
        Thread.sleep(3000)
        Intents.intended(IntentMatchers.hasComponent(MapActivity::class.java.name), (Intents.times(1)))

        Intents.release()
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
