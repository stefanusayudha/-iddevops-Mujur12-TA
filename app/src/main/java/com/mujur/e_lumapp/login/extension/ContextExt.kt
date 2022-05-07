package com.mujur.e_lumapp.login.ui.common.extension

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import com.mujur.e_lumapp.R

fun Context.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.showGenericAlertDialog(message: String){
    AlertDialog.Builder(this).apply {
        setMessage(message)
        setPositiveButton(getString(R.string.button_text_ok)){ dialog, _ ->
            dialog.dismiss()
        }
    }.show()
}