package com.stamenkovski.recyclerindicators.ListView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.stamenkovski.recyclerindicators.R

class ListViewAdapter(val context: Context) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return convertView ?: LayoutInflater.from(context).inflate(R.layout.items_list_view, parent, false)
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return 20
    }
}