package com.example.mvvm_example.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TipCalculationRepositoryTest {

    lateinit var repository: TipCalculationRepository

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        repository = TipCalculationRepository()
    }

    @Test
    fun testSaveTip() {
        val tip = TipCalculation("Pancake Paradise",
            100.00, 15,
            15.00, 115.00)
        repository.saveTipCalculation(tip)
        assertEquals(tip, repository.loadTipCalculationByName(tip.location))
    }

    @Test
    fun testLoadSavedTipCalculation() {
        val tc1 = TipCalculation("Pancake Paradise",
            100.00, 15,
            15.00, 115.00)
        val tc2 = TipCalculation("Veggie Sensation",
            100.00, 50,
            50.00, 150.00)
        repository.saveTipCalculation(tc1)
        repository.saveTipCalculation(tc2)

        repository.loadSavedTipCalculation().observeForever { tipCalculations ->
                assertEquals(2, tipCalculations?.size)
        }
    }

}