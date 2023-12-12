package com.example.kotlinfuel_managment.view.fragments

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kotlinfuel_managment.R
import com.example.kotlinfuel_managment.databinding.FragmentHomeBinding
import com.example.kotlinfuel_managment.hideKeyboard
import com.example.kotlinfuel_managment.model.Data
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import java.text.DateFormat
import java.text.DecimalFormat
import java.util.Calendar


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var database: DatabaseReference


    private var totalFuel = 0.0
    private var totalDriven = 0.0
    private var totalCost = 0.0

    private var fuel  = 0.0
    private var distance = 0.0
    private var cost  = 0.0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        database = Firebase.database.reference
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        // adding the color to be shown
        // adding the color to be shown
        val animator = ObjectAnimator.ofInt(binding.sample,"textColor", Color.BLUE, Color.RED, Color.GREEN)
        // duration of one color
        animator.duration = 500
        animator.setEvaluator(ArgbEvaluator())
        // color will be show in reverse manner
        animator.repeatCount = Animation.REVERSE
        // It will be repeated up to infinite time
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
            val df = DecimalFormat("#.##")
            val  tripAverage = getString(R.string.trip_average_textView_edited, df.format(calculateTripConsumption(distance, fuel)))
            val  vehiclesAverage = getString(R.string.average_textView_edited, df.format(calculateVehicleConsumption(totalDriven,totalFuel)))
            binding.tripCounterReadingEditText.text.clear()
            binding.fuelYouPutInEditText.text.clear()
            binding.costOfFuelEditText.text.clear()
            binding.tripAverageTextView.text = tripAverage
            binding.averageTextView.text = vehiclesAverage

            val data = Data(fuelYouPutIn, tripReading, costOfFuel, tripAverage, vehiclesAverage)
            val currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().time)
            database.child("Data").child(currentDate).setValue(data).addOnCompleteListener {

                Toast.makeText(requireContext(),"saved", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(requireContext(),"not saved retry", Toast.LENGTH_SHORT).show()
            }

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
