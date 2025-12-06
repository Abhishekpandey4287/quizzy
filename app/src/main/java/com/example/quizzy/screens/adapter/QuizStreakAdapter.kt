package com.example.quizzy.screens.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzy.R
import com.example.quizzy.data.model.StreakItem
import com.example.quizzy.databinding.ItemQuizStreakBinding

class QuizStreakAdapter(
    private val items: List<StreakItem>
) : RecyclerView.Adapter<QuizStreakAdapter.StreakViewHolder>() {

    inner class StreakViewHolder(val binding: ItemQuizStreakBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreakViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemQuizStreakBinding.inflate(inflater, parent, false)
        return StreakViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StreakViewHolder, position: Int) {
        val item = items[position]

        if (item.status == "done") {
            holder.binding.ivStatus.setImageResource(R.drawable.check)
        } else {
            holder.binding.ivStatus.setImageResource(getIcon(item))
        }
    }

    private fun getIcon(item: StreakItem): Int {
        return if (item.status == "done") {
            R.drawable.check
        } else {
            when (item.day) {
                "M"  -> R.drawable.ic_m
                "T"  -> R.drawable.ic_t
                "W"  -> R.drawable.ic_w
                "Th" -> R.drawable.ic_t
                "F"  -> R.drawable.ic_f
                "S" -> R.drawable.ic_s
                "Su" -> R.drawable.ic_s
                else -> R.drawable.check
            }
        }
    }

    override fun getItemCount() = items.size
}