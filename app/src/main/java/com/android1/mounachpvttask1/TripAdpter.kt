package com.android1.mounachpvttask1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TripAdpter (private val dataList: List<DataItem>) : RecyclerView.Adapter<TripAdpter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val destination: TextView = itemView.findViewById(R.id.destination)
        val startDate: TextView = itemView.findViewById(R.id.startDate)
        val endDate: TextView = itemView.findViewById(R.id.endDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trip, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.destination.text = item.text1
        holder.startDate.text = item.text2
        holder.endDate.text = item.text3
    }

    override fun getItemCount(): Int = dataList.size
}
