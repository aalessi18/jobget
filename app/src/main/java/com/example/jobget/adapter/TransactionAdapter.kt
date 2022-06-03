package com.example.jobget.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jobget.model.TransactionModel

class TransactionAdapter(private val listOfTransactions: List<TransactionModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return listOfTransactions.size
    }
}