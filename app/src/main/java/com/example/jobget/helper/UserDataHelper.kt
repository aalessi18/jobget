package com.example.jobget.helper

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.jobget.model.TransactionModel
import com.example.jobget.model.TransactionType
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataHelper @Inject constructor() {
    private val userTransactions = mutableMapOf<String, MutableList<TransactionModel>>()

    fun addToTransactions(activity: Activity, date: String, transactionModel: TransactionModel) {
        val sharedPreferences: SharedPreferences = activity.getPreferences(MODE_PRIVATE)
        if (!userTransactions.containsKey(date)) {
            userTransactions[date] = mutableListOf(transactionModel)
        } else {
            userTransactions[date]?.add(transactionModel)
        }
        addToSharedPreferences(sharedPreferences)
    }

    fun getTransactions(activity: Activity): Map<String, List<TransactionModel>>? {
        val sharedPreferences: SharedPreferences = activity.getPreferences(MODE_PRIVATE)
        if (userTransactions.isNullOrEmpty()) {
            val currentDate = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(Date())
            userTransactions[currentDate] = mutableListOf(
                TransactionModel("Income", "1000", TransactionType.INCOME),
                TransactionModel("Shoes", "70", TransactionType.EXPENSE),
            )
            addToSharedPreferences(sharedPreferences)
        }
        val json = sharedPreferences.getString("transactions", null)
        return Gson().fromJson(json, Map::class.java) as Map<String, List<TransactionModel>>
    }

    private fun addToSharedPreferences(sharedPreferences: SharedPreferences) {
        val jsonObject = Gson().toJson(userTransactions)
        with(sharedPreferences.edit()) {
            putString("transactions", jsonObject)
            commit()
        }
    }
}