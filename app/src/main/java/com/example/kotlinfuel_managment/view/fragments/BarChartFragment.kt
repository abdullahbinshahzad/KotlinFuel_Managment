package com.example.kotlinfuel_managment.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinfuel_managment.databinding.FragmentBarChartBinding

class BarChartFragment : Fragment() {

    private lateinit var binding: FragmentBarChartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBarChartBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    @Override
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}