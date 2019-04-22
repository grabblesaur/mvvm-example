package com.example.mvvm_example

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
        assertOutput(name = "", checkAmount = "$15.99", tipAmount = "$2.40", total = "$18.39")

        // Save Tip
        saveTip(name = "BBQ Max")
        assertOutput(name = "BBQ Max", checkAmount = "$15.99", tipAmount = "$2.40", total = "$18.39")

        // Clear outputs
        clearOutputs()
        assertOutput(name = "", checkAmount = "$0.00", tipAmount = "$0.00", total = "$0.00")


        // Load Tip
        loadTip(name = "BBQ Max")

    }

}