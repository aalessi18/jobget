package com.example.jobget.interfaces

import com.example.jobget.model.TransactionModel

interface SwipeGestureListener {
    fun swipeToDelete(dateChosen: String, transactionModel: TransactionModel)
}