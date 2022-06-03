package com.example.jobget.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Display
import android.widget.Button
import android.widget.EditText


class AddTransactionDialog(context: Context) : Dialog(context) {
    private lateinit var activity: Activity
    private lateinit var defaultDisplay: Display
    private lateinit var editTextTitle: EditText
    private lateinit var editTextAmount: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initComponents()
    }

    private fun initComponents() {
        TODO("Not yet implemented")
    }
}