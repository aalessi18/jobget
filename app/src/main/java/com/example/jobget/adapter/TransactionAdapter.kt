package com.example.jobget.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobget.R
import com.example.jobget.model.TransactionModel
import com.example.jobget.model.TransactionType

class TransactionAdapter(private val listOfTransactions: List<TransactionModel>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_transaction_data, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.tvTransactionTitle.text = listOfTransactions[position].transactionTitle
        holder.tvTransactionAmount.text =
            if (listOfTransactions[position].transactionType == TransactionType.INCOME) {
                "$${listOfTransactions[position].transactionAmount}"
            } else {
                "- $${listOfTransactions[position].transactionAmount}"
            }

    }

    override fun getItemCount(): Int {
        return listOfTransactions.size
    }

    inner class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTransactionTitle: TextView = itemView.findViewById(R.id.tv_transaction_label)
        val tvTransactionAmount: TextView = itemView.findViewById(R.id.tv_transaction_amount)
    }
}