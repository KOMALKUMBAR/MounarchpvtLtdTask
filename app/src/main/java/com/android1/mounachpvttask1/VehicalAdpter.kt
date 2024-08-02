package com.android1.mounachpvttask1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

 class VehicalAdpter(private val dataList: List<DataItem>) : RecyclerView.Adapter<VehicalAdpter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.vehicalname)
        val textView2: TextView = itemView.findViewById(R.id.vehicletype)
        val textView3: TextView = itemView.findViewById(R.id.registrationnumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemvehical, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.textView1.text = item.text1
        holder.textView2.text = item.text2
        holder.textView3.text = item.text3
    }

    override fun getItemCount(): Int = dataList.size
}

