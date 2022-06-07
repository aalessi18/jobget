package com.example.jobget.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jobget.databinding.RowListDataBinding
import com.example.jobget.interfaces.SwipeGestureListener
import com.example.jobget.model.TransactionModel


class RowContainerAdapter(
    private val context: Context,
    private val mapOfTransactions: MutableMap<String, MutableList<TransactionModel>>,
    private val swipeGestureListener: SwipeGestureListener
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
        mapOfTransactions[dateKeyString]?.let {
            if (it.isNotEmpty()) {
                holder.tvDate.text = dateKeyString
                holder.rvTransactionList.layoutManager = LinearLayoutManager(context)
                val adapter = TransactionAdapter(context, it, dateKeyString, swipeGestureListener)
                adapter.getItemTouchListener().attachToRecyclerView(holder.rvTransactionList)
                holder.rvTransactionList.adapter = adapter
            } else {
//                mapOfTransactions.remove(dateKeyString)
            }
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