package com.example.ecommercie_1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.ecommercie_1.MainActivity.Companion.TAG
import com.example.ecommercie_1.databinding.FragmentListBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage


class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var layoutManager: GridLayoutManager
    private val favoriteListEpoxyController by lazy {
        FavoriteListEpoxyController()
    }
    private lateinit var recyclerView: EpoxyRecyclerView
    private val viewModel: FavoriteListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        recyclerView = binding.recyclerView
       layoutManager = GridLayoutManager(context,2)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = favoriteListEpoxyController.adapter
        viewModel.favoriteOrder.observe(viewLifecycleOwner, Observer {
            favoriteListEpoxyController.setData(it)
            Log.d(TAG, it.toString())
        })
        viewModel.startDownload()
        return binding.root
    }


}