package com.example.kotlinfuel_managment.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DataRepository {
    private val databaseReference = FirebaseDatabase.getInstance().getReference("Data")

    fun getData(): LiveData<List<Data>>{
        val data = MutableLiveData<List<Data>>()

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = mutableListOf<Data>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(Data::class.java)
                    item?.let {
                        items.add(it)
                    }
                }
                data.value = items
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("ONcCANCELLED","onCancelled")
            }

        })
        return data
    }
}