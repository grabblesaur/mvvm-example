package com.example.mvvm_example

import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.mvvm_example.view.TipCalculatorActivity
import org.junit.Rule
import org.junit.Test

class TipCalculatorActivityTest {

    @get:Rule
    var activityTest = ActivityTestRule(TipCalculatorActivity::class.java)

    @Test
    fun testTipCalculator() {

        // Calculate Tip
        enter(checkAmount = 15.99, tipPercent = 15)
        calculateTip()
        assertOutput(name = "", checkAmount = "$15,99", tipAmount = "$2,40", total = "$18,39")

        // Save Tip
        saveTip(name = "BBQ Max")
        assertOutput(name = "BBQ Max", checkAmount = "$15,99", tipAmount = "$2,40", total = "$18,39")

        // Clear outputs
        clearOutputs()
        assertOutput(name = "", checkAmount = "$0,00", tipAmount = "$0,00", total = "$0,00")

        // Load Tip
        loadTip(name = "BBQ Max")
        assertOutput(name = "BBQ Max", checkAmount = "$15,99", tipAmount = "$2,40", total = "$18,39")
    }

    fun enter(checkAmount: Double, tipPercent: Int) {
        onView(withId(R.id.input_check_amount)).perform(replaceText(checkAmount.toString()))
        onView(withId(R.id.input_tip_percentage)).perform(replaceText(tipPercent.toString()))
    }

    fun calculateTip() {
        onView(withId(R.id.calculate_fab)).perform(click())
    }

    fun assertOutput(name: String, checkAmount: String, tipAmount: String, total: String) {
        onView(withId(R.id.calculation_name)).check(matches(withText(name)))
        onView(withId(R.id.bill_amount)).check(matches(withText(checkAmount)))
        onView(withId(R.id.tip_amount)).check(matches(withText(tipAmount)))
        onView(withId(R.id.grand_total)).check(matches(withText(total)))
    }

    fun clearOutputs() {
        enter(0.0, 0)
        calculateTip()
    }

    fun saveTip(name: String) {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getContext())
        onView(withText(R.string.action_save)).perform(click())
        onView(withHint(R.string.save_hint)).perform(replaceText(name))
        onView(withText(R.string.action_dialog_save)).perform(click())
    }

    fun loadTip(name: String) {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getContext())
        onView(withText(R.string.action_load)).perform(click())
        onView(withText(name)).perform(click())
    }

}