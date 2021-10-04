package com.test.korzh.giphyassignment.ui.fragment.search

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.test.korzh.giphyassignment.constant.DOWN
import com.test.korzh.giphyassignment.constant.FIRST
import com.test.korzh.giphyassignment.data.source.local.model.Giphy
import com.test.korzh.giphyassignment.databinding.FragmentSearchBinding
import com.test.korzh.giphyassignment.ui.fragment.BaseFragment
import com.test.korzh.giphyassignment.ui.fragment.GiphyClickListener
import com.test.korzh.giphyassignment.ui.fragment.search.State.SEARCH
import com.test.korzh.giphyassignment.ui.fragment.search.State.TRENDING
import com.test.korzh.giphyassignment.util.getViewModelFactory


class SearchGiphyFragment : BaseFragment<FragmentSearchBinding>(), GiphyClickListener {

    companion object {
        fun newInstance() = SearchGiphyFragment()
    }

    private val viewModel by viewModels<SearchViewModel> { getViewModelFactory() }

    private var state: State = TRENDING
    private var lastSearchText: String? = null

    override fun onFavoriteClick(giphy: Giphy, isFavorite: Boolean) {
        viewModel.changeFileState(context, giphy, isFavorite)
    }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun initUi(binding: FragmentSearchBinding): View =
        binding.run {
            initRecyclerView()
            initProgressBar()
            initTrending()
            initSearch()
            observeFavorites()
            return root
        }

    private fun FragmentSearchBinding.initRecyclerView() {
        recyclerView.adapter = SearchAdapter(this@SearchGiphyFragment)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(DOWN)) {
                    recyclerView.adapter?.itemCount?.let {
                        val offset = it - 1
                        when (state) {
                            TRENDING -> viewModel.getGiphyTrending(offset)
                            SEARCH -> viewModel.getGiphySearch(offset, lastSearchText)
                        }
                    }
                }
            }
        })
    }

    private fun FragmentSearchBinding.initProgressBar() {
        viewModel.progressLiveData.observe(viewLifecycleOwner) {
            progressBar.visibility = it
        }
    }

    private fun FragmentSearchBinding.initTrending() {
        val searchAdapter = recyclerView.adapter as SearchAdapter
        viewModel.giphyTrendLiveData.observe(viewLifecycleOwner) {
            if (state == SEARCH) {
                searchAdapter.clear()
            }
            state = TRENDING
            searchAdapter.setDataSet(it)
        }
        viewModel.getGiphyTrending(FIRST)
    }

    private fun FragmentSearchBinding.initSearch() {
        val searchAdapter = recyclerView.adapter as SearchAdapter
        viewModel.giphySearchLiveData.observe(viewLifecycleOwner) {
            if (state == TRENDING) {
                searchAdapter.clear()
                state = SEARCH
            }
            searchAdapter.setDataSet(it)
        }
        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextChange(text: String?): Boolean {
                if (TextUtils.isEmpty(text)) {
                    viewModel.getGiphyTrending(FIRST)
                }
                return true
            }

            override fun onQueryTextSubmit(searchText: String?): Boolean {
                lastSearchText = searchText
                viewModel.getGiphySearch(FIRST, searchText)
                return true
            }
        })
    }

    private fun FragmentSearchBinding.observeFavorites() {
        val searchAdapter = recyclerView.adapter as SearchAdapter
        viewModel.favorites.observe(viewLifecycleOwner, { favorites ->
            favorites?.let {
                searchAdapter.updateGiphyState(it)
            }
        })
    }
}