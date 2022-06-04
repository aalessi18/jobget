package com.example.jobget.helper

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.jobget.model.TransactionModel
import com.example.jobget.model.TransactionType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataHelper @Inject constructor() {

    fun addToTransactions(
        activity: Activity,
        transactionModel: TransactionModel,
        date: String? = null
    ) {
        val sharedPreferences: SharedPreferences = activity.getPreferences(MODE_PRIVATE)
        val submittedDate: String = if (date.isNullOrEmpty()) {
            SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(Date())
        } else {
            date
        }

        val json = sharedPreferences.getString("transactions", null)
        val jsonData: MutableMap<String, MutableList<TransactionModel>> = Gson().fromJson(
            json,
            object : TypeToken<MutableMap<String, MutableList<TransactionModel>>>() {}.type
        )

        if (jsonData.containsKey(submittedDate)) {
            val list = jsonData[submittedDate]
            list?.add(transactionModel)
        } else {
            jsonData[submittedDate] = mutableListOf(transactionModel)
        }

        addToSharedPreferences(sharedPreferences, jsonData)
    }

    fun getTransactions(activity: Activity): Map<String, List<TransactionModel>> {
        val sharedPreferences: SharedPreferences = activity.getPreferences(MODE_PRIVATE)
        val json = sharedPreferences.getString("transactions", null)
        return Gson().fromJson(
            json,
            object : TypeToken<Map<String, List<TransactionModel>>>() {}.type
        )
    }

    fun getExpenseTotal(activity: Activity): String {
        val sharedPreferences: SharedPreferences = activity.getPreferences(MODE_PRIVATE)
        val json = sharedPreferences.getString("transactions", null)
        val jsonData: MutableMap<String, MutableList<TransactionModel>> = Gson().fromJson(
            json,
            object : TypeToken<MutableMap<String, MutableList<TransactionModel>>>() {}.type
        )

        var totalExpense = 0;
        jsonData.forEach { (_, mutableList) ->
            mutableList.forEach {
                if (it.transactionType == TransactionType.EXPENSE) {
                    totalExpense += it.transactionAmount.toInt()
                }
            }
        }
        return totalExpense.toString()
    }

    fun getIncomeTotal(activity: Activity): String {
        val sharedPreferences: SharedPreferences = activity.getPreferences(MODE_PRIVATE)
        val json = sharedPreferences.getString("transactions", null)
        val jsonData: MutableMap<String, MutableList<TransactionModel>> = Gson().fromJson(
            json,
            object : TypeToken<MutableMap<String, MutableList<TransactionModel>>>() {}.type
        )

        var totalIncome = 0;
        jsonData.forEach { (_, mutableList) ->
            mutableList.forEach {
                if (it.transactionType == TransactionType.INCOME) {
                    totalIncome += it.transactionAmount.toInt()
                }
            }
        }
        return totalIncome.toString()
    }

    fun getBalanceTotal(activity: Activity): String {
        val sharedPreferences: SharedPreferences = activity.getPreferences(MODE_PRIVATE)
        val json = sharedPreferences.getString("transactions", null)
        val jsonData: MutableMap<String, MutableList<TransactionModel>> = Gson().fromJson(
            json,
            object : TypeToken<MutableMap<String, MutableList<TransactionModel>>>() {}.type
        )

        var totalBalance = 0;
        jsonData.forEach { (_, mutableList) ->
            mutableList.forEach {
                when (it.transactionType == TransactionType.INCOME) {
                    true -> totalBalance += it.transactionAmount.toInt()
                    else -> totalBalance -= it.transactionAmount.toInt()
                }
            }
        }
        return totalBalance.toString()
    }

    private fun addToSharedPreferences(
        sharedPreferences: SharedPreferences,
        jsonData: MutableMap<String, MutableList<TransactionModel>>
    ) {
        with(sharedPreferences.edit()) {
            putString("transactions", Gson().toJson(jsonData))
            commit()
        }
    }
}