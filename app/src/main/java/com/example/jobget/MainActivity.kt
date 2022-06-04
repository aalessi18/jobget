package com.example.jobget

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jobget.adapter.RowContainerAdapter
import com.example.jobget.databinding.ActivityMainBinding
import com.example.jobget.dialog.AddTransactionFragmentDialog
import com.example.jobget.interfaces.AddButtonListener
import com.example.jobget.viewmodel.MainActivityViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), AddButtonListener {
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private lateinit var tvExpensesHeading: TextView
    private lateinit var tvExpensesLabel: TextView
    private lateinit var tvIncomeHeading: TextView
    private lateinit var tvIncomeLabel: TextView
    private lateinit var tvBalanceHeading: TextView
    private lateinit var tvBalanceLabel: TextView
    private lateinit var rvTransactions: RecyclerView
    private lateinit var fabAddButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
    }

    private fun initComponents() {
        setViewBindings()
        setBalanceContainerHeadings()
    }

    override fun onResume() {
        super.onResume()
        setRecyclerViewList()
        setBalanceContainerValues()
    }

    private fun setViewBindings() {
        binding.apply {
            tvExpensesHeading = clBalanceContainer.clExpenses.tvHeading
            tvExpensesLabel = clBalanceContainer.clExpenses.tvLabel
            tvIncomeHeading = clBalanceContainer.clIncome.tvHeading
            tvIncomeLabel = clBalanceContainer.clIncome.tvLabel
            tvBalanceHeading = clBalanceContainer.clBalance.tvHeading
            tvBalanceLabel = clBalanceContainer.clBalance.tvLabel
            rvTransactions = rvTransactionsContainer
            fabAddButton = fabButton
            fabAddButton.setOnClickListener {
                openDialog()
            }
        }
    }

    private fun setBalanceContainerHeadings() {
        tvExpensesHeading.text = "Expenses"
        tvIncomeHeading.text = "Income"
        tvBalanceHeading.text = "Balance"
    }

    private fun setBalanceContainerValues() {
        tvExpensesLabel.text = viewModel.getExpenseTotal(this)
        tvIncomeLabel.text = viewModel.getIncomeTotal(this)
        tvBalanceLabel.text = viewModel.getBalanceTotal(this)
    }

    private fun openDialog() {
        val dialog = AddTransactionFragmentDialog(viewModel, this)
        dialog.show(supportFragmentManager, AddTransactionFragmentDialog.TAG)
    }

    override fun setRecyclerViewList() {
        viewModel.getTransactions(this)?.let {
            val adapter = RowContainerAdapter(this, it)
            rvTransactions.layoutManager = LinearLayoutManager(this)
            rvTransactions.adapter = adapter
        }
        rvTransactions.adapter?.notifyDataSetChanged()
    }
}