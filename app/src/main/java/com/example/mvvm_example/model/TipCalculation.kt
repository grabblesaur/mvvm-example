package com.example.mvvm_example.model

data class TipCalculation(
    val location: String = "",
    val checkAmount: Double = 0.0,
    val tipPct: Int = 0,
    val tipAmount: Double = 0.0,
    val grandTotal: Double = 0.0
)