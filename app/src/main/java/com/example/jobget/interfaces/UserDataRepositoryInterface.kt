package com.example.jobget.interfaces

import android.app.Activity
import com.example.jobget.model.TransactionModel

interface UserDataRepositoryInterface {
    fun getTransactions(activity: Activity): Map<String, List<TransactionModel>>?
    fun addTransaction(activity: Activity, model: TransactionModel, date: String? = null)
    fun getExpenseTotal(activity: Activity): String
    fun getIncomeTotal(activity: Activity): String
    fun getBalanceTotal(activity: Activity): String
}