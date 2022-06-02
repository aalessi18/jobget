package com.example.jobget.repository

import android.app.Activity
import com.example.jobget.helper.UserDataHelper
import com.example.jobget.model.TransactionModel
import javax.inject.Singleton
import org.json.JSONArray

@Singleton
class UserDataRepository {

    fun getTransactions(activity: Activity) : JSONArray? {
        return UserDataHelper().getTransactions(activity)
    }

    fun addTransaction(activity: Activity, date: String, model: TransactionModel) {
        UserDataHelper().addToTransactions(activity, date, model)
    }
}