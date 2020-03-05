package com.example.juleeyahwright.opensesame


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.juleeyahwright.opensesame.ReportAddInfo.ReportAddInfoActivity
import com.example.juleeyahwright.opensesame.ReportDetail.ReportDetailActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class ReportDetailActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(ReportDetailActivity::class.java)

    /*
    Testing: Pressing the add info button takes you to the add into view
    Pass Criteria: After button is clicked, ReportAddInfoActivity is shown
    */
    @Test
    fun performLogin_loginActivityTest() {
        Intents.init()
        onView(withId(R.id.reportDetailAddMessage)).perform(ViewActions.click())
        Thread.sleep(3000)
        Intents.intended(IntentMatchers.hasComponent(ReportAddInfoActivity::class.java.name), (Intents.times(1)))

        Intents.release()
    }
}
