package com.rajat.pdfviewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "http://139.59.115.149:5040/upload/epapers/node-handbook-(1).pdf"

        open_pdf.setOnClickListener {
            startActivity(
                PdfViewerActivity.buildIntent(
                    this,
                    url,
                    false,
                    "title",
                    "title",
                    "",
                    true
                )
            )
        }

    }
}
