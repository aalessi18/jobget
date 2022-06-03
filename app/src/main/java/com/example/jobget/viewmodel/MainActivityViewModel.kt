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

    fun getTransactions(activity: Activity): Map<String, List<TransactionModel>>? {
        return repository.getTransactions(activity)
    }

    fun addTransaction(activity: Activity, date: String, model: TransactionModel) {
        repository.addTransaction(activity, date, model)
    }
}