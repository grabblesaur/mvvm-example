package com.example.mvvm_example.viewmodel

import com.example.mvvm_example.model.Calculator
import com.example.mvvm_example.model.TipCalculation

class CalculatorViewModel
(private val calculator: Calculator = Calculator()) {

    var inputCheckAmount = ""
    var inputTipPercentage = ""

    var tipCalculation = TipCalculation()

    fun calculateTip() {
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPercentage = inputTipPercentage.toIntOrNull()

        if (checkAmount != null && tipPercentage != null) {
            tipCalculation = calculator
                    .calculateTip(checkAmount, tipPercentage)
        }
    }

}