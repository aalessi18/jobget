package com.example.jobget.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(): ViewModel()  {
    fun updateEditTextValue(textValue: String?, isIncrement: Boolean = false) : String {
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
}