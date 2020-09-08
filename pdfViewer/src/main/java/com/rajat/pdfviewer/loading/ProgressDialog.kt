package com.rajat.pdfviewer.loading

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.rajat.pdfviewer.R
import kotlinx.android.synthetic.main.progress_dialog.*
import kotlinx.android.synthetic.main.progress_dialog.view.*

/**
 * Created by ktmmoe on 08, September, 2020
 **/
object ProgressDialog {
    private var dialog: Dialog? = null

    fun showProgress(context: Context) {
        if (dialog == null)
            dialog = Dialog(context)
        dialog?.let {
            val view = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null)

//            circularProgress = view.circularProgress

            it.setContentView(view)
            it.setCancelable(false)
            it.show()
        }
    }

    fun hideLoadingProgress() {
        dialog?.let {
            if (it.isShowing) {
                it.cancel()
                dialog = null
            }
        }
    }
}