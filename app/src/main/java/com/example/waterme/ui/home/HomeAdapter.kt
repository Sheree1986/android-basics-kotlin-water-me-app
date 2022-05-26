package com.example.waterme.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.waterme.core.model.Plant
import com.example.waterme.databinding.ListItemBinding

class HomeAdapter(
    private val longClickListener: (Plant) -> Unit,
    private val clickListener: (Plant) -> Unit
) : ListAdapter<Plant, HomeAdapter.HomeViewHolder>(diffCallback) {
    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Plant>() {
            override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean =
                oldItem == newItem
        }
    }

    inner class HomeViewHolder(private val bind: ListItemBinding) :
        RecyclerView.ViewHolder(bind.root) {
        fun binding(plant: Plant) {
            bind.apply {
                name.text = plant.name
                description.text = plant.description
                schedule.text = plant.schedule
                type.text = plant.type
                editItemButton.setOnClickListener { clickListener(plant) }
            }
            itemView.setOnLongClickListener {
                longClickListener(plant)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        HomeViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.binding(getItem(position))
    }
}