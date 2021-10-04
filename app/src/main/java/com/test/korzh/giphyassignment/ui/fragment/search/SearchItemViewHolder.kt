package com.test.korzh.giphyassignment.ui.fragment.search

import android.graphics.drawable.Animatable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.test.korzh.giphyassignment.R
import com.test.korzh.giphyassignment.data.source.local.model.Giphy
import com.test.korzh.giphyassignment.ui.fragment.GiphyClickListener
import com.test.korzh.giphyassignment.ui.util.loadGiphy

class SearchItemViewHolder(view: View, private val callback: GiphyClickListener) :
    RecyclerView.ViewHolder(view), View.OnClickListener {

    private val imageView: ImageView = view.findViewById(R.id.imageView)
    private val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
    private val addFab: FloatingActionButton = view.findViewById(R.id.addFab)

    private var giphy: Giphy? = null

    init {
        addFab.setOnClickListener(this)
    }

    fun bind(giphy: Giphy) {
        this.giphy = giphy
        loadGiphy(itemView, giphy, imageView, progressBar, addFab)
        bindButton(giphy)
    }

    private fun bindButton(giphy: Giphy) {
        addFab.isSelected = giphy.isFavorite
        animateButton()
    }

    override fun onClick(view: View?) {
        addFab.isSelected = !addFab.isSelected
        animateButton()
        giphy?.let { callback.onFavoriteClick(it, addFab.isSelected) }
    }

    private fun animateButton() {
        addFab.setImageResource(
            when (addFab.isSelected) {
                false -> R.drawable.animated_minus
                true -> R.drawable.animated_plus
            }
        )
        val drawable = addFab.drawable
        if (drawable is Animatable) {
            (drawable as Animatable).start();
        }
    }
}