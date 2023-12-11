package com.example.kotlinfuel_managment.model

import androidx.room.PrimaryKey

data class Data(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val tripFuel: String,
    val tripDrive: String,
    val costOfFuel: String,
    val tripAverage: Double,
    val vehiclesAverage: Double
)
