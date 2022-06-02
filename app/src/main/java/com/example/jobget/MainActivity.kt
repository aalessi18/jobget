package com.example.jobget

import android.app.Application
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.jobget.databinding.ActivityMainBinding
import com.example.jobget.viewmodel.MainActivityViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
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
        binding.apply {
            tvExpensesHeading = clBalanceContainer.clExpenses.tvHeading
            tvExpensesLabel = clBalanceContainer.clExpenses.tvLabel
            tvIncomeHeading = clBalanceContainer.clIncome.tvHeading
            tvIncomeLabel = clBalanceContainer.clIncome.tvLabel
            tvBalanceHeading = clBalanceContainer.clBalance.tvHeading
            tvBalanceLabel = clBalanceContainer.clBalance.tvLabel
            rvTransactions = rvTransactionsView
            fabAddButton = fabButton
        }
    }
}