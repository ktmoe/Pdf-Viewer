package com.rajat.pdfviewer

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.BufferedInputStream
import java.io.File
import java.net.URL

/**
 * Created by Rajat on 11,July,2020
 */

internal class PdfDownloader(private val title: String, url: String, private val listener: StatusListener) {
    interface StatusListener {
        fun getContext(): Context
        fun onDownloadStart() {}
        fun onDownloadProgress(currentBytes: Long, totalBytes: Long) {}
        fun onDownloadSuccess(absolutePath: String, message: String) {}
        fun onError(error: Throwable) {}
    }

    init {
        GlobalScope.async { download(url) }
    }

    private fun download(downloadUrl: String) {
        GlobalScope.launch(Dispatchers.Main) { listener.onDownloadStart() }
        val outputFile = File(listener.getContext().cacheDir, "$title.pdf")
        var message = ""
        if (!outputFile.exists()) {
            message = "File doesn't exists."
            try {
                val bufferSize = 8192
                val url = URL(downloadUrl)
                val connection = url.openConnection()
                connection.connect()

                val totalLength = connection.contentLength
                val inputStream = BufferedInputStream(url.openStream(), bufferSize)
                val outputStream = outputFile.outputStream()
                var downloaded = 0

                do {
                    val data = ByteArray(bufferSize)
                    val count = inputStream.read(data)
                    if (count == -1)
                        break
                    if (totalLength > 0) {
                        downloaded += bufferSize
                        GlobalScope.launch(Dispatchers.Main) {
                            listener.onDownloadProgress(
                                downloaded.toLong(),
                                totalLength.toLong()
                            )
                        }
                    }
                    outputStream.write(data, 0, count)
                } while (true)
            } catch (e: Exception) {
                e.printStackTrace()
                GlobalScope.launch(Dispatchers.Main) { listener.onError(e) }
                return
            }
        } else message = "File Reloaded."

        GlobalScope.launch(Dispatchers.Main) { listener.onDownloadSuccess(outputFile.absolutePath, message) }
    }
}