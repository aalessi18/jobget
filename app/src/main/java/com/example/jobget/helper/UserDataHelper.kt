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

    private fun addToSharedPreferences(sharedPreferences: SharedPreferences, jsonData: MutableMap<String, MutableList<TransactionModel>>) {
        with(sharedPreferences.edit()) {
            putString("transactions", Gson().toJson(jsonData))
            commit()
        }
    }
}