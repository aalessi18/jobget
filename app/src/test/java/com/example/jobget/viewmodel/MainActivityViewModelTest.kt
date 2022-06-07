package com.example.jobget.viewmodel

import com.example.jobget.repository.UserDataRepositoryImplementation
import org.junit.Assert.assertEquals
import org.junit.Test

class MainActivityViewModelTest {

    @Test
    fun testGetDefaultIncome() {
        val repository = UserDataRepositoryImplementation()
        val viewModel = MainActivityViewModel(repository)

        assertEquals(1, viewModel.getDefaultIncome(0))
        assertEquals(1, viewModel.getDefaultIncome(1))
        assertEquals(1, viewModel.getDefaultIncome(-1))
        assertEquals(100, viewModel.getDefaultIncome(100))
    }
}