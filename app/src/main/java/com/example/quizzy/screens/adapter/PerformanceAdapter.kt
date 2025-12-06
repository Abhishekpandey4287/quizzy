package com.example.quizzy.screens.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzy.R
import com.example.quizzy.data.model.PerformanceTopic
import com.example.quizzy.databinding.ItemPerformanceBarBinding

class PerformanceAdapter(
    private val items: List<PerformanceTopic>
) : RecyclerView.Adapter<PerformanceAdapter.PerformanceViewHolder>() {

    inner class PerformanceViewHolder(val binding: ItemPerformanceBarBinding)
        : RecyclerView.ViewHolder(binding.root)

    private val Int.dp: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerformanceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPerformanceBarBinding.inflate(inflater, parent, false)
        return PerformanceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PerformanceViewHolder, position: Int) {
        val item = items[position]
        val context = holder.itemView.context

        holder.binding.tvTopicName.text = item.topic

        val height = when (item.trend) {
            "up" -> 70.dp
            "down" -> 30.dp
            else -> 50.dp
        }

        holder.binding.viewBar.layoutParams.height = height

        holder.binding.viewBar.background = when (item.trend) {
            "up" -> ContextCompat.getDrawable(context, R.drawable.bar_up)
            "down" -> ContextCompat.getDrawable(context, R.drawable.bar_down)
            else -> ContextCompat.getDrawable(context, R.drawable.bar_neutral)
        }
    }

    override fun getItemCount(): Int = items.size
}