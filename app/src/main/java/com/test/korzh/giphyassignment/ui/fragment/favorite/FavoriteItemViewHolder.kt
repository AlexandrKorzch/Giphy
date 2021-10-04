package com.test.korzh.giphyassignment.ui.fragment.favorite

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.test.korzh.giphyassignment.R
import com.test.korzh.giphyassignment.data.source.local.model.Giphy
import com.test.korzh.giphyassignment.ui.fragment.GiphyClickListener
import com.test.korzh.giphyassignment.ui.util.loadGiphy


class FavoriteItemViewHolder(view: View, private val callback: GiphyClickListener) :
    RecyclerView.ViewHolder(view), View.OnClickListener {

    private val imageView: ImageView = view.findViewById(R.id.imageView)
    private val addFab: FloatingActionButton = view.findViewById(R.id.addFab)

    private var giphy: Giphy? = null

    init {
        addFab.setOnClickListener(this)
    }

    fun bind(giphy: Giphy) {
        this.giphy = giphy
        loadGiphy(itemView, giphy, imageView)
    }

    override fun onClick(view: View?) {
        addFab.isSelected = !addFab.isSelected
        giphy?.let { callback.onFavoriteClick(it, addFab.isSelected) }
    }
}