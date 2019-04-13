package com.example.mvvm_example.model

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CalculatorTest {

    lateinit var calculator: Calculator

    @Before
    fun setup() {
        calculator = Calculator()
    }

    @Test
    fun testCalculateTip() {

        val baseTc = TipCalculation(checkAmount = 10.0)
        val testValues = listOf(baseTc.copy(tipPct = 25, tipAmount = 2.5, grandTotal = 12.5),
            baseTc.copy(tipPct = 10, tipAmount = 1.0, grandTotal = 11.0),
            baseTc.copy(tipPct = 18, tipAmount = 1.8, grandTotal = 11.8))

        testValues.forEach {
            assertEquals(it, calculator.calculateTip(it.checkAmount, it.tipPct))
        }
    }
}