package com.mirkamol.android_sass.fragment.main.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mirkamol.android_sass.R
import com.mirkamol.android_sass.adapters.HomePhotoAdapter
import com.mirkamol.android_sass.databinding.FragmentHistoryBinding
import com.mirkamol.android_sass.databinding.FragmentHomeBinding
import com.mirkamol.android_sass.databinding.FragmentMainFlowBinding
import com.mirkamol.android_sass.model.HomePhotoItem
import com.mirkamol.android_sass.utils.UiStateList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewmodel by viewModels<HomeViewModel>()
    private val adapter by lazy { HomePhotoAdapter() }
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private var PAGE = 1
    private var PER_PAGE = 15
    private val photos = ArrayList<HomePhotoItem>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel.getAllPhotos(PAGE, PER_PAGE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObservers()
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewmodel.getPhotosState.collect {
                    when (it) {
                        is UiStateList.LOADING -> {

                        }
                        is UiStateList.SUCCESS -> {
                            photos.addAll(photos.size,it.data)
                            Log.d("@@@", "setupObservers: ${it.data}")
                            adapter.submitList(photos.toList())
                        }
                        is UiStateList.ERROR -> {
                            Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT)
                                .show()
                        }
                        else -> Unit
                    }
                }
            }
        }
    }

    private fun setupUI() {
        binding.apply {
            rvHomePhotos.adapter = adapter
            nestedScroll.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (nestedScroll.getChildAt(nestedScroll.childCount - 1) != null) {
                    if (scrollY >= nestedScroll.getChildAt(nestedScroll.childCount - 1).measuredHeight - nestedScroll.measuredHeight &&
                        scrollY > oldScrollY
                    ) {
                        PAGE++
                        Log.d("page", "setupUI: $PAGE")
                        viewmodel.getAllPhotos(PAGE, PER_PAGE)
                        //Toast.makeText(requireContext(), "the end", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

}