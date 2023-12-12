package com.example.kotlinfuel_managment.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinfuel_managment.databinding.FragmentHistoryBinding
import com.example.kotlinfuel_managment.model.DataAdapter
import com.example.kotlinfuel_managment.viewmodel.DataViewModel

class HistoryFragment : Fragment() {

    private lateinit var viewModel: DataViewModel
    private lateinit var adapter: DataAdapter
    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(DataViewModel::class.java)
        adapter = DataAdapter()
        val recyclerView = binding.recyclerView
        recyclerView.adapter = DataAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getData().observe(viewLifecycleOwner, Observer{items ->
            adapter.submitList(items)
        })



//        database = Firebase.database.reference
//        dataArrayList = ArrayList()
//        event = database.addValueEventListener(object : ValueEventListener {
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                    for (itemSnapshot in snapshot.children) {
//                        val data = itemSnapshot.child("Data").getValue(Data::class.java)
//                        Toast.makeText(requireContext(), "data is not null", Toast.LENGTH_SHORT).show()
//                        if (data != null) {
//                            dataArrayList.add(data)
//                        }
//
//                    }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(requireContext(), "loadPost:onCancelled", Toast.LENGTH_SHORT).show()
//            }
//
//        })
        return binding.root
    }
}