package com.taro.guess

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MaterialActivityTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityScenarioRule<MaterialActivity>(MaterialActivity::class.java)

    @Test
    fun guessWrong(){
        val secret = activityTestRule.scenario.onActivity {
            
        }

        Espresso.onView(ViewMatchers.withId(R.id.number)).perform(ViewActions.typeText("5"))

    }
}