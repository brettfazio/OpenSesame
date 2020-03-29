package com.example.juleeyahwright.opensesame


import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
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
        onView(withId(R.id.editTitleHeader)).check(ViewAssertions.matches(withText("dummy")))
    }

    /*
    Testing: Location info is set with report info
    Pass Criteria: Location info is set with report info from dummy report
    */
    @Test
    fun locationInfoSetTest() {
        onView(withId(R.id.editReportDetailInfo)).check(ViewAssertions.matches(withText("this is a dummy report")))
    }

        /*
    Testing: Info is set with report info
    Pass Criteria: Info is set with report info from dummy report
    */
    @Test
    fun infoSetTest() {
        onView(withId(R.id.editReportLocInfo)).check(ViewAssertions.matches(withText("basement")))
    }

    /*
    Testing: Create message button is visible to the user.
    Pass Criteria: Create message button is completely visible.
    */
    @Test
    fun createButtonVisible_reportEditInfoActivityTest() {
        onView(withId(R.id.reportDetailEditButton)).check(ViewAssertions.matches(isCompletelyDisplayed()))
    }

    /*
    Testing: Location info is modifiable
    Pass Criteria: Location info field is set to the text that it is changed to.
    */
    @Test
    fun setLocationInfoTest() {
        onView(withId(R.id.editReportLocInfo)).perform(replaceText("new loc info"), closeSoftKeyboard())
        onView(withId(R.id.editReportLocInfo)).check(ViewAssertions.matches(withText("new loc info")))
    }

    /*
    Testing: Name is modifiable
    Pass Criteria: Name field is set to the text that it is changed to.
*/
    @Test
    fun setTitleInfoTest() {
        onView(withId(R.id.editTitleHeader)).perform(replaceText("title"), closeSoftKeyboard())
        onView(withId(R.id.editTitleHeader)).check(ViewAssertions.matches(withText("title")))
    }

    /*
    Testing: Info is modifiable
    Pass Criteria: Info field is set to the text that it is changed to.
    */
    @Test
    fun setInfoTest() {
        onView(withId(R.id.editReportDetailInfo)).perform(replaceText("new info"), closeSoftKeyboard())
        onView(withId(R.id.editReportDetailInfo)).check(ViewAssertions.matches(withText("new info")))
    }
}
