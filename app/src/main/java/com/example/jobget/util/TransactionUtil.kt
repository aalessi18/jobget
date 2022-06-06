package com.example.jobget.util

import com.example.jobget.model.TransactionType

public fun isTransactionTypeIncome(transactionType: TransactionType): Boolean =
    transactionType == TransactionType.INCOME

public fun isTransactionTypeExpense(transactionType: TransactionType): Boolean =
    transactionType == TransactionType.EXPENSE