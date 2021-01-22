package com.hogwarts.weatherapp.adapters

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.view.ContextThemeWrapper
import com.hogwarts.weatherapp.R

class ContentProvider(val context: Context) {
    private lateinit var dialog: AlertDialog

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun createDialog() {
        val builder =
            AlertDialog.Builder(ContextThemeWrapper(context as Activity, R.style.AlertDialogTheme))
        builder.setView(R.layout.fetching_data_layout)
        builder.setCancelable(false)
        dialog = builder.create()
        dialog.window!!.setBackgroundDrawableResource(R.color.transparent_background)
        dialog.show()
    }

    fun hideDialog() {
        dialog.dismiss()
    }

}