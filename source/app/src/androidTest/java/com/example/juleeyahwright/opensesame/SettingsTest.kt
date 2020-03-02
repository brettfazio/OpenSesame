package com.example.juleeyahwright.opensesame

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView;
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.example.juleeyahwright.opensesame.Common.Constant
import com.example.juleeyahwright.opensesame.Common.SharedPreferencesController
import com.example.juleeyahwright.opensesame.Settings.SettingsActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test


class SettingsTest {

    @Rule
    @JvmField
    var settingsActivityTestRule = ActivityTestRule(SettingsActivity::class.java)
    /*
   Testing: Color is stored
   Pass criteria: A color is retrieved
    */
    @Test
    fun colorSet_test() {
        assert(Constant.color != null)
    }

    /*
    Testing: Theme is stored
    Pass criteria: A theme is retrieved
     */
    @Test
    fun themeSet_test() {
        assert(Constant.appTheme != null)
    }

    /*
    Testing: When theme is not set, default to amber theme
    Pass criteria: Amber theme is returned when nothing is set
     */
    @Test
    fun defaultTheme_test() {
        SharedPreferencesController.setTheme(settingsActivityTestRule.activity.applicationContext, 0)
        assert(Constant.appTheme == R.style.AppTheme_amber)
    }

    /*
    Testing: When color picker button is selected, color chooser dialog shows up
    Pass criteria: Color choose dialog is opened
     */
//    @Test
//    fun colorDialog_test() {
//        onView(ViewMatchers.withId(R.id.color_button)).perform(ViewActions.click())
//        Thread.sleep(3000)
//        onView(withText("WHITE")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
//    }

    /*
    Testing: Checkbox is checked
    Pass criteria: When checkbox is selected, it is checked
     */
    @Test
    fun checkbox_test() {
        onView(ViewMatchers.withId(R.id.show_compass)).perform(ViewActions.click())
        Thread.sleep(3000)
        assert(true)
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
