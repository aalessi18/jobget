package com.example.jobget.interfaces

import com.example.jobget.model.TransactionModel


interface AddButtonListener {
    fun setRecyclerViewList(transactionModel: TransactionModel? = null)
}