package com.example.juleeyahwright.opensesame


import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.juleeyahwright.opensesame.Report.ReportReference
import com.example.juleeyahwright.opensesame.ReportAddInfo.ReportAddInfoActivity
import com.google.android.gms.maps.model.LatLng
import org.junit.Rule
import org.junit.Test


@LargeTest
class ReportAddInfoActivityTest {

    @get:Rule
    val mActivityTestRule: ActivityTestRule<ReportAddInfoActivity> =
            object : ActivityTestRule<ReportAddInfoActivity>(ReportAddInfoActivity::class.java) {
                override fun getActivityIntent(): Intent {
                    val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                    return Intent(targetContext, ReportAddInfoActivity::class.java).apply {
                        putExtra(ReportAddInfoActivity.REPORT_EXTRA,
                                ReportReference("dummy", "this is a dummy report", "basement", LatLng(0.0, 0.0), "eFz687FFHDHD"))
                    }
                }
            }

    /*
    Testing: Can enter a message into the field
    Pass Criteria: A message can be successfully entered
    */
    @Test
    fun setMessage_reportAddInfoActivityTest() {
        onView(withId(R.id.messageInfoEditText)).perform(ViewActions.replaceText("123456"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.messageInfoEditText)).check(ViewAssertions.matches(ViewMatchers.withText("123456")))
    }
}
