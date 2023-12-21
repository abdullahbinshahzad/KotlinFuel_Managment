package com.example.kotlinfuel_managment.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinfuel_managment.Repository
import com.example.kotlinfuel_managment.RepositoryImpl
import com.example.kotlinfuel_managment.model.Data
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DataViewModel(private val repository: Repository = RepositoryImpl()):ViewModel() {

    private val _dataList = MutableStateFlow<List<Data>>(emptyList())
    val dataList: StateFlow<List<Data>> get() = _dataList

    fun insertData(data: HashMap<String, Any>) {
        viewModelScope.launch {
            repository.insertData(data)
        }
    }
    fun getAllData() {
        viewModelScope.launch {
            repository.getAllData().collect { data ->
                _dataList.value = data
                Log.d("DataViewModel", "DataList size: ${data.size}")
            }
        }
    }
}