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
    private val userTransactions = mutableMapOf<String, MutableList<TransactionModel>>()

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
        if (!userTransactions.containsKey(submittedDate)) {
            userTransactions[submittedDate] = mutableListOf(transactionModel)
        } else {
            userTransactions[submittedDate]?.add(transactionModel)
        }
        addToSharedPreferences(sharedPreferences)
    }

    fun getTransactions(activity: Activity): Map<String, List<TransactionModel>> {
        val sharedPreferences: SharedPreferences = activity.getPreferences(MODE_PRIVATE)
        if (sharedPreferences.getString("transactions", null) == null) {
            val currentDate = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(Date())
            userTransactions[currentDate] = mutableListOf(
                TransactionModel("Income", "1000", TransactionType.INCOME),
                TransactionModel("Shoes", "70", TransactionType.EXPENSE),
                TransactionModel("Income", "1000", TransactionType.INCOME),
                TransactionModel("Shoes", "70", TransactionType.EXPENSE),
                TransactionModel("Income", "1000", TransactionType.INCOME),
                TransactionModel("Shoes", "70", TransactionType.EXPENSE),
                TransactionModel("Income", "1000", TransactionType.INCOME),
                TransactionModel("Shoes", "70", TransactionType.EXPENSE),
                TransactionModel("Income", "1000", TransactionType.INCOME),
                TransactionModel("Shoes", "70", TransactionType.EXPENSE),
                TransactionModel("Income", "1000", TransactionType.INCOME),
                TransactionModel("Shoes", "70", TransactionType.EXPENSE),
                TransactionModel("Income", "1000", TransactionType.INCOME),
                TransactionModel("Shoes", "70", TransactionType.EXPENSE),
                TransactionModel("Income", "1000", TransactionType.INCOME),
                TransactionModel("Shoes", "70", TransactionType.EXPENSE),
                TransactionModel("Income", "1000", TransactionType.INCOME),
                TransactionModel("Shoes", "70", TransactionType.EXPENSE),
                TransactionModel("Income", "1000", TransactionType.INCOME),
                TransactionModel("Shoes", "70", TransactionType.EXPENSE),
                TransactionModel("Income", "1000", TransactionType.INCOME),
                TransactionModel("Shoes", "70", TransactionType.EXPENSE),
                TransactionModel("Income", "1000", TransactionType.INCOME),
                TransactionModel("Shoes", "70", TransactionType.EXPENSE),
                TransactionModel("Income", "1000", TransactionType.INCOME),
                TransactionModel("Shoes", "70", TransactionType.EXPENSE),
                TransactionModel("Income", "1000", TransactionType.INCOME),
                TransactionModel("Shoes", "70", TransactionType.EXPENSE),
                TransactionModel("Income", "1000", TransactionType.INCOME),
                TransactionModel("Shoes", "70", TransactionType.EXPENSE),
                TransactionModel("Income", "1000", TransactionType.INCOME),
                TransactionModel("Shoes", "70", TransactionType.EXPENSE),
                TransactionModel("Income", "1000", TransactionType.INCOME),
                TransactionModel("Shoes", "70", TransactionType.EXPENSE),
            )
            addToSharedPreferences(sharedPreferences)
        }
        val json = sharedPreferences.getString("transactions", null)
        return Gson().fromJson(
            json,
            object : TypeToken<Map<String, List<TransactionModel>>>() {}.type
        )
    }

    private fun addToSharedPreferences(sharedPreferences: SharedPreferences) {
        val jsonObject = Gson().toJson(userTransactions)
        with(sharedPreferences.edit()) {
            putString("transactions", jsonObject)
            commit()
        }
    }
}

//            addToSharedPreferences(sharedPreferences)
//            val jsonObject = Gson().toJson(userTransactions)
//            val json = sharedPreferences.getString("transactions", null)
//            val jsonData: MutableMap<String, MutableList<TransactionModel>> = Gson().fromJson(
//                json,
//                object : TypeToken<MutableMap<String, MutableList<TransactionModel>>>() {}.type
//            )
//
//            if (jsonData.containsKey(currentDate)) {
//                val list = jsonData[currentDate]
//                list?.add(TransactionModel("Income", "500", TransactionType.INCOME),)
//            } else {
//
//            }