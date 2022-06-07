package com.example.jobget.interfaces

import android.app.Activity
import com.example.jobget.model.TransactionModel

interface UserDataRepositoryInterface {
    fun getTransactions(activity: Activity): MutableMap<String, MutableList<TransactionModel>>?
    fun addTransaction(activity: Activity, model: TransactionModel, date: String? = null)
    fun deleteTransaction(
        activity: Activity,
        dateChosen: String,
        transactionModel: TransactionModel
    )

    fun getExpenseTotal(activity: Activity): String
    fun getIncomeTotal(activity: Activity): String
    fun getBalanceTotal(activity: Activity): String
}