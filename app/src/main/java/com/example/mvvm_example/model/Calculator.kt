package com.example.mvvm_example.model

import androidx.lifecycle.LiveData
import java.math.RoundingMode

class Calculator(val repository: TipCalculationRepository = TipCalculationRepository()) {

    fun calculateTip(checkAmount: Double, tipPct: Int): TipCalculation {
        val tipAmount = (checkAmount * (tipPct.toDouble() / 100.0))
            .toBigDecimal()
            .setScale(2, RoundingMode.HALF_UP)
            .toDouble()
        val grandTotal = checkAmount + tipAmount

        return TipCalculation("", checkAmount, tipPct, tipAmount, grandTotal)
    }

    fun saveTipCalculation(tc: TipCalculation) {
        repository.saveTipCalculation(tc)
    }

    fun loadTipCalculation(locationName: String): TipCalculation? {
        return repository.loadTipCalculationByName(locationName)
    }

    fun loadSavedTipCalculations(): LiveData<List<TipCalculation>> {
        return repository.loadSavedTipCalculation()
    }

}