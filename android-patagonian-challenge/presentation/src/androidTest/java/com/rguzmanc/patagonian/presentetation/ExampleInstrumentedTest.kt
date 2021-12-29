package com.rguzmanc.patagonian.presentetation

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule var activityScenarioRule = activityScenarioRule<PatagonianActivity>()


    @Test
    fun verifySessionTextViewVisibility(){
        val scenario = activityScenarioRule.scenario
        scenario.moveToState(Lifecycle.State.RESUMED)

        onView(withId(R.id.txt_session_counter)).check(matches(isDisplayed()))
    }
}