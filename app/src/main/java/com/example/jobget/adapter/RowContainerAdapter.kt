package com.example.jobget.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jobget.databinding.RowListDataBinding
import com.example.jobget.model.TransactionModel


class RowContainerAdapter(
    private val context: Context,
    private val mapOfTransactions: Map<String, List<TransactionModel>>
) :
    RecyclerView.Adapter<RowContainerAdapter.RowViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RowContainerAdapter.RowViewHolder {
        val view = RowListDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RowViewHolder(view)
    }

    override fun onBindViewHolder(holder: RowContainerAdapter.RowViewHolder, position: Int) {
        val dateKeyString = mapOfTransactions.keys.toTypedArray()[position]
        holder.tvDate.text = dateKeyString
        mapOfTransactions[dateKeyString]?.let {
            holder.rvTransactionList.layoutManager = LinearLayoutManager(context)
            holder.rvTransactionList.adapter = TransactionAdapter(context, it)
        }
    }

    override fun getItemCount(): Int {
        return mapOfTransactions.size
    }

    inner class RowViewHolder(binding: RowListDataBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvDate: TextView = binding.tvRowDate
        val rvTransactionList: RecyclerView = binding.rvTransactions
    }
}