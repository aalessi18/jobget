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
import androidx.fragment.app.viewModels
import com.example.jobget.R
import com.example.jobget.databinding.DialogAddTransactionBinding
import com.example.jobget.interfaces.AddButtonListener
import com.example.jobget.model.TransactionModel
import com.example.jobget.util.getTransactionType
import com.example.jobget.viewmodel.AddTransactionViewModel
import com.example.jobget.viewmodel.MainActivityViewModel

// TODO: Is there away to avoid passing the viewmodel, maybe a callback instead
class AddTransactionFragmentDialog(
    private val mainActivityViewModel: MainActivityViewModel,
    private val addButtonListener: AddButtonListener
) : DialogFragment() {
    private val viewModel: AddTransactionViewModel by viewModels()
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
            setIncrementButton(this)
            setDecrementButton(this)
            buttonAdd = btnAdd
            buttonCancel = btnCancel
        }
    }

    private fun setIncrementButton(binding: DialogAddTransactionBinding) {
        buttonIncrement = binding.clDollarContainer.btnUp
        buttonIncrement.setOnClickListener {
            editTextDollarAmount.setText(
                viewModel.updateEditTextValue(
                    editTextDollarAmount.text.toString(),
                    true
                )
            )
        }
    }

    private fun setDecrementButton(binding: DialogAddTransactionBinding) {
        buttonDecrement = binding.clDollarContainer.btnDown
        buttonDecrement.setOnClickListener {
            editTextDollarAmount.setText(viewModel.updateEditTextValue(editTextDollarAmount.text.toString()))
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
            if (viewModel.checkIfTextsAreEmptyOrZero(
                    editTextDescription.text.toString(),
                    editTextDollarAmount.text.toString()
                )
            ) {
                Toast.makeText(context, R.string.fill_in_the_blanks, Toast.LENGTH_SHORT)
                    .show()
            } else {
                activity?.let {
                    getTransactionType(spinnerTransactionType.selectedItem.toString())?.let { transactionType ->
                        mainActivityViewModel.addTransaction(
                            it,
                            TransactionModel(
                                editTextDescription.text.toString(),
                                editTextDollarAmount.text.toString(),
                                transactionType
                            )
                        )
                        addButtonListener.setRecyclerViewList()
                        dismiss()
                    }
                }
            }
        }
    }
}