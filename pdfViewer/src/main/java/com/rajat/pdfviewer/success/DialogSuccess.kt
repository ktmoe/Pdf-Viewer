package com.rajat.pdfviewer.success

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.rajat.pdfviewer.R
import kotlinx.android.synthetic.main.success_view.*

/**
 * Created by ktmmoe on 08, September, 2020
 **/
class DialogSuccess private constructor(ctx: Context, private val message: String = "", private val callback: () -> Unit = {}) : DialogFragment() {

    private val start: Long = 200
    private val duration: Long = 1000
    private val dismiss: Long = 200

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.success_view, container, false)
        dialog?.let { dialog ->
            dialog.window?.let {
                it.setLayout(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                it.setBackgroundDrawable(ColorDrawable(Color.WHITE))
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        success_view.startAnim(start, duration)
        success_view.listener(
            end = {
                Handler().postDelayed(
                    {
                        callback()
                        dismiss()
                    },
                    dismiss
                )
            }
        )
        tv_message.visibility = if (message != null) View.VISIBLE else View.GONE
        tv_message.apply {
            text = message ?: ""
        }
    }

    companion object {
        fun Builder(ctx: Context, message: String, callback: () -> Unit) = DialogSuccess(ctx, message, callback)
    }
}