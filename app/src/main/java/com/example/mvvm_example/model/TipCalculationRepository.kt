package com.example.mvvm_example.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TipCalculationRepository {

    val savedTips = mutableMapOf<String, TipCalculation>()

    fun saveTipCalculation(tc: TipCalculation) {
        savedTips[tc.location] = tc
    }

    fun loadTipCalculationByName(locationName: String): TipCalculation? {
        return savedTips[locationName]
    }

    fun loadSavedTipCalculation(): LiveData<List<TipCalculation>> {
        val liveData = MutableLiveData<List<TipCalculation>>()
        liveData.value = savedTips.values.toList()
        return liveData
    }
}