package com.example.jobget.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jobget.R
import com.example.jobget.model.TransactionModel


// date, list of transactions, list of amounts
// key is date -> value is key value pair (string, string)
// Map<String, TransactionModel>
class RowContainerAdapter(private val mapOfTransactions: Map<String, List<TransactionModel>>) :
    RecyclerView.Adapter<RowContainerAdapter.RowViewHolder>() {

    companion object {
        private const val ROW_IS_DATE = 0
        private const val ROW_IS_TRANSACTION = 1
        private const val ROW_IS_DIVIDER = 2
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RowContainerAdapter.RowViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_list_data, parent, false)

        return RowViewHolder(view)
    }

    override fun onBindViewHolder(holder: RowContainerAdapter.RowViewHolder, position: Int) {
//        holder.tvListRowLabel.text = listOfTransactions.keys.toTypedArray()[position]
    }

    override fun getItemCount(): Int {
        return mapOfTransactions.size
    }

//    override fun getItemViewType(position: Int): Int {
//        return if (position == 0) ROW_IS_DATE else ROW_IS_TRANSACTION
//    }

    inner class RowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDate = itemView.findViewById<TextView>(R.id.tv_row_date)
        val rvTransactionList = itemView.findViewById<RecyclerView>(R.id.rv_transactions)
    }
}