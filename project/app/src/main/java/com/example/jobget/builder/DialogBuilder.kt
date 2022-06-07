package com.example.jobget.builder

import android.app.Activity
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.example.jobget.R

fun showErrorDialog(activity: Activity): AlertDialog? {
    val builder = AlertDialog.Builder(activity)
    builder.setTitle(activity.getString(R.string.error_dialog_title))
    builder.setMessage(activity.getString(R.string.error_dialog_message))
    builder.setPositiveButton(activity.getString(R.string.ok_text)) { dialog: DialogInterface, _: Int -> dialog.dismiss() }
    return builder.show()
}

fun displayAlertPopup(it: FragmentActivity): AlertDialog? {
    val builder = AlertDialog.Builder(it)
    builder.setTitle(it.getString(R.string.out_of_bounds_dialog_title))
    builder.setMessage(it.getString(R.string.out_of_bounds_dialog_message, Integer.MAX_VALUE))
    builder.setPositiveButton(it.getString(R.string.ok_text)) { dialog: DialogInterface, _: Int -> dialog.dismiss() }
    return builder.show()
}