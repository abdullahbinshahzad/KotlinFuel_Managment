package com.example.kotlinfuel_managment.model

import com.google.firebase.Timestamp

data class Data(
    val tripFuel: Double = 0.0,
    val tripDrive: Double = 0.0,
    val costOfFuel: Double = 0.0,
    val tripAverage: Double = 0.0,
    val vehiclesAverage: Double = 0.0,
    val timestamp: Timestamp? = null
)
