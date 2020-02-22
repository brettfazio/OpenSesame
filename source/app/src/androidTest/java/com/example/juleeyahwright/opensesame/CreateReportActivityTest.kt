package com.example.juleeyahwright.opensesame



import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.juleeyahwright.opensesame.CreateReport.CreateReportActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class CreateReportActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(CreateReportActivity::class.java)

    /*
    Testing: Report has a title field
    Pass Criteria: A report has a title
    */
    @Test
    fun setTitle_createReportActivityTest() {
        onView(withId(R.id.titleEditText)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.titleEditText)).perform(ViewActions.replaceText("Title"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.titleEditText)).check(ViewAssertions.matches(ViewMatchers.withText("Title")))
    }
    /*
    Testing:Report has an info field
    Pass Criteria: A report has info text
    */
    @Test
    fun setInfo_createReportActivityTest() {
        onView(withId(R.id.infoEditText)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.infoEditText)).perform(ViewActions.replaceText("Info\nmation"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.infoEditText)).check(ViewAssertions.matches(ViewMatchers.withText("Info\nmation")))
    }
}
