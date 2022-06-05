package com.example.jobget.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.jobget.R
import com.example.jobget.databinding.DialogAddTransactionBinding
import com.example.jobget.interfaces.AddButtonListener
import com.example.jobget.model.TransactionModel
import com.example.jobget.model.TransactionType
import com.example.jobget.viewmodel.MainActivityViewModel

class AddTransactionFragmentDialog(
    private val viewModel: MainActivityViewModel,
    private val addButtonListener: AddButtonListener
) : DialogFragment() {
    private lateinit var binding: DialogAddTransactionBinding
    private lateinit var spinnerTransactionType: Spinner
    private lateinit var editTextDescription: EditText
    private lateinit var editTextDollarAmount: EditText
    private lateinit var buttonIncrement: ImageButton
    private lateinit var buttonDecrement: ImageButton
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
        setViewBindings()
        setSpinnerContent()
        setAddButtonOnClickListener()
        setCancelButtonOnClickListener()
    }

    private fun setViewBindings() {
        binding.apply {
            spinnerTransactionType = spTransactionType
            editTextDescription = etTransactionDescription
            editTextDollarAmount = clDollarContainer.etDollarAmount
            buttonIncrement = clDollarContainer.btnUp
            buttonIncrement.setOnClickListener {
                updateEditTextValue(true)
            }
            buttonDecrement = clDollarContainer.btnDown
            buttonDecrement.setOnClickListener {
                updateEditTextValue()
            }
            buttonAdd = btnAdd
            buttonCancel = btnCancel
        }
    }

    private fun updateEditTextValue(isIncrement: Boolean = false) {
        when (!editTextDollarAmount.text.isNullOrEmpty()) {
            true -> {
                var tempInt = Integer.parseInt(editTextDollarAmount.text.toString())
                when (isIncrement) {
                    true -> tempInt++
                    else -> {
                        if (tempInt > 0) {
                            tempInt--
                        }
                    }
                }
                editTextDollarAmount.setText(tempInt.toString())
            }
            else -> {
                editTextDollarAmount.setText("0")
            }
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
                    addButtonListener.setRecyclerViewList()
                    dismiss()
                }
            }
        }
    }
}