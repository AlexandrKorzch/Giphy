package com.test.korzh.giphyassignment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment<Binding> : Fragment() {

    protected var _binding: Binding? = null
    private val binding get() = _binding!!

    abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?)

    abstract fun initUi(binding: Binding): View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding(inflater, container)
        return initUi(binding)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}