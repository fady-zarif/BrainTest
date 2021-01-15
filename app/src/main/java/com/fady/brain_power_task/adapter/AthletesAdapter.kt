package com.fady.brain_power_task.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.fady.brain_power_task.R
import com.fady.brain_power_task.databinding.AthleteItemViewBinding
import com.fady.brain_power_task.model.Athlete

class AthletesAdapter(private val interaction: (athlete: Athlete, pos: Int) -> Unit) :
    ListAdapter<Athlete, AthletesAdapter.AthletesViewHolder>(
        DiffCallback()
    ) {
    class DiffCallback : DiffUtil.ItemCallback<Athlete>() {
        override fun areItemsTheSame(oldItem: Athlete, newItem: Athlete): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Athlete, newItem: Athlete): Boolean {
            return oldItem.name == newItem.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AthletesViewHolder {
        val itemView = AthleteItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AthletesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AthletesViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            interaction(getItem(position), position)
        }
    }

    class AthletesViewHolder(
        private val
        athleteItemView: AthleteItemViewBinding
    ) :
        RecyclerView.ViewHolder(athleteItemView.root) {
        fun bind(item: Athlete) {
            if (!item.image.isNullOrEmpty()) {
                Glide.with(athleteItemView.imgAthlete).load(item.image)
                    .placeholder(R.drawable.loading_image).into(athleteItemView.imgAthlete)
            } else {
                athleteItemView.tvName.text = item.name.substring(0, 6)
                athleteItemView.tvName.visibility = View.VISIBLE
            }
        }
    }
}
