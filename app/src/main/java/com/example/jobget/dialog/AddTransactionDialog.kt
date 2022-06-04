package com.example.jobget.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Display
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.jobget.R
import com.example.jobget.databinding.DialogAddTransactionBinding


class AddTransactionDialog(context: Context, private val display: Display) : Dialog(context) {
    private lateinit var binding: DialogAddTransactionBinding
    private lateinit var spinnerTransactionType: Spinner
    private lateinit var editTextDescription: EditText
    private lateinit var editTextDollarAmount: EditText
    private lateinit var buttonAdd: Button
    private lateinit var buttonCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAddTransactionBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        initComponents()
    }

    private fun initComponents() {
        setDialogSize()
        setViewBindings()
        setSpinnerContent()
        setCancelButtonOnClickListener()
    }

    private fun setDialogSize() {
        val displayMetrics = DisplayMetrics()
        display.getMetrics(displayMetrics)
        window?.setLayout(display.width, ((display.height * 0.5).toInt()))
    }

    private fun setViewBindings() {
        binding.apply {
            spinnerTransactionType = spTransactionType
            editTextDescription = etTransactionDescription
            editTextDollarAmount = clDollarContainer.etDollarAmount
            buttonAdd = btnAdd
            buttonCancel = btnCancel
        }
    }

    private fun setSpinnerContent() {
        ArrayAdapter.createFromResource(
            context,
            R.array.transaction_type_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerTransactionType.adapter = adapter
        }
    }

    private fun setCancelButtonOnClickListener() {
        buttonCancel.setOnClickListener {
            onBackPressed()
        }
    }
}