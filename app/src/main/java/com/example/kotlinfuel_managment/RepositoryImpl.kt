package com.example.kotlinfuel_managment

import android.util.Log
import com.example.kotlinfuel_managment.model.Data
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class RepositoryImpl : Repository {
    private val db = Firebase.firestore
    override suspend fun insertData(data: HashMap<String, Double>) {
        db.collection("users")
            .add(data)
            .addOnSuccessListener { documentReference ->
                Log.d("DAWID", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("EAD", "Error adding document", e)
            }
    }

    override suspend fun getAllData(): Flow<List<Data>> = flow {
        try {
            val dataList = mutableListOf<Data>()
            val result = db.collection("users").get().await()
            for (document in result) {
                val data = document.toObject(Data::class.java)
                dataList.add(data)
            }
            emit(dataList)
        } catch (e: Exception) {
            // Handle exceptions if needed
        }
    }
}