package com.example.kotlinfuel_managment.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.lifecycle.observe
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinfuel_managment.databinding.FragmentBarChartBinding
import com.example.kotlinfuel_managment.model.Data
import com.example.kotlinfuel_managment.viewmodel.DataViewModel
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class BarChartFragment : Fragment() {

    private lateinit var binding: FragmentBarChartBinding
    private lateinit var dataViewModel: DataViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBarChartBinding.inflate(inflater, container, false)
        dataViewModel = ViewModelProvider(this).get(DataViewModel::class.java)


        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataViewModel.getAllData().observe(viewLifecycleOwner) { dataList ->
            updateBarChart(dataList)
        }
    }
    private fun updateBarChart(dataList: List<Data>) {
        val entries = ArrayList<BarEntry>()
        val title = "Title"

        for ((index, data) in dataList.withIndex()) {
            // Assuming you have a field in Data class to represent Y-axis value
            val value = data.yourYAxisValue
            val barEntry = BarEntry(index.toFloat(), value.toFloat())
            entries.add(barEntry)
        }

        val barDataSet = BarDataSet(entries, title)
        val data = BarData(barDataSet)
        binding.barChartView.data = data
        binding.barChartView.invalidate()
    }
}