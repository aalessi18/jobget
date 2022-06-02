package com.example.jobget.helper

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.jobget.model.TransactionModel
import com.google.gson.Gson
import org.json.JSONArray

object UserDataHelper {
    private val userTransactions = mutableMapOf<String, MutableList<TransactionModel>>()

    fun addToTransactions(activity: Activity, date: String, transactionModel: TransactionModel) {
        val sharedPreferences: SharedPreferences = activity.getPreferences(MODE_PRIVATE)
        if (!userTransactions.containsKey(date)) {
            userTransactions[date] = mutableListOf(transactionModel)
        } else {
            userTransactions[date]?.add(transactionModel)
        }
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