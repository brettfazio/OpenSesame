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

    @Test
    fun email_test() {
        SharedPreferencesController.setEmail(mActivityTestRule.activity.applicationContext, "test@test.com")
        assert(SharedPreferencesController.getEmail(mActivityTestRule.activity.applicationContext).equals("test@test.com"))
    }

    @Test
    fun password_test() {
        SharedPreferencesController.setPassword(mActivityTestRule.activity.applicationContext, "strong")
        assert(SharedPreferencesController.getPassword(mActivityTestRule.activity.applicationContext).equals("strong"))
    }

    @Test
    fun notSet_test() {
        assert(!SharedPreferencesController.isLoginCredentialsSet(mActivityTestRule.activity.applicationContext))
    }

    @Test
    fun emailSet_test() {
        SharedPreferencesController.setEmail(mActivityTestRule.activity.applicationContext, "test@test.com")
        assert(!SharedPreferencesController.isLoginCredentialsSet(mActivityTestRule.activity.applicationContext))
    }

    @Test
    fun passwordSet_test() {
        SharedPreferencesController.setPassword(mActivityTestRule.activity.applicationContext, "str0ng")
        assert(!SharedPreferencesController.isLoginCredentialsSet(mActivityTestRule.activity.applicationContext))
    }

    @Test
    fun allSet_test() {
        SharedPreferencesController.setEmail(mActivityTestRule.activity.applicationContext, "test@test.com")
        SharedPreferencesController.setPassword(mActivityTestRule.activity.applicationContext, "str0ng")
        assert(SharedPreferencesController.isLoginCredentialsSet(mActivityTestRule.activity.applicationContext))
    }
}