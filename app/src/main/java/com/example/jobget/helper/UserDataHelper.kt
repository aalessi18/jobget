package com.example.jobget.helper

import android.app.Activity
import android.content.Context.MODE_PRIVATE
import android.content.DialogInterface
import android.content.SharedPreferences
import androidx.appcompat.app.AlertDialog
import com.example.jobget.R
import com.example.jobget.model.TransactionModel
import com.example.jobget.util.getBalance
import com.example.jobget.util.getTotal
import com.example.jobget.util.retrieveDate
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataHelper @Inject constructor() {

    companion object {
        private const val TRANSACTIONS_KEY = "transactions"
    }

    fun addToTransactions(
        activity: Activity,
        transactionModel: TransactionModel,
        date: String? = null
    ) {
        val submittedDate: String =
            retrieveDate(activity.getString(R.string.date_pattern), date)
        val sharedPreferences: SharedPreferences = activity.getPreferences(MODE_PRIVATE)
        val json = sharedPreferences.getString(TRANSACTIONS_KEY, null)
        val jsonData: MutableMap<String, MutableList<TransactionModel>>? =
            convertToMutableMapGsonFromJson(json)

        jsonData?.let {
            if (jsonData.containsKey(submittedDate)) {
                val list = jsonData[submittedDate]
                list?.add(transactionModel)
            } else {
                jsonData[submittedDate] = mutableListOf(transactionModel)
            }
        }

        addToSharedPreferences(sharedPreferences, jsonData)
    }

    fun getTransactions(activity: Activity): MutableMap<String, MutableList<TransactionModel>>? {
        val json = getJsonFromSharedPreferences(activity)
        return convertToMapGsonFromJson(json)
    }

    fun deleteTransaction(
        activity: Activity,
        dateChosen: String,
        transactionModel: TransactionModel
    ) {
        val sharedPreferences: SharedPreferences = activity.getPreferences(MODE_PRIVATE)
        val json = sharedPreferences.getString(TRANSACTIONS_KEY, null)
        val jsonData: MutableMap<String, MutableList<TransactionModel>>? =
            convertToMutableMapGsonFromJson(json)
        jsonData?.let {
            if (jsonData.containsKey(dateChosen)) {
                val list = jsonData[dateChosen]
                list?.remove(transactionModel)
                it.keys.removeIf {
                    list?.isEmpty() ?: false
                }
                addToSharedPreferences(sharedPreferences, jsonData)
            } else {
                showErrorDialog(activity)
            }
        }
    }

    private fun showErrorDialog(activity: Activity): AlertDialog? {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(activity.getString(R.string.error_dialog_title))
        builder.setMessage(activity.getString(R.string.error_dialog_message))
        builder.setPositiveButton(activity.getString(R.string.ok_text)) { dialog: DialogInterface, _: Int -> dialog.dismiss() }
        return builder.show()
    }

    fun getExpenseTotal(activity: Activity): String {
        val jsonData: MutableMap<String, MutableList<TransactionModel>>? = getJsonData(activity)
        return getTotal(jsonData).toString()
    }

    fun getIncomeTotal(activity: Activity): String {
        val jsonData: MutableMap<String, MutableList<TransactionModel>>? = getJsonData(activity)
        return getTotal(jsonData, true).toString()
    }

    fun getBalanceTotal(activity: Activity): String {
        val jsonData: MutableMap<String, MutableList<TransactionModel>>? = getJsonData(activity)
        return getBalance(jsonData).toString()
    }

    private fun getJsonData(activity: Activity): MutableMap<String, MutableList<TransactionModel>>? {
        val json = getJsonFromSharedPreferences(activity)
        return convertToMutableMapGsonFromJson(json)
    }

    private fun getJsonFromSharedPreferences(activity: Activity): String? {
        val sharedPreferences: SharedPreferences = activity.getPreferences(MODE_PRIVATE)
        return sharedPreferences.getString(TRANSACTIONS_KEY, null)
    }

    private fun addToSharedPreferences(
        sharedPreferences: SharedPreferences,
        jsonData: MutableMap<String, MutableList<TransactionModel>>?
    ) {
        with(sharedPreferences.edit()) {
            putString(TRANSACTIONS_KEY, Gson().toJson(jsonData))
            commit()
        }
    }

    private fun convertToMapGsonFromJson(json: String?): MutableMap<String, MutableList<TransactionModel>> =
        json?.let {
            Gson().fromJson(
                json,
                object : TypeToken<Map<String, MutableList<TransactionModel>>>() {}.type
            )
        } ?: HashMap()

    private fun convertToMutableMapGsonFromJson(json: String?): MutableMap<String, MutableList<TransactionModel>>? =
        json?.let {
            Gson().fromJson(
                json,
                object : TypeToken<MutableMap<String, MutableList<TransactionModel>>>() {}.type
            )
        } ?: HashMap()
}