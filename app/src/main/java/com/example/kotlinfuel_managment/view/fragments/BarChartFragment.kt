package com.example.kotlinfuel_managment.view.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.kotlinfuel_managment.databinding.FragmentBarChartBinding
import com.example.kotlinfuel_managment.model.Data
import com.example.kotlinfuel_managment.viewmodel.DataViewModel
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlinx.coroutines.launch

class BarChartFragment : Fragment() {

    private lateinit var binding: FragmentBarChartBinding
    private lateinit var dataViewModel: DataViewModel

    private val groupSpace = 0.6f
    private val barSpace = 0f
    private val barWidth = 0.9f

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

        retrieveDataAndUpdateBarChartView()

        dataViewModel.getAllData()
    }

    private fun retrieveDataAndUpdateBarChartView() {
        lifecycleScope.launch {
            dataViewModel.dataList.collect { data ->
                updateBarChart(data)
            }
        }
    }

    private fun updateBarChart(dataList: List<Data>) {
        Log.d("DataList", "DataList size: ${dataList.size}")
        if (dataList.isEmpty()) {
            Log.w("DataList", "DataList is empty")
            return
        }

        // Assuming you have separate lists for each category
        val tripFuelEntries = ArrayList<BarEntry>()
        val tripAverageEntries = ArrayList<BarEntry>()
        val vehicleAverageEntries = ArrayList<BarEntry>()
        val costOfFuelEntries = ArrayList<BarEntry>()
        val tripDriveEntries = ArrayList<BarEntry>()

// Populate entries for each category based on timestamps
        for ((index, data) in dataList.withIndex()) {
            val groupIndex = index * 5 // Each timestamp has 5 categories

            tripFuelEntries.add(BarEntry(groupIndex.toFloat(), data.tripFuel.toFloat()))
            tripAverageEntries.add(BarEntry(groupIndex + 1f, data.tripAverage.toFloat()))
            vehicleAverageEntries.add(BarEntry(groupIndex + 2f, data.vehiclesAverage.toFloat()))
            costOfFuelEntries.add(BarEntry(groupIndex + 3f, data.costOfFuel.toFloat()))
            tripDriveEntries.add(BarEntry(groupIndex + 4f, data.tripDrive.toFloat()))
        }

// Create BarDataSet instances for each category
        val tripFuelDataSet = BarDataSet(tripFuelEntries, "Trip Fuel")
        val tripAverageDataSet = BarDataSet(tripAverageEntries, "Trip Average")
        val vehicleAverageDataSet = BarDataSet(vehicleAverageEntries, "Vehicle Average")
        val costOfFuelDataSet = BarDataSet(costOfFuelEntries, "Cost of Fuel")
        val tripDriveDataSet = BarDataSet(tripDriveEntries, "Trip Drive")

// Set colors or other properties for each BarDataSet
        tripFuelDataSet.color = Color.BLUE
        tripAverageDataSet.color = Color.GREEN
        vehicleAverageDataSet.color = Color.YELLOW
        costOfFuelDataSet.color = Color.RED
        tripDriveDataSet.color = Color.MAGENTA

// Create BarData and add all BarDataSet instances
        val data = BarData(tripFuelDataSet, tripAverageDataSet, vehicleAverageDataSet, costOfFuelDataSet, tripDriveDataSet)

// Set BarData to BarChart
        binding.barChartView.data = data

// Group bars and set other properties
        binding.barChartView.groupBars(0f, groupSpace, barSpace)
        binding.barChartView.barData.barWidth = barWidth

// Set X-axis labels
        val xAxis = binding.barChartView.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(List(dataList.size) { index -> index.toString() })
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.labelRotationAngle = 0f
        xAxis.granularity = 1f

        binding.barChartView.invalidate()
    }
}