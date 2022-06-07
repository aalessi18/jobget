package com.example.jobget.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.example.jobget.interfaces.UserDataRepositoryInterface
import com.example.jobget.model.TransactionModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: UserDataRepositoryInterface) :
    ViewModel() {

    fun getTransactions(activity: Activity): MutableMap<String, MutableList<TransactionModel>>? {
        return repository.getTransactions(activity)
    }

    fun addTransaction(activity: Activity, model: TransactionModel, date: String? = null) {
        repository.addTransaction(activity, model, date)
    }

    fun deleteTransaction(activity: Activity, dateChosen: String, model: TransactionModel) {
        repository.deleteTransaction(activity, dateChosen, model)
    }

    fun getExpenseTotal(activity: Activity): String {
        return repository.getExpenseTotal(activity)
    }

    fun getIncomeTotal(activity: Activity): String {
        return repository.getIncomeTotal(activity)
    }

    fun getBalanceTotal(activity: Activity): String {
        return repository.getBalanceTotal(activity)
    }

    fun getDefaultIncome(value: Int) : Int {
        return when (value == 0 || value < 0) {
            true -> 1
            else -> value
        }
    }
}