package com.example.jobget.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobget.databinding.RowTransactionDataBinding
import com.example.jobget.model.TransactionModel
import com.example.jobget.util.getTransactionLabel
import com.example.jobget.util.getTransactionLabelColor

class TransactionAdapter(
    private val context: Context,
    private val listOfTransactions: List<TransactionModel>
) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view =
            RowTransactionDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.tvTransactionTitle.text = listOfTransactions[position].transactionTitle
        setViewHolderData(
            holder,
            getTransactionLabel(listOfTransactions[position].transactionType),
            position,
            getTransactionLabelColor(listOfTransactions[position].transactionType)
        )
    }

    override fun getItemCount(): Int {
        return listOfTransactions.size
    }

    private fun setViewHolderData(
        viewHolder: TransactionViewHolder,
        stringId: Int,
        position: Int,
        colorId: Int
    ) {
        viewHolder.tvTransactionAmount.text = context.getString(
            stringId,
            listOfTransactions[position].transactionAmount
        )
        viewHolder.tvTransactionTitle.setTextColor(colorId)
        viewHolder.tvTransactionAmount.setTextColor(colorId)
    }

    inner class TransactionViewHolder(binding: RowTransactionDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvTransactionTitle: TextView = binding.tvTransactionLabel
        val tvTransactionAmount: TextView = binding.tvTransactionAmount
    }
}