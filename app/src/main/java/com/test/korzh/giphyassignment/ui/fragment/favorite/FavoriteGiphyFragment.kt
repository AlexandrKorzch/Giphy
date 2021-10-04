package com.test.korzh.giphyassignment.ui.fragment.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.test.korzh.giphyassignment.R
import com.test.korzh.giphyassignment.data.source.local.model.Giphy
import com.test.korzh.giphyassignment.databinding.FragmentFavoriteBinding
import com.test.korzh.giphyassignment.ui.fragment.BaseFragment
import com.test.korzh.giphyassignment.ui.fragment.GiphyClickListener
import com.test.korzh.giphyassignment.util.getViewModelFactory


class FavoriteGiphyFragment :
    BaseFragment<FragmentFavoriteBinding>(), GiphyClickListener {

    private val viewModel by viewModels<FavoriteViewModel> { getViewModelFactory() }

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteGiphyFragment()
    }

    override fun onFavoriteClick(giphy: Giphy, isFavorite: Boolean) {
        viewModel.deleteGiphy(giphy)
    }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
    }

    override fun initUi(binding: FragmentFavoriteBinding): View =
        binding.run {
            initRecyclerView()
            observeFavorites()
            return root
        }

    private fun FragmentFavoriteBinding.initRecyclerView() {
        recyclerView.adapter = FavoriteAdapter(this@FavoriteGiphyFragment)
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.grid_space)
        recyclerView.addItemDecoration(SpacesItemDecoration(spacingInPixels))
    }

    private fun FragmentFavoriteBinding.observeFavorites() {
        val favoriteAdapter = recyclerView.adapter as FavoriteAdapter
        viewModel.favorites.observe(viewLifecycleOwner, { favorites ->
            favorites?.let {
                favoriteAdapter.updateGiphyState(it)
            }
        })
    }
}