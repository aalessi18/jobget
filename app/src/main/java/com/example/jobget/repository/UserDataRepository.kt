package com.example.jobget.repository

import android.app.Activity
import com.example.jobget.helper.UserDataHelper
import com.example.jobget.interfaces.UserDataRepositoryInterface
import com.example.jobget.model.TransactionModel
import javax.inject.Inject
import org.json.JSONArray


class UserDataRepositoryImplementation @Inject constructor() : UserDataRepositoryInterface {
    override fun getTransactions(activity: Activity): JSONArray? {
        return UserDataHelper().getTransactions(activity)
    }

    override fun addTransaction(activity: Activity, date: String, model: TransactionModel) {
        UserDataHelper().addToTransactions(activity, date, model)
    }
}