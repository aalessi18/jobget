package com.example.jobget.viewmodel

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AddTransactionViewModelTest {
    private lateinit var viewModel: AddTransactionViewModel

    @Before
    fun setup() {
        viewModel = AddTransactionViewModel()
    }

    @Test
    fun testUpdateEditTextValueIncrement() {
        assertEquals("1", viewModel.updateEditTextValue("0", true))
        assertEquals("0", viewModel.updateEditTextValue("", true))
        assertEquals("100", viewModel.updateEditTextValue("99", true))
        assertNotEquals("100", viewModel.updateEditTextValue("99"))
        assertNotEquals("101", viewModel.updateEditTextValue("99", true))
    }

    @Test
    fun testUpdateEditTextValueDecrement() {
        assertEquals("0", viewModel.updateEditTextValue("1"))
        assertEquals("0", viewModel.updateEditTextValue("0"))
        assertEquals("0", viewModel.updateEditTextValue(""))
        assertEquals("98", viewModel.updateEditTextValue("99"))
        assertNotEquals("98", viewModel.updateEditTextValue("99", true))
        assertNotEquals("97", viewModel.updateEditTextValue("99"))
    }

    @Test
    fun testCheckIfTextsAreEmptyOrZero() {
        assertTrue(viewModel.checkIfTextsAreEmptyOrZero(null, null))
        assertTrue(viewModel.checkIfTextsAreEmptyOrZero(null, ""))
        assertTrue(viewModel.checkIfTextsAreEmptyOrZero("", null))
        assertTrue(viewModel.checkIfTextsAreEmptyOrZero("", ""))
        assertTrue(viewModel.checkIfTextsAreEmptyOrZero("", "1"))
        assertTrue(viewModel.checkIfTextsAreEmptyOrZero("Shoes", "0"))
        assertTrue(viewModel.checkIfTextsAreEmptyOrZero("Shoes", null))
        assertFalse(viewModel.checkIfTextsAreEmptyOrZero("Shoes", "1"))
    }
}