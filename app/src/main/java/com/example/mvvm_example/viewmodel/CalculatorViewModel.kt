package com.example.mvvm_example.viewmodel

import android.app.Application
import androidx.databinding.BaseObservable
import androidx.databinding.Observable
import com.example.mvvm_example.R
import com.example.mvvm_example.model.Calculator
import com.example.mvvm_example.model.TipCalculation

private const val TAG = "CalculatorViewModel"

class CalculatorViewModel @JvmOverloads constructor(app: Application, val calculator: Calculator = Calculator()) : ObservableViewModel(app) {

    private var lastTipCalculated = TipCalculation()

    var inputCheckAmount = ""
    var inputTipPercentage = ""

    val outputCheckAmount get() = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalculated.checkAmount)
    val outputTipAmount get() = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalculated.tipAmount)
    val outputGrandTotal get() = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalculated.grandTotal)
    val locationName get() = lastTipCalculated.location

    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tc: TipCalculation) {
        lastTipCalculated = tc
        notifyChange()
    }

    fun calculateTip() {
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPercentage = inputTipPercentage.toIntOrNull()

        if (checkAmount != null && tipPercentage != null) {
            updateOutputs(calculator.calculateTip(checkAmount, tipPercentage))
        }
    }

    fun saveCurrentTip(name: String) {
        val tipToSave = lastTipCalculated.copy(location = name)
        calculator.saveTipCalculation(tipToSave)
        updateOutputs(tipToSave)
    }
}