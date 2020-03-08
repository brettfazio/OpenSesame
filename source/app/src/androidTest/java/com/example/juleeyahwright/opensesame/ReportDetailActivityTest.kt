package com.example.juleeyahwright.opensesame


import android.content.Context
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.juleeyahwright.opensesame.Report.ReportReference
import com.example.juleeyahwright.opensesame.ReportAddInfo.ReportAddInfoActivity
import com.example.juleeyahwright.opensesame.ReportDetail.ReportDetailActivity
import com.google.android.gms.maps.model.LatLng
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@LargeTest
class ReportDetailActivityTest {

    @get:Rule
    val mActivityTestRule: ActivityTestRule<ReportDetailActivity> =
            object : ActivityTestRule<ReportDetailActivity>(ReportDetailActivity::class.java) {
                override fun getActivityIntent(): Intent {
                    val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                    return Intent(targetContext, ReportDetailActivity::class.java).apply {
                        putExtra(ReportDetailActivity.REPORT_EXTRA,
                                ReportReference("dummy", "this is a dummy report", "basement", LatLng(0.0, 0.0), "eFz687FFHDHD"))
                    }
                }
            }

    /*
    Testing: Pressing the add info button takes you to the add into view
    Pass Criteria: After button is clicked, ReportAddInfoActivity is shown
    */
    @Test
    fun addMoreInfo_reportDetailActivityTest() {
        Intents.init()
        onView(withId(R.id.reportDetailAddMessage)).perform(ViewActions.click())
        Thread.sleep(3000)
        Intents.intended(IntentMatchers.hasComponent(ReportAddInfoActivity::class.java.name), (Intents.times(1)))

        Intents.release()
    }

    /*
    Testing: Same information is displayed on the screen as is put in the intent extra
    Pass Criteria: Report put into the intent extra is displayed on the screen
    */
    @Test
    fun setFields_reportDetailActivityTest() {
        onView(withId(R.id.reportDetailHeader)).check(ViewAssertions.matches(ViewMatchers.withText("dummy")))

        onView(withId(R.id.reportDetailInfo)).check(ViewAssertions.matches(ViewMatchers.withText("this is a dummy report")))

        onView(withId(R.id.reportDetailLocationInfo)).check(ViewAssertions.matches(ViewMatchers.withText("basement")))
    }
}
