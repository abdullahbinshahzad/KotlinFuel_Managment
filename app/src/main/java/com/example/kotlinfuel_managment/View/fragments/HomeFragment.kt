package com.example.kotlinfuel_managment.View.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinfuel_managment.R
import com.example.kotlinfuel_managment.databinding.FragmentHomeBinding
import com.example.kotlinfuel_managment.hideKeyboard
import java.text.DecimalFormat

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private var totalFuel = 0.0
    private var totalDistance = 0.0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calculateAverageButton.setOnClickListener {
            Log.i("button clicked", "onViewCreated: clicked")
            val fuelInput = binding.fuelInputEditText.text.toString()
            val distanceInput = binding.distanceInputEditText.text.toString()
            if (fuelInput.isNotEmpty() && distanceInput.isNotEmpty()) {
                val fuel = fuelInput.toDouble()
                val distance = distanceInput.toDouble()
                totalFuel += fuel
                totalDistance += distance
                calculateAndDisplayConsumption()
                binding.distanceInputEditText.text.clear()
                binding.fuelInputEditText.text.clear()
                //hideKeyboard(requireActivity())
            }
        }
    }

    private fun calculateAndDisplayConsumption() {
        if (totalDistance > 0 && totalFuel > 0) {
            val consumption = totalDistance / totalFuel
            val df = DecimalFormat("#.##")
            binding.averageTextView.text = "Average of Your Vehicle: ${df.format(consumption)} Km/L"
            binding.totaldistanceTextView.text = "Total Driven: ${df.format(consumption)} Km/L"
        }
    }
}
