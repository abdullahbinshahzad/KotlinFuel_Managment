package com.example.kotlinfuel_managment.view.fragments

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinfuel_managment.R
import com.example.kotlinfuel_managment.databinding.FragmentHomeBinding
import com.example.kotlinfuel_managment.hideKeyboard
import com.example.kotlinfuel_managment.viewmodel.DataViewModel
import com.google.firebase.firestore.FieldValue
import java.text.DecimalFormat

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var dataViewModel: DataViewModel
    private lateinit var sharedPreferences: SharedPreferences

    private val TOTAL_FUEL_KEY = "totalFuel"
    private val TOTAL_DRIVEN_KEY = "totalDriven"
    private val TOTAL_COST_KEY = "totalCost"

    private var fuel  = 0.0
    private var distance = 0.0
    private var cost  = 0.0

    private var totalFuel = 0.0
    private var totalDriven = 0.0
    private var totalCost = 0.0

    private val df = DecimalFormat("#.##")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        dataViewModel = ViewModelProvider(this)[DataViewModel::class.java]
        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)

        // Retrieve previous values
        totalFuel = sharedPreferences.getFloat(TOTAL_FUEL_KEY, 0.0f).toDouble()
        totalDriven = sharedPreferences.getFloat(TOTAL_DRIVEN_KEY, 0.0f).toDouble()
        totalCost = sharedPreferences.getFloat(TOTAL_COST_KEY, 0.0f).toDouble()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animator = ObjectAnimator.ofInt(binding.sample,"textColor", Color.BLUE, Color.RED, Color.GREEN)
        animator.duration = 500
        animator.setEvaluator(ArgbEvaluator())
        animator.repeatCount = Animation.REVERSE
        animator.repeatCount = Animation.INFINITE
        animator.start()

        binding.calculateAverageButton.setOnClickListener {
            insertDataToDatabase()
        }
    }
    private fun insertDataToDatabase() {
        val fuelYouPutIn = binding.fuelYouPutInEditText.text.toString()
        val tripReading = binding.tripCounterReadingEditText.text.toString()
        val costOfFuel = binding.costOfFuelEditText.text.toString()
        if (fuelYouPutIn.isNotEmpty() && tripReading.isNotEmpty() && costOfFuel.isNotEmpty()) {
            fuel = fuelYouPutIn.toDouble()
            distance = tripReading.toDouble()
            cost = costOfFuel.toDouble()
            totalFuel += fuel
            totalDriven += distance
            totalCost += cost
            with(sharedPreferences.edit()) {
                putFloat(TOTAL_FUEL_KEY, totalFuel.toFloat())
                putFloat(TOTAL_DRIVEN_KEY, totalDriven.toFloat())
                putFloat(TOTAL_COST_KEY, totalCost.toFloat())
                apply()
            }
            val  tripAverage = calculateTripConsumption(distance, fuel)
            val  vehiclesAverage = calculateVehicleConsumption(totalDriven,totalFuel)
            val data = hashMapOf(
                "tripFuel" to fuel,
                "tripDrive" to distance,
                "costOfFuel" to cost,
                "tripAverage" to tripAverage,
                "vehiclesAverage" to vehiclesAverage,
                "timestamp" to FieldValue.serverTimestamp())
            dataViewModel.insertData(data)
            binding.tripCounterReadingEditText.text.clear()
            binding.fuelYouPutInEditText.text.clear()
            binding.costOfFuelEditText.text.clear()
            binding.tripAverageTextView.text = getString(R.string.trip_average_textView_edited,df.format(tripAverage))
            binding.averageTextView.text = getString(R.string.average_textView_edited,df.format(vehiclesAverage))
            hideKeyboard(requireActivity())
        }
    }
}
    private fun calculateVehicleConsumption(totalDriven:Double, totalFuel: Double ): Double {
        return totalDriven / totalFuel
    }
    private fun calculateTripConsumption(distance: Double, fuel: Double): Double {
        return distance / fuel
    }