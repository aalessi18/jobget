package com.example.jobget.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobget.databinding.RowTransactionDataBinding
import com.example.jobget.model.TransactionModel
import com.example.jobget.util.isTransactionTypeIncome

class TransactionAdapter(private val listOfTransactions: List<TransactionModel>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view =
            RowTransactionDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.tvTransactionTitle.text = listOfTransactions[position].transactionTitle
        if (isTransactionTypeIncome(listOfTransactions[position].transactionType)) {
            holder.tvTransactionAmount.text = "$${listOfTransactions[position].transactionAmount}"
            holder.tvTransactionTitle.setTextColor(Color.GREEN)
            holder.tvTransactionAmount.setTextColor(Color.GREEN)
        } else {
            holder.tvTransactionAmount.text = "- $${listOfTransactions[position].transactionAmount}"
            holder.tvTransactionTitle.setTextColor(Color.RED)
            holder.tvTransactionAmount.setTextColor(Color.RED)
        }
    }

    override fun getItemCount(): Int {
        return listOfTransactions.size
    }

    inner class TransactionViewHolder(binding: RowTransactionDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvTransactionTitle: TextView = binding.tvTransactionLabel
        val tvTransactionAmount: TextView = binding.tvTransactionAmount
    }
}