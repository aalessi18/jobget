package com.example.jobget.interfaces

import android.app.Activity
import com.example.jobget.model.TransactionModel
import org.json.JSONArray

interface UserDataRepositoryInterface {
    fun getTransactions(activity: Activity) : JSONArray?
    fun addTransaction(activity: Activity, date: String, model: TransactionModel)
}