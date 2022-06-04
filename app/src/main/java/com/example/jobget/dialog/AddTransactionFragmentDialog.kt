package com.example.jobget.dialog

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.jobget.R
import com.example.jobget.databinding.DialogAddTransactionBinding
import com.example.jobget.model.TransactionModel
import com.example.jobget.model.TransactionType
import com.example.jobget.viewmodel.MainActivityViewModel

class AddTransactionFragmentDialog() : DialogFragment() {
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: DialogAddTransactionBinding
    private lateinit var spinnerTransactionType: Spinner
    private lateinit var editTextDescription: EditText
    private lateinit var editTextDollarAmount: EditText
    private lateinit var buttonAdd: Button
    private lateinit var buttonCancel: Button

    companion object {
        const val TAG = "AddTransactionFragmentDialog"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogAddTransactionBinding.inflate(LayoutInflater.from(context))
        initComponents()
        return binding.root
    }

    private fun initComponents() {
//        setDialogSize()
        setViewBindings()
        setSpinnerContent()
        setAddButtonOnClickListener()
        setCancelButtonOnClickListener()
    }

//    private fun setDialogSize() {
//        val displayMetrics = DisplayMetrics()
//        dialog?.window?.setLayout(, )
//    }

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
        context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.transaction_type_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerTransactionType.adapter = adapter
            }
        }
    }

    private fun setCancelButtonOnClickListener() {
        buttonCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun setAddButtonOnClickListener() {
        buttonAdd.setOnClickListener {
            if (editTextDescription.text.isNullOrEmpty() || editTextDollarAmount.text.isNullOrEmpty()) {
                Toast.makeText(context, "Please fill in all the elements", Toast.LENGTH_SHORT)
                    .show()
            } else {
                activity?.let {
                    viewModel.addTransaction(
                        it,
                        TransactionModel(
                            editTextDescription.text.toString(),
                            editTextDollarAmount.text.toString(),
                            if (spinnerTransactionType.selectedItem.toString()
                                    .equals(TransactionType.INCOME.name, true)
                            ) TransactionType.INCOME else TransactionType.EXPENSE
                        )
                    )
                    activity?.supportFragmentManager?.backStackEntryCount
                    dismiss()
                }
            }
        }
    }
}