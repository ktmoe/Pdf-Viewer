package com.rajat.pdfviewer.loading

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.rajat.pdfviewer.R
import kotlinx.android.synthetic.main.progress_dialog.*

/**
 * Created by ktmmoe on 08, September, 2020
 **/
object ProgressDialog {
    private var dialog: Dialog? = null
    private lateinit var view: View

    fun showProgress(context: Context) {
        if (dialog == null)
            dialog = Dialog(context)
        dialog?.let {
            this.view = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null)

            it.setContentView(view)
            it.setCancelable(false)
            it.show()
            it.window?.setBackgroundDrawable(null)
        }
    }

    fun setProgress(progress: Int){
        dialog?.let {
            it.progress_horizontal.progress = progress
        }
    }

    fun hideLoadingProgress(){
        dialog?.let {
            if (it.isShowing) {
                it.cancel()
                dialog = null
            }
        }
    }
}