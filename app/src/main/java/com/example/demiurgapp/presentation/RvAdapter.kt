package com.example.demiurgapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demiurgapp.R
import com.example.demiurgapp.databinding.CellLifeBinding
import com.example.demiurgapp.domain.entities.Cell

class RvAdapter : ListAdapter<Cell, RvAdapter.Holder>(Comparator()) {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CellLifeBinding.bind(view)

        fun bind(cell: Cell) = with(binding) {
            title.text = cell.name
            description.text = cell.description

            when (cell.type) {
                Cell.Companion.LifeOrDeath.LIFE.name -> {
                    image.setImageResource(R.drawable.chiken)
                }
                Cell.Companion.LifeOrDeath.DEATH.name -> {
                    image.setImageResource(R.drawable.skeleton)
                }
                Cell.Companion.TypesOfCell.LIFE_CELL.name -> {
                    image.setImageResource(R.drawable.bomb)
                }
                Cell.Companion.TypesOfCell.DEATH_CELL.name -> {
                    image.setImageResource(R.drawable.skeleton)
                }
            }
        }
    }

    class Comparator : DiffUtil.ItemCallback<Cell>() {
        override fun areContentsTheSame(oldItem: Cell, newItem: Cell): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Cell, newItem: Cell): Boolean {
            return oldItem.name == newItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvAdapter.Holder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.cell_life,
            parent,
            false
        )
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

}