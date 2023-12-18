package com.example.kotlinfuel_managment.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinfuel_managment.databinding.FragmentHistoryBinding
import com.example.kotlinfuel_managment.model.DataAdapter
import com.example.kotlinfuel_managment.viewmodel.DataViewModel

class HistoryFragment : Fragment() {
    private lateinit var dataAdapter: DataAdapter
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var dataViewModel: DataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        dataViewModel = ViewModelProvider(this)[DataViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataAdapter = DataAdapter()
        binding.recyclerView.adapter = dataAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        retrieveDataAndUpdateRecyclerView()

        dataViewModel.getAllData()
    }

    private fun retrieveDataAndUpdateRecyclerView() {

        lifecycleScope.launchWhenStarted {
            dataViewModel.dataList.collect { data ->
                dataAdapter.submitList(data)
                Log.d("Did&data", "$data")
            }
        }
    }
}