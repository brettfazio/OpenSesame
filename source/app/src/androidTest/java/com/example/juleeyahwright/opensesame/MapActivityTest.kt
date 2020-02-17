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
import com.example.juleeyahwright.opensesame.CreateReport.CreateReportActivity
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
class MapActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MapActivity::class.java)

    @get:Rule var permissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION)

    @Test
    fun addButtonTest() {
        Intents.init()
        onView(withId(R.id.add_button)).perform(click())
        Thread.sleep(3000)
        Intents.intended(IntentMatchers.hasComponent(CreateReportActivity::class.java.name))
        Intents.release()
    }
}
