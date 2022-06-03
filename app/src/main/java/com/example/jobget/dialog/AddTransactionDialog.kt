package com.example.jobget.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Display
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.example.jobget.R
import com.example.jobget.databinding.DialogAddTransactionBinding


class AddTransactionDialog(context: Context, private val display: Display) : Dialog(context) {
    private lateinit var binding: DialogAddTransactionBinding
    private lateinit var activity: Activity
    private lateinit var defaultDisplay: Display
    private lateinit var spinnerTransactionType: Spinner
    private lateinit var editTextTitle: EditText
    private lateinit var editTextAmount: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_transaction)
        initComponents()
    }

    private fun initComponents() {
        spinnerTransactionType = findViewById(R.id.sp_transaction_type)
        ArrayAdapter.createFromResource(
            context,
            R.array.transaction_type_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerTransactionType.adapter = adapter
        }
        btnCancel = findViewById(R.id.btn_cancel)
        btnCancel.setOnClickListener {
            onBackPressed()
        }
    }
}