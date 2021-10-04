package com.test.korzh.giphyassignment.ui.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.test.korzh.giphyassignment.R
import com.test.korzh.giphyassignment.data.source.local.model.Giphy
import java.io.File


fun loadGiphy(
    itemView: View,
    giphy: Giphy,
    imageView: ImageView,
    progressBar: ProgressBar,
    addFab: FloatingActionButton
) {
    solveImageHeight(itemView, imageView, giphy)
    progressBar.visibility = VISIBLE
    addFab.hide()

    val listener = object : RequestListener<GifDrawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<GifDrawable>?,
            isFirstResource: Boolean
        ): Boolean {
            progressBar.visibility = GONE
            return false
        }

        override fun onResourceReady(
            resource: GifDrawable?,
            model: Any?,
            target: Target<GifDrawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            progressBar.visibility = GONE
            addFab.show()
            return false
        }
    }

    Glide.with(itemView)
        .asGif()
        .load(Uri.parse(giphy.gifUrl))
        .placeholder(R.drawable.place_holder)
        .error(ColorDrawable(Color.RED))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .listener(listener)
        .into(imageView);
}

fun loadGiphy(
    itemView: View,
    giphy: Giphy,
    imageView: ImageView
) {
    giphy.localPath?.let {
        Glide.with(itemView)
            .asGif()
            .load(File(it))
            .placeholder(R.drawable.place_holder)
            .error(ColorDrawable(Color.RED))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView);
    }
}

private fun solveImageHeight(
    itemView: View,
    imageView: ImageView,
    giphy: Giphy
) {
    val density = itemView.context.resources.displayMetrics.density
    val layoutParams = imageView.layoutParams
    layoutParams.height = giphy.height * density.toInt()
    imageView.layoutParams = layoutParams
}