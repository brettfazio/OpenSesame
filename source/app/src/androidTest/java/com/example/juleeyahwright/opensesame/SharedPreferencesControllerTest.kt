package com.example.juleeyahwright.opensesame

import android.content.Context
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class SharedPreferencesControllerTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LaunchActivity::class.java)

    @Before
    fun clearData() {
        val mActivity = mActivityTestRule.activity

        val prefs = mActivity.getSharedPreferences("LOGIN", Context.MODE_PRIVATE)

        prefs.edit().clear().commit()
    }

    /*
    Testing: Email is stored
    Pass Criteria: Stored data matches input
    */
    @Test
    fun email_test() {
        SharedPreferencesController.setEmail(mActivityTestRule.activity.applicationContext, "test@test.com")
        assert(SharedPreferencesController.getEmail(mActivityTestRule.activity.applicationContext) == "test@test.com")
    }

    /*
    Testing: Password data is stored
    Pass Criteria: Stored data matches input
    */
    @Test
    fun password_test() {
        SharedPreferencesController.setPassword(mActivityTestRule.activity.applicationContext, "strong")
        assert(SharedPreferencesController.getPassword(mActivityTestRule.activity.applicationContext) == "strong")
    }

    /*
    Testing: With no input, login credentials are absent
    Pass Criteria: No login credentials present
    */
    @Test
    fun notSet_test() {
        assert(!SharedPreferencesController.isLoginCredentialsSet(mActivityTestRule.activity.applicationContext))
    }

    /*
    Testing: Email data is stored
    Pass Criteria: The email that is stored and received matches what was input
    */
    @Test
    fun emailSet_test() {
        SharedPreferencesController.setEmail(mActivityTestRule.activity.applicationContext, "test@test.com")
        assert(!SharedPreferencesController.isLoginCredentialsSet(mActivityTestRule.activity.applicationContext))
    }

    /*
    Testing: Password data is stored
    Pass Criteria: The password that is stored and received matches what was input
    */
    @Test
    fun passwordSet_test() {
        SharedPreferencesController.setPassword(mActivityTestRule.activity.applicationContext, "str0ng")
        assert(!SharedPreferencesController.isLoginCredentialsSet(mActivityTestRule.activity.applicationContext))
    }

    /*
    Testing: Email and password data is stored
    Pass Criteria: The email and password that are stored and received match what was input
    */
    @Test
    fun allSet_test() {
        SharedPreferencesController.setEmail(mActivityTestRule.activity.applicationContext, "test@test.com")
        SharedPreferencesController.setPassword(mActivityTestRule.activity.applicationContext, "str0ng")
        assert(SharedPreferencesController.isLoginCredentialsSet(mActivityTestRule.activity.applicationContext))
    }

    /*
    Testing: After email and password are stored, clear method removes credentials from storage
    Pass Criteria: No login credentials present
    */
    @Test
    fun clear_test() {
        SharedPreferencesController.setEmail(mActivityTestRule.activity.applicationContext, "test@test.com")
        SharedPreferencesController.setPassword(mActivityTestRule.activity.applicationContext, "str0ng")
        SharedPreferencesController.clearSignInData(mActivityTestRule.activity.applicationContext)
        assert(!SharedPreferencesController.isLoginCredentialsSet(mActivityTestRule.activity.applicationContext))
    }
}