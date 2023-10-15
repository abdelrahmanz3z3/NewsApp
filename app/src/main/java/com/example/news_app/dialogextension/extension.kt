package com.example.news_app.dialogextension

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.fragment.app.Fragment

fun Fragment.showMessage(
    message: String,
    posMessage: String,
    posAction: DialogInterface.OnClickListener,
    negMessage: String,
    negAction: DialogInterface.OnClickListener
): AlertDialog {
    val alert = AlertDialog.Builder(context)
    alert.setMessage(message)
    alert.setPositiveButton(posMessage, posAction)
    alert.setNegativeButton(negMessage, negAction)
    return alert.show()
}

fun Activity.showMessage(
    message: String,
    posMessage: String,
    posAction: DialogInterface.OnClickListener,
    negMessage: String,
    negAction: DialogInterface.OnClickListener
): AlertDialog {
    val alert = AlertDialog.Builder(this)
    alert.setMessage(message)
    alert.setPositiveButton(posMessage, posAction)
    alert.setNegativeButton(negMessage, negAction)
    return alert.show()
}