package com.example.jobget.repository

import android.app.Activity
import com.example.jobget.helper.UserDataHelper
import com.example.jobget.interfaces.UserDataRepositoryInterface
import com.example.jobget.model.TransactionModel
import javax.inject.Inject


class UserDataRepositoryImplementation @Inject constructor() : UserDataRepositoryInterface {
    override fun getTransactions(activity: Activity): Map<String, List<TransactionModel>> {
        return UserDataHelper().getTransactions(activity)
    }

    override fun addTransaction(activity: Activity, model: TransactionModel, date: String?) {
        UserDataHelper().addToTransactions(activity, model, date)
    }

    override fun getExpenseTotal(activity: Activity) : String{
        return UserDataHelper().getExpenseTotal(activity)
    }

    override fun getIncomeTotal(activity: Activity) : String{
        return UserDataHelper().getIncomeTotal(activity)
    }

    override fun getBalanceTotal(activity: Activity) : String{
        return UserDataHelper().getBalanceTotal(activity)
    }
}