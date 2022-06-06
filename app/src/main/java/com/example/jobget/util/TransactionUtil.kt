package com.example.jobget.util

import com.example.jobget.model.TransactionModel
import com.example.jobget.model.TransactionType

fun isTransactionTypeIncome(transactionType: TransactionType): Boolean =
    transactionType == TransactionType.INCOME

fun isTransactionTypeExpense(transactionType: TransactionType): Boolean =
    transactionType == TransactionType.EXPENSE

fun getTransactionType(textValue: String) = if (textValue.equals(
        TransactionType.INCOME.name,
        true
    )
) TransactionType.INCOME else TransactionType.EXPENSE

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