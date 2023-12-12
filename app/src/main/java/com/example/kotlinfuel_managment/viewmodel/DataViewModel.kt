package com.example.kotlinfuel_managment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinfuel_managment.model.Data
import com.example.kotlinfuel_managment.model.DataRepository

class DataViewModel(private val repository: DataRepository): ViewModel() {
    fun getData(): LiveData<List<Data>>{
        return repository.getData()
    }
}