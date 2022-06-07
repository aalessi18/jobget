package com.example.jobget.util

import android.graphics.Color
import com.example.jobget.model.TransactionModel
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

    @Test
    fun testGetTransactionLabelColor() {
        assertEquals(Color.GREEN, getTransactionLabelColor(TransactionType.INCOME))
        assertEquals(Color.RED, getTransactionLabelColor(TransactionType.EXPENSE))
    }

    @Test
    fun testGetTotal() {
        val map = mutableMapOf<String, MutableList<TransactionModel>>()
        map["03 Jun, 2022"] = mutableListOf(
            TransactionModel("Shoes", "90", TransactionType.EXPENSE),
            TransactionModel("Pens", "10", TransactionType.EXPENSE),
            TransactionModel("Laptop", "100", TransactionType.EXPENSE),
            TransactionModel("Salary", "1000", TransactionType.INCOME),
            TransactionModel("Dividends", "50", TransactionType.INCOME)
        )
        assertEquals(200, getTotal(map))
        assertEquals(1050, getTotal(map, true))
    }

    @Test
    fun testGetBalanceTotal() {
        val map = mutableMapOf<String, MutableList<TransactionModel>>()
        map["03 Jun, 2022"] = mutableListOf(
            TransactionModel("Shoes", "90", TransactionType.EXPENSE),
            TransactionModel("Pens", "10", TransactionType.EXPENSE),
            TransactionModel("Laptop", "100", TransactionType.EXPENSE),
            TransactionModel("Salary", "1000", TransactionType.INCOME),
            TransactionModel("Dividends", "50", TransactionType.INCOME)
        )
        assertEquals(850, getBalance(map))
    }
}