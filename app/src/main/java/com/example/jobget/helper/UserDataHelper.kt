package com.example.jobget.helper

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.jobget.model.TransactionModel
import com.google.gson.Gson
import org.json.JSONArray

object UserDataHelper {
    private val userTransactions = mutableListOf<TransactionModel>()

    fun addToTransactions(activity: Activity, transactionModel: TransactionModel) {
        val sharedPreferences: SharedPreferences = activity.getPreferences(MODE_PRIVATE)
        userTransactions.add(transactionModel)
        val jsonObject = Gson().toJson(userTransactions)
        with(sharedPreferences.edit()) {
            putString("transactions", jsonObject)
            commit()
        }
    }

    fun getTransactions(activity: Activity): JSONArray? {
        val sharedPreferences: SharedPreferences = activity.getPreferences(MODE_PRIVATE)
        return sharedPreferences.getString("transactions", null) as JSONArray?
    }
}