package com.example.kotlinfuel_managment.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinfuel_managment.databinding.CardTemplateBinding

class DataAdapter: ListAdapter<Data, DataAdapter.DataViewHolder>(Comparator())
{
    class DataViewHolder(val binding: CardTemplateBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = CardTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item =getItem(position)
        holder.binding.tripFuelTextView.text = item.tripFuel
        holder.binding.tripDriveTextView.text = item.tripDrive
        holder.binding.costOfFuelTextView.text = item.costOfFuel
        holder.binding.tripAverageTextView.text = item.tripAverage.toString()
        holder.binding.vehicleAverageTextView.text = item.vehiclesAverage.toString()
    }

    class Comparator : DiffUtil.ItemCallback<Data>(){

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }
}