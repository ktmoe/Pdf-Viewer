package com.rajat.pdfviewer.loading

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import com.github.lzyzsd.circleprogress.DonutProgress
import com.rajat.pdfviewer.R
import kotlinx.android.synthetic.main.progress_dialog.*
import kotlinx.android.synthetic.main.progress_dialog.view.*

/**
 * Created by ktmmoe on 08, September, 2020
 **/
object ProgressDialog {
    private var dialog: Dialog? = null
    private lateinit var progress: DonutProgress
    private var showed = false

    private fun showProgress(context: Context) {
        if (dialog == null)
            dialog = Dialog(context)
        dialog?.let {
            val view = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null)

            progress = view.donutProgress
//            circularProgress = view.circularProgress

            it.setContentView(view)
            it.setCancelable(false)
            it.show()
            it.window!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        }
    }

    fun updateProgress(progress: Int, context: Context) {
        if (!showed) showProgress(context)
        this.progress.setDonut_progress("$progress")
        if (progress == 100) hideLoadingProgress()
    }

    private fun hideLoadingProgress() {
        dialog?.let {
            if (it.isShowing) {
                it.cancel()
                dialog = null
            }
        }
    }
}