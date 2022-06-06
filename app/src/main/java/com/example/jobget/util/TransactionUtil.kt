package com.example.jobget.util

import android.graphics.Color
import com.example.jobget.R
import com.example.jobget.model.TransactionModel
import com.example.jobget.model.TransactionType

fun isTransactionTypeIncome(transactionType: TransactionType): Boolean =
    transactionType == TransactionType.INCOME

fun isTransactionTypeExpense(transactionType: TransactionType): Boolean =
    transactionType == TransactionType.EXPENSE

fun getTransactionType(textValue: String): TransactionType? {
    return when {
        (textValue.equals(TransactionType.INCOME.name, true)) -> TransactionType.INCOME
        (textValue.equals(TransactionType.EXPENSE.name, true)) -> TransactionType.EXPENSE
        else -> null
    }
}

fun getTransactionLabel(transactionType: TransactionType) =
    if (isTransactionTypeIncome(transactionType)) R.string.income_cost_label else R.string.expense_cost_label

fun getTransactionLabelColor(transactionType: TransactionType) =
    if (isTransactionTypeIncome(transactionType)) Color.GREEN else Color.RED

fun getTotal(
    jsonData: MutableMap<String, MutableList<TransactionModel>>,
    isIncomeTotal: Boolean = false
): Int {
    var total = 0
    jsonData.forEach { (_, mutableList) ->
        mutableList.forEach {
            when (isIncomeTotal) {
                true -> {
                    if (isTransactionTypeIncome(it.transactionType)) {
                        total += it.transactionAmount.toInt()
                    }
                }
                else -> {
                    if (isTransactionTypeExpense(it.transactionType)) {
                        total += it.transactionAmount.toInt()
                    }
                }
            }
        }
    }
    return total
}

fun getBalance(jsonData: MutableMap<String, MutableList<TransactionModel>>): Int {
    var totalBalance = 0;
    jsonData.forEach { (_, mutableList) ->
        mutableList.forEach {
            when (isTransactionTypeIncome(it.transactionType)) {
                true -> totalBalance += it.transactionAmount.toInt()
                else -> totalBalance -= it.transactionAmount.toInt()
            }
        }
    }
    return totalBalance
}