package com.example.kotlinfuel_managment

import com.example.kotlinfuel_managment.model.Data
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun insertData(data: HashMap<String, Double>)
    suspend fun getAllData(): Flow<List<Data>>
}