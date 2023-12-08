package com.example.kotlinfuel_managment.view.fragments

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import com.example.kotlinfuel_managment.R
import com.example.kotlinfuel_managment.databinding.FragmentHomeBinding
import com.example.kotlinfuel_managment.hideKeyboard
import java.text.DecimalFormat


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private var totalFuel = 0.0
    private var totalDriven = 0.0
    private var totalCost = 0.0

    private var fuel  = 0.0
    private var distance = 0.0
    private var cost  = 0.0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentHomeBinding.inflate(layoutInflater)
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
                calculateAndDisplayConsumption()
                binding.tripCounterReadingEditText.text.clear()
                binding.fuelYouPutInEditText.text.clear()
                binding.costOfFuelEditText.text.clear()
                hideKeyboard(requireActivity())
            }
        }
    }

    private fun calculateAndDisplayConsumption() {
        if (distance > 0 && fuel > 0 && totalDriven > 0 && totalFuel > 0) {
            val df = DecimalFormat("#.##")
            val tripAverage = getString(R.string.trip_average_textView_edited, df.format(distance / fuel))
            val vehiclesAverage = getString(R.string.average_textView_edited, df.format(totalDriven / totalFuel))
            binding.tripAverageTextView.text = tripAverage
            binding.averageTextView.text = vehiclesAverage
        }
    }
}