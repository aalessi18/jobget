package com.example.jobget.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor() : ViewModel() {
    companion object {
        private const val ZERO_TEXT = "0"
    }

    fun updateEditTextValue(textValue: String?, isIncrement: Boolean = false): String {
        when (!textValue.isNullOrEmpty()) {
            true -> {
                var tempInt = Integer.parseInt(textValue)
                when (isIncrement) {
                    true -> tempInt++
                    else -> {
                        if (tempInt > 0) {
                            tempInt--
                        }
                    }
                }
                return tempInt.toString()
            }
            else -> {
                return "0"
            }
        }
    }

    fun checkIfTextsAreEmptyOrZero(
        editTextDescription: String?,
        editTextDollarAmount: String?
    ): Boolean =
        editTextDescription.isNullOrEmpty() || editTextDollarAmount.isNullOrEmpty() || editTextDollarAmount == ZERO_TEXT
}