package com.example.jobget.interfaces

import android.app.Activity
import com.example.jobget.model.TransactionModel

interface UserDataRepositoryInterface {
    fun getTransactions(activity: Activity) : Map<String, List<TransactionModel>>?
    fun addTransaction(activity: Activity, date: String, model: TransactionModel)
}