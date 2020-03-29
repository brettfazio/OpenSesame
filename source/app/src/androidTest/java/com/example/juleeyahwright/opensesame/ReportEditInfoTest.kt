package com.example.juleeyahwright.opensesame


import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.juleeyahwright.opensesame.Report.ReportReference
import com.example.juleeyahwright.opensesame.ReportEditInfo.ReportEditInfoActivity
import com.google.android.gms.maps.model.LatLng
import org.junit.Rule
import org.junit.Test


@LargeTest
class ReportEditInfoTest {

    @get:Rule
    val mActivityTestRule: ActivityTestRule<ReportEditInfoActivity> =
            object : ActivityTestRule<ReportEditInfoActivity>(ReportEditInfoActivity::class.java) {
                override fun getActivityIntent(): Intent {
                    val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                    return Intent(targetContext, ReportEditInfoActivity::class.java).apply {
                        putExtra(ReportEditInfoActivity.REPORT_EXTRA,
                                ReportReference("dummy", "this is a dummy report", "basement", LatLng(0.0, 0.0), "eFz687FFHDHD", "hashUID"))
                    }
                }
            }

    /*
    Testing: Title is set with report info
    Pass Criteria: Title is set with report info from dummy report
    */
    @Test
    fun titleSetTest() {
        onView(withId(R.id.editTitleHeader)).check(ViewAssertions.matches(ViewMatchers.withText("dummy")))
    }

    /*
    Testing: Location info is set with report info
    Pass Criteria: Location info is set with report info from dummy report
    */
    @Test
    fun locationInfoSetTest() {
        onView(withId(R.id.editReportDetailInfo)).check(ViewAssertions.matches(ViewMatchers.withText("basement")))
    }

        /*
    Testing: Info is set with report info
    Pass Criteria: Info is set with report info from dummy report
    */
    @Test
    fun infoSetTest() {
        onView(withId(R.id.editReportLocInfo)).check(ViewAssertions.matches(ViewMatchers.withText("this is a dummy report")))
    }

    /*
    Testing: Create message button is visible to the user.
    Pass Criteria: Create message button is completely visible.
    */
    @Test
    fun createButtonVisible_reportEditInfoActivityTest() {
        onView(withId(R.id.reportDetailEditButton)).check(ViewAssertions.matches(isCompletelyDisplayed()))
    }
}
