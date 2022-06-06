package com.example.jobget.util

import com.example.jobget.model.TransactionType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TransactionUtilTest {

    @Test
    fun testIsTransactionTypeIncome() {
        assertTrue(isTransactionTypeIncome(TransactionType.INCOME))
        assertFalse(isTransactionTypeIncome(TransactionType.EXPENSE))
    }

    @Test
    fun testIsTransactionTypeExpense() {
        assertTrue(isTransactionTypeExpense(TransactionType.EXPENSE))
        assertFalse(isTransactionTypeExpense(TransactionType.INCOME))
    }

    @Test
    fun testGetTransactionType() {
        assertEquals(TransactionType.INCOME, getTransactionType("income"))
        assertEquals(TransactionType.INCOME, getTransactionType("Income"))
        assertNotEquals(TransactionType.INCOME, getTransactionType("expense"))
        assertNotEquals(TransactionType.INCOME, getTransactionType(""))

        assertEquals(TransactionType.EXPENSE, getTransactionType("expense"))
        assertEquals(TransactionType.EXPENSE, getTransactionType("Expense"))
        assertNotEquals(TransactionType.EXPENSE, getTransactionType("income"))
        assertNotEquals(TransactionType.EXPENSE, getTransactionType(""))
        assertEquals(null, getTransactionType(""))
    }
}