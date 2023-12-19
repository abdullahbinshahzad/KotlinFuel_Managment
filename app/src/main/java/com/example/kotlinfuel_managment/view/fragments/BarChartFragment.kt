package com.example.kotlinfuel_managment.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.kotlinfuel_managment.databinding.FragmentBarChartBinding
import com.example.kotlinfuel_managment.model.Data
import com.example.kotlinfuel_managment.viewmodel.DataViewModel
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.coroutines.launch

class BarChartFragment : Fragment() {

    private lateinit var binding: FragmentBarChartBinding
    private lateinit var dataViewModel: DataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBarChartBinding.inflate(inflater, container, false)
        dataViewModel = ViewModelProvider(this)[DataViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            dataViewModel.dataList.collect { dataList ->
                updateBarChart(dataList)
            }
        }
    }

    private fun updateBarChart(dataList: List<Data>) {
        val entries = ArrayList<BarEntry>()
        val title = "Title"

        for ((index, data) in dataList.withIndex()) {
            val value = data.timestamp.toString()
            val barEntry = BarEntry(index.toFloat(), value.toFloat())
            entries.add(barEntry)
        }

        val barDataSet = BarDataSet(entries, title)
        val data = BarData(barDataSet)
        binding.barChartView.data = data
        binding.barChartView.invalidate()
    }
}
