package com.stamenkovski.recyclerindicators.ViewPager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.MenuItem
import android.widget.TextView
import com.arindicatorview.ARIndicatorView
import com.stamenkovski.recyclerindicators.R
import kotlinx.android.synthetic.main.activity_view_pager.*

class ViewPagerActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var arIndicatorView: ARIndicatorView
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        this.supportActionBar?.apply {
            title = "View Pager"
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        this.viewPager = view_pager
        this.arIndicatorView = ar_indicator_viewpager
        this.viewPager.adapter = ViewPagerAdapter(this)
        this.arIndicatorView.attachTo(viewPager)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }
}
