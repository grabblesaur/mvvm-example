package com.example.mvvm_example.viewmodel

import android.app.Application
import androidx.databinding.BaseObservable
import androidx.databinding.Observable
import com.example.mvvm_example.R
import com.example.mvvm_example.model.Calculator
import com.example.mvvm_example.model.TipCalculation

private const val TAG = "CalculatorViewModel"

class CalculatorViewModel @JvmOverloads constructor(app: Application, val calculator: Calculator = Calculator()) : ObservableViewModel(app) {

    var inputCheckAmount = ""
    var inputTipPercentage = ""

    var outputCheckAmount = ""
    var outputTipAmount = ""
    var outputGrandTotal = ""

    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tc: TipCalculation) {
        val app = getApplication<Application>()
        outputCheckAmount = app.getString(R.string.dollar_amount, tc.checkAmount)
        outputTipAmount = app.getString(R.string.dollar_amount, tc.tipAmount)
        outputGrandTotal = app.getString(R.string.dollar_amount, tc.grandTotal)
    }

    fun calculateTip() {
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPercentage = inputTipPercentage.toIntOrNull()

        if (checkAmount != null && tipPercentage != null) {
            updateOutputs(calculator
                    .calculateTip(checkAmount, tipPercentage))
            notifyChange()
        }
    }
}