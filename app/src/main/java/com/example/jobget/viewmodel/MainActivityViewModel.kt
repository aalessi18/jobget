package com.example.jobget.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.example.jobget.model.TransactionModel
import com.example.jobget.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import org.json.JSONArray

@HiltViewModel
class MainActivityViewModel : ViewModel() {
    @Inject
    private var repository = UserDataRepository()

    fun getTransactions(activity: Activity): JSONArray? {
        return repository.getTransactions(activity)
    }

    fun addTransaction(activity: Activity, date: String, model: TransactionModel) {
        repository.addTransaction(activity, date, model)
    }
}