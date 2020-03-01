package com.example.juleeyahwright.opensesame


import android.Manifest
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

    // keeps a pop-up window asking for permissions from being generated that would interfere with tests
    @get:Rule
    var permissionRule: GrantPermissionRule = GrantPermissionRule.grant(Manifest.permission.ACCESS_FINE_LOCATION)

    // sets up clean environment for tests
    @Before
    fun clearData() {
        val mActivity = mActivityTestRule.activity

        val prefs = mActivity.getSharedPreferences("LOGIN", Context.MODE_PRIVATE)

        prefs.edit().clear().commit()
    }

    /*
    Testing: Soft keyboard inputs string into email field
    Pass Criteria: Text displayed is the same as the text input
    */
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

    /*
    Testing: Soft keyboard inputs string into password field
    Pass Criteria: Text displayed is the same as the text input
    */
    @Test
    fun setPassword_loginActivityTest() {
        onView(withId(R.id.passwordField)).perform(replaceText("123456"), closeSoftKeyboard())
        onView(withId(R.id.passwordField)).check(ViewAssertions.matches(withText("123456")))
    }

    /*
    Testing: When correct email/password combo is entered and login is pressed, user is taken to MapActivity
    Pass Criteria: After button is clicked, MapActivity is the newly displayed screen
    */
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

    /*
    Testing: When correct email/password combo is entered and login is pressed many times, user is taken to MapActivity
    Pass Criteria: After button is clicked several times, MapActivity is the newly displayed screen
    */
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

    /*
    Testing: That pressing sign up takes you to the sign up page
    Pass Criteria: After pressing the sign up button an Intent to SignUpActivity is triggered.
    */
    @Test
    fun signUp_loginActivityTest() {
        Intents.init()
        onView(withId(R.id.signUpButton)).perform(click())
        Thread.sleep(3000)
        Intents.intended(IntentMatchers.hasComponent(SignUpActivity::class.java.name), (Intents.times(1)))

        Intents.release()
    }

    /*
    Utility function that verifies that an element is present
    */
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
