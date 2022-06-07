package com.example.jobget.model

enum class TransactionType { INCOME, EXPENSE }

data class TransactionModel(
    val transactionTitle: String,
    val transactionAmount: String,
    val transactionType: TransactionType
)
