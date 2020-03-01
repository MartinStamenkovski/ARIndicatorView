package com.stamenkovski.recyclerindicators

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class Adapter(val context: Context) : androidx.recyclerview.widget.RecyclerView.Adapter<Adapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.items_recycler, p0, false))
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
    }

    inner class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    }
}