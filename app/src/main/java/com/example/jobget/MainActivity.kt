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
import com.google.android.material.progressindicator.LinearProgressIndicator
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
    private lateinit var lpiBalanceBar: LinearProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
    }

    private fun initComponents() {
        setViewBindings()
        setBalanceContainerHeadings()
        setBalanceContainerValues()
    }

    override fun onResume() {
        super.onResume()
        setRecyclerViewList()
    }

    private fun setViewBindings() {
        binding.apply {
            tvExpensesHeading = clBalanceContainer.clExpenses.tvHeading
            tvExpensesLabel = clBalanceContainer.clExpenses.tvLabel
            tvIncomeHeading = clBalanceContainer.clIncome.tvHeading
            tvIncomeLabel = clBalanceContainer.clIncome.tvLabel
            tvBalanceHeading = clBalanceContainer.clBalance.tvHeading
            tvBalanceLabel = clBalanceContainer.clBalance.tvLabel
            lpiBalanceBar = clBalanceContainer.lpiBalanceIndicator
            rvTransactions = rvTransactionsContainer
            setFloatingActionButton(this)
        }
    }

    private fun setFloatingActionButton(binding: ActivityMainBinding) {
        fabAddButton = binding.fabButton
        fabAddButton.setOnClickListener {
            openDialog()
        }
    }

    private fun setBalanceContainerHeadings() {
        tvExpensesHeading.text = getString(R.string.expense_text_heading)
        tvIncomeHeading.text = getString(R.string.income_text_heading)
        tvBalanceHeading.text = getString(R.string.balance_text_heading)
    }

    private fun setBalanceContainerValues() {
        val incomeTotal = viewModel.getIncomeTotal(this)
        val expenseTotal = viewModel.getExpenseTotal(this)
        tvExpensesLabel.text = expenseTotal
        tvIncomeLabel.text = incomeTotal
        tvBalanceLabel.text = viewModel.getBalanceTotal(this)
        lpiBalanceBar.max = incomeTotal.toInt()
        lpiBalanceBar.progress = expenseTotal.toInt()
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
        setBalanceContainerValues()
    }
}