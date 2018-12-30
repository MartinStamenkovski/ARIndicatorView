package com.stamenkovski.recyclerindicators.ListView

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.arindicatorview.ARIndicatorView
import com.stamenkovski.recyclerindicators.R
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var arIndicatorView: ARIndicatorView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        listView = list_view
        arIndicatorView = ar_indicator_list_view

        listView.adapter = ListViewAdapter(this@ListViewActivity)
    }
}
