package com.test.korzh.giphyassignment.ui.util

import android.content.Context
import com.test.korzh.giphyassignment.data.source.local.model.Giphy
import java.io.File
import java.io.FileOutputStream
import java.net.URL

const val DIR_NAME = "giphy"
const val FILE_TYPE = ".gif"

fun loadFile(
    context: Context?,
    giphy: Giphy,
    isFavorite: Boolean,
    callback: LoadFileCallback
) {
    val dir = File(context?.filesDir, DIR_NAME)
    if (!dir.exists()) {
        dir.mkdir()
    }
    val dest = File(dir, "${giphy.title}$FILE_TYPE")
    if (isFavorite && !dest.exists()) {
        download(giphy.gifUrl, dest)
    }

    giphy.localPath = dest.absolutePath
    giphy.isFavorite = isFavorite
    callback.onLoaded(giphy)
}

private fun download(url: String, dest: File) {
    URL(url).openStream().use { input ->
        FileOutputStream(dest).use { output ->
            input.copyTo(output)
        }
    }
}

interface LoadFileCallback {
    fun onLoaded(giphy: Giphy)
}