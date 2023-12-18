package com.example.kotlinfuel_managment.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinfuel_managment.R
import com.example.kotlinfuel_managment.databinding.CardTemplateBinding

class DataAdapter: ListAdapter<Data, DataAdapter.DataViewHolder>(Comparator())
{
    class DataViewHolder(val binding: CardTemplateBinding): RecyclerView.ViewHolder(binding.root) {
        // TODO
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = CardTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.tripFuelTextView.text= holder.itemView.context.getString(R.string.label_trip_fuel, item.tripFuel.toString())
        holder.binding.tripDriveTextView.text= holder.itemView.context.getString(R.string.label_trip_drive,item.tripDrive.toString())
        holder.binding.costOfFuelTextView.text= holder.itemView.context.getString(R.string.label_cost_of_fuel, item.costOfFuel.toString())
        holder.binding.tripAverageTextView.text= holder.itemView.context.getString(R.string.label_trip_average, item.tripAverage.toString())
        holder.binding.vehicleAverageTextView.text= holder.itemView.context.getString(R.string.label_vehicle_average, item.vehiclesAverage.toString())
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